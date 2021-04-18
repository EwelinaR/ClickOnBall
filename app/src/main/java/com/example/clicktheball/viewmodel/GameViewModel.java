package com.example.clicktheball.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.clicktheball.model.GameStatus;

public class GameViewModel extends ViewModel {

    private final MutableLiveData<Boolean> gameInProgress = new MutableLiveData<>();
    private final GameStatus gameStatus;

    public GameViewModel() {
        gameStatus = new GameStatus();
        gameInProgress.setValue(false);
    }

    public LiveData<Boolean> isGameInProgress() {
        return gameInProgress;
    }

    public String getPoints() {
        return String.valueOf(gameStatus.getPoints());
    }

    public void addPoint() {
        gameStatus.addPoint();
    }

    public void onPlayGame() {
        gameInProgress.setValue(true);
    }

    public void onStopGame() {
        gameInProgress.setValue(false);
        addPoint();
    }
}
