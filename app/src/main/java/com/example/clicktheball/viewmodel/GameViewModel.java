package com.example.clicktheball.viewmodel;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.clicktheball.model.Ball;
import com.example.clicktheball.model.Point;

public class GameViewModel extends ViewModel {

    private final MutableLiveData<Boolean> gameInProgress = new MutableLiveData<>();
    private final MutableLiveData<Integer> points = new MutableLiveData<>();
    private final int numberOfBalls = 2;
    private double gamePanelWidth;
    private double gamePanelHeight;

    public GameViewModel() {
        gameInProgress.postValue(false);
    }

    public LiveData<Boolean> isGameInProgress() {
        return gameInProgress;
    }

    public LiveData<Integer> getPoints() {
        return points;
    }

    public void ballClicked(Ball ball) {
        this.points.setValue(this.points.getValue() + ball.getPoints());
        ball.setClicked(true);
    }

    public void onPlayGame() {
        gameInProgress.setValue(true);
        points.setValue(0);
    }

    public void onStop() {
        onStopGame();
        points.setValue(0);
    }

    public void onStopGame() {
        gameInProgress.setValue(false);
    }

    public int getNumberOfBalls() {
        return numberOfBalls;
    }

    public void setGamePanelSize(double gamePanelWidth, double gamePanelHeight) {
        this.gamePanelWidth = gamePanelWidth;
        this.gamePanelHeight = gamePanelHeight;
    }

    public Ball getBallModel() {
        Ball ball = new Ball();
        getNextAnimation(ball);
        return ball;
    }

    public void getNextAnimation(Ball ball) {
        ball.setClicked(false);
        // ball can be on the left/right or above/below screen
        int startSide = (int) (Math.random() * 4);
        ball.setStartPosition(initPosition(startSide, ball));

        // end position has to be different than start side
        int endSide = (int) (Math.random() * 3);
        if (endSide >= startSide) endSide++;
        ball.setEndPosition(initPosition(endSide, ball));
    }

    @VisibleForTesting
    Point initPosition(int side, Ball ball) {
        Point position = new Point();
        int ballRadius = ball.getBallRadius();
        if (side % 2 == 0) {
            // even side means up or down
            position.x = Math.random() * (gamePanelWidth - 2 * ballRadius);
            if (side == 0) {
                position.y = gamePanelHeight;
            } else {
                position.y = -2 * ballRadius;
            }
        } else {
            // odd side means left or right
            if (side == 1) {
                position.x = -2 * ballRadius;
            } else {
                position.x = gamePanelWidth;
            }
            position.y = Math.random() * (gamePanelHeight - 2 * ballRadius);
        }
        return position;
    }

    public long getAnimationDuration(Ball ballModel) {
        double destination = Math.sqrt(Math.pow(ballModel.getXDistance(), 2)
                + Math.pow(ballModel.getYDistance(), 2));
        return (long) (destination / ballModel.getSpeed());
    }

    public float getXAnimatedPosition(double value, Ball ball) {
        return (float) (ball.getStartPosition().x + ball.getXDistance() * value / 100);
    }

    public float getYAnimatedPosition(double value, Ball ball) {
        return (float) (ball.getStartPosition().y + ball.getYDistance() * value / 100);
    }
}
