package com.example.clicktheball.viewmodel;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.clicktheball.R;
import com.example.clicktheball.model.GameState;
import com.example.clicktheball.model.GameStatus;

public class GameModelView extends BaseObservable {

    private final GameStatus gameStatus;
    private GameState state;
    private View view;
    private Context context;

    public GameModelView() {
        gameStatus = new GameStatus();
        state = GameState.BEFORE_FIRST_GAME;
    }

    public void setView(View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Bindable
    public boolean isBeforeFirstGameState() {
        return state.equals(GameState.BEFORE_FIRST_GAME);
    }

    @Bindable
    public boolean isPlayingState() {
        return state.equals(GameState.PLAYING);
    }

    @Bindable
    public boolean isEndState() {
        return state.equals(GameState.END);
    }

    @Bindable
    public boolean isReadyState() {
        return state.equals(GameState.READY);
    }

    @Bindable
    public String getPoints() {
        return String.valueOf(gameStatus.getPoints());
    }

    public void addPoint() {
        gameStatus.addPoint();
        notifyChange();
    }

    public void onStartTheGame() {
        gameStatus.resetPoints();
        state = GameState.PLAYING;
        notifyChange();

        initBall();
    }

    public void onStopTheGame() {
        state = GameState.END;
        notifyChange();
    }

    private void initBall() {
        int ballRadius = 50;
        FrameLayout frameLayout = view.findViewById(R.id.game_container);
        ImageView icon = new ImageView(context);
        icon.setImageResource(R.drawable.blue_circle);
        LinearLayout.LayoutParams params
                = new LinearLayout.LayoutParams(ballRadius * 2, ballRadius * 2);
        icon.setLayoutParams(params);
        frameLayout.addView(icon);

        int gamePanelWidth = frameLayout.getWidth();
        int gamePaneHeight = frameLayout.getHeight();
        icon.setX(gamePanelWidth - ballRadius * 2);
        icon.setY(gamePaneHeight - ballRadius * 2);
    }
}
