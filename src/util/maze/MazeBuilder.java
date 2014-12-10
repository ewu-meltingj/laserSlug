/**    *********************************
 *     ******* Team Lazer Slugz ********
 *     *********************************
 *              
 *  This is the current state of the third
 *  iteration for Team Lazer Slugz. The code
 *  uses some more patterns to help seperate
 *  MVC elements. The ultimate goal of this 
 *  iteration is to create a game that can
 *  switch out a GUI on the fly.
 * 	
 */
package util.maze;

import model.Coordinate;
import model.maze.Interactable;
import model.maze.Maze;
import model.passage.A_Passage;
import model.passage.PassageHorizontal;
import model.passage.PassageVertical;
import model.room.Room;
import contracts.I_UserInteract;

// TODO: Auto-generated Javadoc
/**
 * The Class MazeBuilder.
 */
public class MazeBuilder {

	/** The _maze. */
	private static Maze _maze;

	/** The _total rooms. */
	private static int _totalRooms;

	/** The _total side rooms. */
	private static int _totalSideRooms;

	/** The Constant ROOM_WIDTH. */
	private static final int ROOM_WIDTH = 11;

	/** The Constant ROOM_HEIGHT. */
	private static final int ROOM_HEIGHT = 5;

	/** The Constant ROOM_PADDING. */
	private static final int ROOM_PADDING = 5;

	/** The _interactive. */
	private static Interactable _interactive = new Interactable();

	/**
	 * Adds the doors to a room. These doors are horizontal so they will be
	 * assigned to a horizontal passage. Having a horizontal and vertical
	 * passage give me no real benefits. This is a part of the application that
	 * needs to be redesigned.
	 * 
	 * @param roomAdjacent
	 *            the room adjacent
	 * @param roomCurrent
	 *            the room current
	 */
	private static void addDoorsLR(Room roomAdjacent, Room roomCurrent) {
		A_Passage pass = new PassageHorizontal(roomAdjacent, roomCurrent);
		_maze.addPassage(pass);
		roomAdjacent.addDoor(pass.getDoorFirst());
		roomCurrent.addDoor(pass.getDoorSecond());
		_interactive.addArray(new I_UserInteract[] { pass.getDoorSecond(),
				pass.getDoorFirst(), pass, roomAdjacent, roomCurrent });
	}

	/**
	 * Adds the doors to a room. These doors are vertical so they will be
	 * assigned to a vertical passage. Having a horizontal and vertical passage
	 * give me no real benefits. This is a part of the application that needs to
	 * be redesigned.
	 * 
	 * @param roomAdjacent
	 *            the room adjacent
	 * @param roomCurrent
	 *            the room current
	 */
	private static void addDoorsUD(Room roomAdjacent, Room roomCurrent) {
		A_Passage pass = new PassageVertical(roomAdjacent, roomCurrent);
		_maze.addPassage(pass);
		roomAdjacent.addDoor(pass.getDoorFirst());
		roomCurrent.addDoor(pass.getDoorSecond());
		_interactive.addArray(new I_UserInteract[] { roomAdjacent, roomCurrent,
				pass, pass.getDoorFirst(), pass.getDoorSecond() });
	}

	/**
	 * Creates the container holding all of the interactable elements inside of
	 * the created maze.
	 * 
	 * @param maze
	 *            the maze
	 * @return the interactable
	 */
	public static Interactable create(Maze maze) {
		_maze = maze;
		_totalRooms = maze.getRoomTotal();
		_totalSideRooms = maze.getRoomTotalSquared();
		maze.setWidth(gridWidth());
		maze.setHeight(gridHeight());

		for (int roomId = 0; roomId < _totalRooms; roomId++) {
			Room roomCurrent = new Room(roomId, createRoomOrigin(roomId),
					ROOM_WIDTH, ROOM_HEIGHT);
			maze.addRoom(roomId, roomCurrent);

			if (hasRoomAdjacentLeft(roomId)) {
				Room roomLeft = maze.getRoom(roomId - 1);
				addDoorsLR(roomLeft, roomCurrent);
			}
			if (hasRoomAdjacentUp(roomId)) {
				Room roomUp = maze.getRoom(roomId - maze.getRoomTotalSquared());
				addDoorsUD(roomUp, roomCurrent);
			}
		}
		return _interactive;
	}

	/**
	 * Creates the room origin.
	 * 
	 * @param roomID
	 *            the room id sets the origin given some additional settings.
	 * @return the point
	 */
	private static Coordinate createRoomOrigin(int roomID) {
		int offsetX = ROOM_WIDTH + ROOM_PADDING;
		int offsetY = ROOM_HEIGHT + ROOM_PADDING;
		int yCoord = roomID / _totalSideRooms;
		int xCoord = roomID % _totalSideRooms;
		return new Coordinate(yCoord * offsetY + ROOM_PADDING, xCoord * offsetX
				+ ROOM_PADDING);
	}

	/**
	 * Grid height.
	 * 
	 * @return the int
	 */
	private static int gridHeight() {
		return (ROOM_HEIGHT + ROOM_PADDING) * _totalSideRooms + ROOM_PADDING;
	}

	/**
	 * Grid width.
	 * 
	 * @return the int
	 */
	private static int gridWidth() {
		return (ROOM_WIDTH + ROOM_PADDING) * _totalSideRooms + ROOM_PADDING;
	}

	/**
	 * Checks for room adjacent left.
	 * 
	 * @param roomId
	 *            the room id checks a hash map to see if their is a room on the
	 *            left
	 * @return true, if successful a new passage will be created containing both
	 *         rooms with some doors attactched.
	 */
	private static boolean hasRoomAdjacentLeft(int roomId) {
		return _maze.hasRoom(roomId - 1) && roomId % _totalSideRooms != 0;
	}

	/**
	 * Checks for room adjacent up.
	 * 
	 * @param roomId
	 *            the room id checks a hash map to see if their is a room
	 * @return true, if successful a new passage will be created containing both
	 *         rooms with some doors attactched.
	 */
	private static boolean hasRoomAdjacentUp(int roomId) {
		return _maze.hasRoom(roomId - _totalSideRooms);
	}

	/**
	 * privates a new maze builder.
	 */
	private MazeBuilder() {
	}

}
