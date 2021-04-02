package com.example.clicktheball.model;

public class GameStatus {
    private long points = 0;

    public long getPoints() {
        return points;
    }

    public void addPoint() {
        points++;
    }
}
