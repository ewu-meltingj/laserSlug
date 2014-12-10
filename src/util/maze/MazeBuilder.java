package util.maze;

import model.passage.A_Passage;
import model.passage.PassageHorizontal;
import model.passage.PassageVertical;
import model.point.Point;
import model.region.RegionMaze;
import model.region.RegionRoom;
import contracts.I_UserInteract;

public class MazeBuilder {

	private RegionMaze _maze;

	private int _totalRooms;

	private int _totalSideRooms;

	private static final int ROOM_WIDTH = 11;

	private static final int ROOM_HEIGHT = 5;

	private static final int ROOM_PADDING = 5;

	Interactive _interactive;

	public MazeBuilder() {
		_interactive = new Interactive();
	}

	private void addDoorsLR(RegionRoom roomAdjacent, RegionRoom roomCurrent) {
		A_Passage pass = new PassageHorizontal(roomAdjacent, roomCurrent);
		_maze.addPassage(pass);
		roomAdjacent.addDoor(pass.getDoorFirst());
		roomCurrent.addDoor(pass.getDoorSecond());
		_interactive.addToMapAll(new I_UserInteract[] { pass.getDoorSecond(),
				pass.getDoorFirst(), pass, roomAdjacent, roomCurrent });
	}

	private void addDoorsUD(RegionRoom roomAdjacent, RegionRoom roomCurrent) {
		A_Passage pass = new PassageVertical(roomAdjacent, roomCurrent);
		_maze.addPassage(pass);
		roomAdjacent.addDoor(pass.getDoorFirst());
		roomCurrent.addDoor(pass.getDoorSecond());
		_interactive.addToMapAll(new I_UserInteract[] { roomAdjacent,
				roomCurrent, pass, pass.getDoorFirst(), pass.getDoorSecond() });
	}

	public Interactive create(RegionMaze maze) {
		_maze = maze;
		_totalRooms = maze.getRoomTotal();
		_totalSideRooms = maze.getRoomTotalSquared();
		maze.setWidth(gridWidth());
		maze.setHeight(gridHeight());

		for (int roomId = 0; roomId < _totalRooms; roomId++) {
			RegionRoom roomCurrent = new RegionRoom(roomId,
					createRoomOrigin(roomId), ROOM_WIDTH, ROOM_HEIGHT);
			maze.addRoom(roomId, roomCurrent);

			if (hasRoomAdjacentLeft(roomId)) {
				RegionRoom roomLeft = maze.getRoom(roomId - 1);
				addDoorsLR(roomLeft, roomCurrent);
			}
			if (hasRoomAdjacentUp(roomId)) {
				RegionRoom roomUp = maze.getRoom(roomId
						- maze.getRoomTotalSquared());
				addDoorsUD(roomUp, roomCurrent);
			}
		}
		return _interactive;
	}

	public Point createRoomOrigin(int roomID) {
		int offsetX = ROOM_WIDTH + ROOM_PADDING;
		int offsetY = ROOM_HEIGHT + ROOM_PADDING;
		int yCoord = roomID / _totalSideRooms;
		int xCoord = roomID % _totalSideRooms;
		return new Point(yCoord * offsetY + ROOM_PADDING, xCoord * offsetX
				+ ROOM_PADDING);
	}

	public int gridHeight() {
		return (ROOM_HEIGHT + ROOM_PADDING) * _totalSideRooms + ROOM_PADDING;
	}

	public int gridWidth() {
		return (ROOM_WIDTH + ROOM_PADDING) * _totalSideRooms + ROOM_PADDING;
	}

	private boolean hasRoomAdjacentLeft(int roomId) {
		return _maze.hasRoom(roomId - 1) && roomId % _totalSideRooms != 0;
	}

	private boolean hasRoomAdjacentUp(int roomId) {
		return _maze.hasRoom(roomId - _totalSideRooms);
	}

}
