package com.example.clicktheball;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.clicktheball.model.Ball;

import java.util.ArrayList;
import java.util.List;

public class BallsHandler {

    private final int numberOfBalls;
    private List<BallAnimation> balls;
    private final FrameLayout frameLayout;

    public BallsHandler(int numberOfBalls, FrameLayout frameLayout, Context context) {
        this.numberOfBalls = numberOfBalls;
        this.frameLayout = frameLayout;

        initBalls(frameLayout, context);
    }

    private void initBalls(FrameLayout frameLayout, Context context) {
        balls = new ArrayList<>();

        for (int i = 0; i < numberOfBalls; i++) {
            Ball ball = new Ball(context);
            ImageView icon = ball.getIcon();
            frameLayout.addView(icon);
            icon.setOnClickListener(view1 -> addPoints());

            balls.add(new BallAnimation(ball));
            balls.get(i).initFirstPath(frameLayout.getWidth(), frameLayout.getHeight());
            balls.get(i).doAnimation();
        }
    }

    private void addPoints() {
        System.out.println("CLICK");
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
