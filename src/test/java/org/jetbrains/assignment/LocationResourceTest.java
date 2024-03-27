package org.jetbrains.assignment;

import org.jetbrains.assignment.dto.Coordinate;
import org.jetbrains.assignment.dto.Move;
import org.jetbrains.assignment.model.Direction;
import org.jetbrains.assignment.resource.LocationResource;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationResourceTest {


    @Test
    void test_findNeededMovesToReach() {
        Coordinate[] inputs = new Coordinate[4];
        inputs[0] = new Coordinate(0, 0);
        inputs[1] = new Coordinate(4, 0);
        inputs[2] = new Coordinate(2, 2);
        inputs[3] = new Coordinate(0, 4);

        LocationResource locationResource = new LocationResource();

        List<Move> moves = locationResource.findNeededMovesToReach(inputs);
        List<Coordinate> location = locationResource.createLocation(moves.toArray(new Move[0]));

        assertTrue(location.containsAll(List.of(inputs)));
    }

    @Test
    void test_createLocation() {
        Move[] moves = new Move[5];

        moves[0] = Move.of(Direction.EAST, 1);
        moves[1] = Move.of(Direction.NORTH, 3);
        moves[2] = Move.of(Direction.EAST, 3);
        moves[3] = Move.of(Direction.SOUTH, 5);
        moves[4] = Move.of(Direction.WEST, 2);


        LocationResource locationResource = new LocationResource();
        List<Coordinate> location = locationResource.createLocation(moves);

        assertEquals(6, location.size());
        assertPosition(0, 0, location.get(0));
        assertPosition(1, 0, location.get(1));
        assertPosition(1, 3, location.get(2));
        assertPosition(4, 3, location.get(3));
        assertPosition(4, -2, location.get(4));
        assertPosition(2, -2, location.get(5));

    }


    private static void assertPosition(int x, int y, Coordinate coordinate) {
        assertEquals(x + " " + y, coordinate.x() + " " + coordinate.y());
    }

}