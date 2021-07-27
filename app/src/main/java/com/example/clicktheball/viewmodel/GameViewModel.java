package com.example.clicktheball.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GameViewModel extends ViewModel {

    private final MutableLiveData<Boolean> gameInProgress = new MutableLiveData<>();
    private final MutableLiveData<Integer> points = new MutableLiveData<>();

    public GameViewModel() {
        gameInProgress.setValue(false);
        points.setValue(0);
    }

    public LiveData<Boolean> isGameInProgress() {
        return gameInProgress;
    }

    public LiveData<Integer> getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points.setValue(this.points.getValue() + points);
    }

    public void onPlayGame() {
        gameInProgress.setValue(true);
    }

    public void onStopGame() {
        gameInProgress.setValue(false);
    }
}
