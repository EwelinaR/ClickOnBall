package com.example.clicktheball.model;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.clicktheball.R;

public class Ball {
    final private static int BALL_RADIUS = 50;
    final private static double BALL_SPEED = 0.5;
    private final ImageView icon;
    private Point startPosition = new Point();
    private Point endPosition = new Point();

    public Ball(Context context) {
        icon = new ImageView(context);
        icon.setImageResource(R.drawable.blue_circle);
        LinearLayout.LayoutParams params
                = new LinearLayout.LayoutParams(BALL_RADIUS * 2, BALL_RADIUS * 2);
        icon.setLayoutParams(params);
    }

    public Point getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Point startPosition) {
        this.startPosition = startPosition;
        icon.setX((int) startPosition.x);
        icon.setY((int) startPosition.y);
    }

    public Point getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Point endPosition) {
        this.endPosition = endPosition;
    }

    public double getXDistance() {
        return endPosition.x - startPosition.x;
    }

    public double getYDistance() {
        return endPosition.y - startPosition.y;
    }

    public ImageView getIcon() {
        return icon;
    }

    public double getSpeed() {
        return BALL_SPEED;
    }

    public int getBallRadius() {
        return BALL_RADIUS;
    }
}
