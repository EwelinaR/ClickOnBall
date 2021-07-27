package com.example.clicktheball;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.clicktheball.model.Ball;

import java.util.ArrayList;
import java.util.List;

public class BallsHandler {

    private final int numberOfBalls;
    private final PointsObserver points;
    private List<BallAnimation> balls;

    public BallsHandler(int numberOfBalls, FrameLayout frameLayout, Context context, PointsObserver points) {
        this.numberOfBalls = numberOfBalls;
        this.points = points;

        initBalls(frameLayout, context);
    }

    private void initBalls(FrameLayout frameLayout, Context context) {
        balls = new ArrayList<>();

        for (int i = 0; i < numberOfBalls; i++) {
            Ball ball = new Ball(context);
            ImageView icon = ball.getIcon();
            frameLayout.addView(icon);
            icon.setOnClickListener(view1 -> addPoints(ball.getPoints()));

            balls.add(new BallAnimation(ball));
            balls.get(i).initFirstPath(frameLayout.getWidth(), frameLayout.getHeight());
            balls.get(i).doAnimation();
        }
    }

    private void addPoints(int points) {
        this.points.updatePoints(points);
    }

    public void startGame() {
        for (BallAnimation ball : balls) {
            ball.play();
        }
    }

    public void stopBalls() {
        for (BallAnimation ball : balls) {
            ball.stop();
        }
    }
}
