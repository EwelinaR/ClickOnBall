package com.example.clicktheball.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.clicktheball.model.GameState;
import com.example.clicktheball.model.GameStatus;

public class GameModelView extends BaseObservable {

    private final GameStatus gameStatus;
    private GameState state;

    public GameModelView() {
        gameStatus = new GameStatus();
        state = GameState.BEFORE_FIRST_GAME;
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
    }

    public void onStopTheGame() {
        state = GameState.END;
        notifyChange();
    }
}
