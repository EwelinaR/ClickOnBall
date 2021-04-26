package com.example.clicktheball.model;

import android.content.Context;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.clicktheball.R;

public class Ball {
    final private static int BALL_RADIUS = 50;
    final private static float BALL_SPEED = 0.5f;
    private final ImageView icon;
    private int gamePanelWidth;
    private int gamePanelHeight;
    private Point startPosition;
    private Point endPosition;

    public Ball(Context context) {
        icon = new ImageView(context);
        icon.setImageResource(R.drawable.blue_circle);
        LinearLayout.LayoutParams params
                = new LinearLayout.LayoutParams(BALL_RADIUS * 2, BALL_RADIUS * 2);
        icon.setLayoutParams(params);
    }

    public void initIcon(int gamePanelWidth, int gamePanelHeight) {
        this.gamePanelWidth = gamePanelWidth;
        this.gamePanelHeight = gamePanelHeight;
        startPosition = new Point();

        startPosition.x = Math.random() * (gamePanelWidth - 2*BALL_RADIUS);
        startPosition.y = Math.random() * (gamePanelHeight - 2*BALL_RADIUS);

        double radian = Math.random() * 2 * Math.PI;
        endPosition = getDestination(startPosition, radian);

        icon.setX((int) startPosition.x);
        icon.setY((int) startPosition.y);
    }

    private Point getDestination(Point start, double radian) {
        double xDirection = Math.sin(radian);
        double yDirection = Math.cos(radian);
        boolean up = yDirection < 0;
        boolean right = xDirection > 0;

        Point destination = new Point();

        // equation of ball motion
        // y = ax + b
        double a = (start.y - (start.y + yDirection)) / (start.x - (start.x + xDirection));
        double b = start.y - ((start.y - (start.y + yDirection)) / (start.x - (start.x + xDirection))) * start.x;

        double x;
        double y;
        if (up) {
            x = (BALL_RADIUS - b) / a;
            // check if crossing point with top border is inside game panel
            if (0 <= x && x <= gamePanelWidth - 2*BALL_RADIUS) {
                destination.x = x;
                destination.y = 0;
            } else if (Double.isInfinite(a)) {  // when y = b
                destination.x = start.x;
                destination.y = 0;
            }
        } else {
            x = (gamePanelHeight - 2*BALL_RADIUS - b) / a;
            if (0 <= x && x <= gamePanelWidth - 2*BALL_RADIUS) {
                destination.x = x;
                destination.y = gamePanelHeight - 2*BALL_RADIUS;

            } else if (Double.isInfinite(a)) {  // when y = b
                destination.x = start.x;
                destination.y = gamePanelHeight - 2*BALL_RADIUS;
            }
        }
        if (right) {
            y = a*(gamePanelWidth - 2*BALL_RADIUS) + b;
            if (0 <= y && y <= gamePanelHeight - 2*BALL_RADIUS) {
                destination.x = gamePanelWidth - 2*BALL_RADIUS;
                destination.y = y;
            }
        } else {
            y = a*BALL_RADIUS + b;
            if (0 <= y && y <= gamePanelHeight - 2*BALL_RADIUS) {
                destination.x = 0;
                destination.y = y;
            }
        }
        return destination;
    }

    public ImageView getIcon() {
        return icon;
    }

    public void play() {
        TranslateAnimation trans =
                new TranslateAnimation(0, (float) (endPosition.x - startPosition.x),
                        0,  (float) (endPosition.y - startPosition.y));

        trans.setDuration(getAnimationDuration());
        trans.setFillAfter(true);
        trans.setFillEnabled(true);
        icon.setAnimation(trans);
    }

    private long getAnimationDuration() {
        double destination = Math.sqrt(Math.pow(endPosition.x - startPosition.x, 2)
                + Math.pow(endPosition.y - startPosition.y, 2));
        return (long) (destination / BALL_SPEED);
    }
}
