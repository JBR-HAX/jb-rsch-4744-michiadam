package org.jetbrains.assignment.dto;


import org.jetbrains.assignment.model.Direction;


public class Move {

    Direction direction;
    int steps;

    public Move(Direction direction, int steps) {
        this.direction = direction;
        this.steps = steps;
    }

    public static Move of(Direction east, int steps) {
          return new Move(east, steps);
    }

    public Direction getDirection() {
        return direction;
    }



    public int getSteps() {
        return steps;
    }


}
