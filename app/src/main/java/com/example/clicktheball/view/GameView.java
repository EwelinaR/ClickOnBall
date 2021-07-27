package com.example.clicktheball.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.clicktheball.BallsHandler;
import com.example.clicktheball.R;
import com.example.clicktheball.viewmodel.GameViewModel;

public class GameView extends Fragment {

    private GameViewModel model;
    private BallsHandler ballsHandler;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_game, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        model = new ViewModelProvider(requireActivity()).get(GameViewModel.class);

        TextView points = view.findViewById(R.id.points);
        points.setText(model.getPoints());

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
                ballsHandler.startGame();
            }
            else {
                play.setVisibility(View.VISIBLE);
                stop.setEnabled(false);
                if (ballsHandler != null) ballsHandler.stopBalls();
            }
        });
    }

    private void initNewGame() {
        FrameLayout frameLayout = getView().findViewById(R.id.game_container);

        ballsHandler = new BallsHandler(1, frameLayout, getContext());
        model.onPlayGame();
    }
}
