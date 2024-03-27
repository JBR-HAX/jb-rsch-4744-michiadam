package org.jetbrains.assignment.resource;

import org.jetbrains.assignment.dto.Coordinate;
import org.jetbrains.assignment.dto.Move;
import org.jetbrains.assignment.model.Direction;
import org.jetbrains.assignment.model.Robot;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a REST controller that provides two endpoints to calculate the moves needed to reach a destination and to calculate the locations visited by a robot.
 */
@RestController
public class LocationResource {
    /**
     * This method calculates the moves needed to reach a destination.
     * Crude implementation: The robot moves to the destination in a straight line.
     *
     * @param destination The destination coordinates.
     * @return List of moves needed to reach the destination.
     */
    @PostMapping("/moves")
    public List<Move> findNeededMovesToReach(@RequestBody Coordinate[] destination) {

        Robot robot = new Robot(destination[0].x(), destination[0].y());
        List<Move> outputData = new ArrayList<>();

        for (Coordinate coordinate : destination) {
            int x = coordinate.x();
            int y = coordinate.y();
            int dx = x - robot.getX();
            int dy = y - robot.getY();
            Direction directionX = dx > 0 ? Direction.EAST : Direction.WEST;
            Direction directionY = dy > 0 ? Direction.NORTH : Direction.SOUTH;
            int stepsX = Math.abs(dx);
            int stepsY = Math.abs(dy);
            outputData.add(new Move(directionX, stepsX));
            robot.setDirection(directionX);
            robot.stepForward(stepsX);
            outputData.add(new Move(directionY, stepsY));
            robot.setDirection(directionY);
            robot.stepForward(stepsY);
        }

        return outputData;
    }


    /**
     * This method calculates the locations visited by a robot. The robot moves in the given directions and steps.
     *
     * @param location The directions and steps to move.
     * @return List of coordinates visited by the robot.
     */
    @PostMapping("/locations")
    public List<Coordinate> createLocation(@RequestBody Move[] location) {

        Robot robot = new Robot(0, 0);

        List<Coordinate> outputData = new ArrayList<>();
        outputData.add(new Coordinate(robot.getX(), robot.getY()));
        for (Move move : location) {

            robot.setDirection(move.getDirection());
            robot.stepForward(move.getSteps());

            Coordinate coordinateElement = new Coordinate(robot.getX(), robot.getY());
            if (!outputData.contains(coordinateElement)) {
                outputData.add(coordinateElement);
            }
        }

        return outputData;
    }


}
