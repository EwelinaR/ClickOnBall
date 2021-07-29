package com.example.clicktheball.model;

public class Ball {
    final private static int BALL_RADIUS = 50;
    final private static double BALL_SPEED = 0.5;
    final private static int POINTS = 10;
    private Point startPosition = new Point();
    private Point endPosition = new Point();
    private boolean clicked = false;

    public Point getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Point startPosition) {
        this.startPosition = startPosition;
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

    public double getSpeed() {
        return BALL_SPEED;
    }

    public int getPoints() {
        return POINTS;
    }

    public int getBallRadius() {
        return BALL_RADIUS;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
