package com.example.clicktheball;

import android.animation.ValueAnimator;
import android.view.View;
import android.widget.ImageView;

import com.example.clicktheball.model.Ball;
import com.example.clicktheball.model.Point;

public class BallAnimation {

    private final Ball ball;
    private int gamePanelWidth;
    private int gamePanelHeight;

    public BallAnimation(Ball ball) {
        this.ball = ball;
    }

    public void initFirstPath(int gamePanelWidth, int gamePanelHeight) {
        this.gamePanelWidth = gamePanelWidth;
        this.gamePanelHeight = gamePanelHeight;

        chooseNewPath();
    }

    public void play() {
        ball.getIcon().setVisibility(View.VISIBLE);
        doAnimation();
    }

    public void stop() {
        ball.getIcon().setVisibility(View.INVISIBLE);
    }

    private void chooseNewPath() {
        // ball can be on the left/right or above/below screen
        int startSide = (int) Math.round(Math.random() * 4);
        ball.setStartPosition(initPosition(startSide));

        // end position has to be different than start side
        int endSide = (int) Math.round(Math.random() * 3);
        if (endSide >= startSide) endSide++;
        ball.setEndPosition(initPosition(endSide));
    }

    private Point initPosition(int side) {
        Point position = new Point();
        int ballRadius = ball.getBallRadius();
        if (side % 2 == 0) {
            // even side means up or down
            position.x = Math.random() * (gamePanelWidth - 2 * ballRadius);
            if (side == 0) {
                position.y = gamePanelHeight + ballRadius;
            } else {
                position.y = -2 * ballRadius;
            }
        } else {
            // odd side means left or right
            if (side == 1) {
                position.x = -2 * ballRadius;
            } else {
                position.x = gamePanelWidth + ballRadius;
            }
            position.y = Math.random() * (gamePanelHeight - 2 * ballRadius);
        }
        return position;
    }

    public void doAnimation() {
        ValueAnimator animation = ValueAnimator.ofFloat(0f, 100f);
        animation.setDuration(2000);

        animation.addUpdateListener(valueAnimator -> {
            float value = (float) animation.getAnimatedValue() / 100;

            ImageView icon = ball.getIcon();
            icon.setX((float) (ball.getStartPosition().x + ball.getXDistance() * value));
            icon.setY((float) (ball.getStartPosition().y + ball.getYDistance() * value));

             if (value == 1) {
                nextAnimation();
             }
        });
        animation.start();
    }

    public void nextAnimation() {
        chooseNewPath();
        doAnimation();
    }

    private long getAnimationDuration() {
        double destination = Math.sqrt(Math.pow(ball.getXDistance(), 2)
                + Math.pow(ball.getYDistance(), 2));
        return (long) (destination / ball.getSpeed());
    }
}
