package com.example.clicktheball.viewmodel;

import com.example.clicktheball.model.Ball;
import com.example.clicktheball.model.Point;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class GameViewModelTest {

    private final int SIZE = 100;

    @Test
    public void pointsAreOndBorders() {
        // arrange
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setGamePanelSize(SIZE, SIZE);
        Ball ball = new Ball();

        // act
        gameViewModel.getNextAnimation(ball);

        // assert
        assertTrue(isPointOnBorder(ball.getStartPosition()));
        assertTrue(isPointOnBorder(ball.getEndPosition()));
    }

    @Test
    public void startAndEnSideAreDifferent() {
        // arrange
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setGamePanelSize(SIZE, SIZE);
        Ball ball = new Ball();

        // act
        gameViewModel.getNextAnimation(ball);

        // assert
        assertNotEquals(getSide(ball.getStartPosition()), getSide(ball.getEndPosition()));
    }

    @Test
    public void bottomSideHasValidPosition() {
        // arrange
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setGamePanelSize(SIZE, SIZE);
        Ball ball = new Ball();

        // act
        Point position = gameViewModel.initPosition(0, ball);

        // assert
        assertEquals(SIZE, position.y, 0.01);
        assertNotEquals(-2 * ball.getBallRadius(), position.x,0.01);
        assertNotEquals(SIZE + 2 * ball.getBallRadius(), position.x,0.01);
    }

    @Test
    public void leftSideHasValidPosition() {
        // arrange
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setGamePanelSize(SIZE, SIZE);
        Ball ball = new Ball();

        // act
        Point position = gameViewModel.initPosition(1, ball);

        // assert
        assertEquals(-2 * ball.getBallRadius(), position.x,0.01);
        assertNotEquals(-2 * ball.getBallRadius(), position.y,0.01);
        assertNotEquals(SIZE + 2 * ball.getBallRadius(), position.y,0.01);
    }

    @Test
    public void upperSideHasValidPosition() {
        // arrange
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setGamePanelSize(SIZE, SIZE);
        Ball ball = new Ball();

        // act
        Point position = gameViewModel.initPosition(2, ball);

        // assert
        assertEquals(-2 * ball.getBallRadius(), position.y,0.01);
        assertNotEquals(-2 * ball.getBallRadius(), position.x,0.01);
        assertNotEquals(SIZE + 2 * ball.getBallRadius(), position.x,0.01);
    }

    @Test
    public void rightSideHasValidPosition() {
        // arrange
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setGamePanelSize(SIZE, SIZE);
        Ball ball = new Ball();

        // act
        Point position = gameViewModel.initPosition(3, ball);

        // assert
        assertEquals(SIZE, position.x, 0.01);
        assertNotEquals(-2 * ball.getBallRadius(), position.y,0.01);
        assertNotEquals(SIZE + 2 * ball.getBallRadius(), position.y,0.01);
    }

    @Test
    @Parameters({"0, 10, 14", "0, 5, 10"})
    public void animationDuration(int x, int y, long expectedDuration) {
        // arrange
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setGamePanelSize(10, 10);

        Ball ball = new Ball();
        ball.setStartPosition(new Point(5, 5));
        ball.setEndPosition(new Point(x, y));

        // act
        long duration = gameViewModel.getAnimationDuration(ball);

        // assert
        assertEquals(expectedDuration, duration);
    }

    private boolean isPointOnBorder(Point point) {
        return isSinglePositionOnBorder(point.x) && !isSinglePositionOnBorder(point.y)
            || !isSinglePositionOnBorder(point.x) && isSinglePositionOnBorder(point.y);
    }

    private boolean isSinglePositionOnBorder(double point) {
        return point < 0 || point == SIZE;
    }

    private int getSide(Point point) {
        if (point.y == SIZE) {
            return 0;
        } else if (point.x < 0) {
            return 1;
        } else if (point.y < 0) {
            return 2;
        } else if (point.x == SIZE) {
            return 3;
        }
        return -1;
    }
}
