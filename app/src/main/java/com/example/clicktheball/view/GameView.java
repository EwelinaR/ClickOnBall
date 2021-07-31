package com.example.clicktheball.view;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.clicktheball.model.Ball;
import com.example.clicktheball.R;
import com.example.clicktheball.viewmodel.GameViewModel;

import java.util.ArrayList;
import java.util.List;

public class GameView extends Fragment {

    private GameViewModel model;
    private final List<ImageView> balls = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_game, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        model = new ViewModelProvider(requireActivity()).get(GameViewModel.class);

        TextView points = view.findViewById(R.id.points);
        model.getPoints().observe(this, newPoints -> points.setText(String.valueOf(newPoints)));

        initButtons(view);
    }

    private void initButtons(View view) {
        Button play = view.findViewById(R.id.play_button);
        play.setOnClickListener(view1 -> initNewGame());

        Button stop = view.findViewById(R.id.stop_game);
        stop.setOnClickListener(view1 -> model.onStopGame());

        model.isGameInProgress().observe(this, gameInProgress -> {
            if (gameInProgress) {
                play.setVisibility(View.INVISIBLE);
                stop.setEnabled(true);
            }
            else {
                play.setVisibility(View.VISIBLE);
                stop.setEnabled(false);
                stopGame();
            }
        });
    }

    private void initNewGame() {
        FrameLayout frameLayout = getView().findViewById(R.id.game_container);
        model.setGamePanelSize(frameLayout.getWidth(), frameLayout.getHeight());

        for (int i = 0; i < model.getNumberOfBalls(); i++) {
            ImageView icon = new ImageView(getContext());
            frameLayout.addView(icon);
            balls.add(icon);
        }
        model.onPlayGame();
        startGame();
    }

    private void startGame() {
        for (ImageView ball : balls) {
            Ball ballModel = model.getBallModel();

            // init ball icon
            ball.setOnClickListener(view1 ->  model.ballClicked(ballModel));
            ball.setImageResource(R.drawable.blue_circle);
            FrameLayout.LayoutParams params
                    = new FrameLayout.LayoutParams(ballModel.getBallRadius() * 2, ballModel.getBallRadius() * 2);
            ball.setLayoutParams(params);
            ball.setX((float) ballModel.getStartPosition().x);
            ball.setY((float) ballModel.getStartPosition().y);
            doAnimation(ballModel, ball);
        }
    }

    public void doAnimation(Ball ballModel, ImageView ball) {
        final int maxValue = 100;
        ValueAnimator animation = ValueAnimator.ofFloat(0f, maxValue);

        // init animation path and time
        model.getNextAnimation(ballModel);
        ball.setX((float) ballModel.getStartPosition().x);
        ball.setY((float) ballModel.getStartPosition().y);

        animation.setDuration(model.getAnimationDuration(ballModel));

        animation.addUpdateListener(valueAnimator -> {
            if (model.isGameInProgress().getValue()) {
                float value = (float) animation.getAnimatedValue();

                ball.setX(model.getXAnimatedPosition(value, ballModel));
                ball.setY(model.getYAnimatedPosition(value, ballModel));

                if (value == maxValue || ballModel.isClicked()) {
                    doAnimation(ballModel, ball);
                    animation.cancel();
                }
            } else {
                // stop animation
                animation.cancel();
            }
        });
        animation.start();
    }

    private void stopGame() {
        FrameLayout frameLayout = getView().findViewById(R.id.game_container);
        // remove ImageViews from View
        for (ImageView ball : balls) {
            frameLayout.removeView(ball);
        }
        balls.clear();
    }

    @Override
    public void onStop() {
        super.onStop();
        model.onStop();
    }
}
