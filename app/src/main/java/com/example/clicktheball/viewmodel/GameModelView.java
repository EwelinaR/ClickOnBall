package com.example.clicktheball.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.clicktheball.model.GameStatus;

public class GameModelView extends BaseObservable {

    private final GameStatus gameStatus;

    public GameModelView() {
        this.gameStatus = new GameStatus();
    }

    @Bindable
    public String getPoints() {
        return String.valueOf(gameStatus.getPoints());
    }

    public void addPoint() {
        gameStatus.addPoint();
        notifyChange();
    }
}
