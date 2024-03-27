package org.jetbrains.assignment.model;

public class Robot {
    private int x;
    private int y;
    private Direction direction;

    public Robot(int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = Direction.NORTH;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void stepForward(int steps) {
        for (int i = 0; i < steps; i++) {
            stepForward();
        }
    }

    public void stepForward() {
        x += direction.dx;
        y += direction.dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
