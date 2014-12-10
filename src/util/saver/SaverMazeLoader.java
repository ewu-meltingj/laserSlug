package util.saver;

import model.passage.A_Passage;
import model.passage.PassageHorizontal;
import model.passage.PassageVertical;
import model.region.RegionMaze;
import model.region.RegionRoom;

public class SaverMazeLoader {

	private RegionMaze _maze;

	private int _totalSideRooms;

	private static final int ROOM_WIDTH = 11;

	private static final int ROOM_HEIGHT = 5;

	private static final int ROOM_PADDING = 5;

	public SaverMazeLoader() {

	}

	private void addDoorsLR(RegionRoom roomAdjacent, RegionRoom roomCurrent) {
		A_Passage pass = new PassageHorizontal(roomAdjacent, roomCurrent);
		_maze.addPassage(pass);
		roomAdjacent.addDoor(pass.getDoorFirst());
		roomCurrent.addDoor(pass.getDoorSecond());
	}

	private void addDoorsUD(RegionRoom roomAdjacent, RegionRoom roomCurrent) {
		A_Passage pass = new PassageVertical(roomAdjacent, roomCurrent);
		_maze.addPassage(pass);
		roomAdjacent.addDoor(pass.getDoorFirst());
		roomCurrent.addDoor(pass.getDoorSecond());
	}

	public RegionMaze create(SaverMaze attributes) {
		_maze = new RegionMaze(attributes.getNumberRoom());
		_maze.setHeight(attributes.getHeight());
		_maze.setWidth(attributes.getWidth());
		_totalSideRooms = _maze.getRoomTotalSquared();
		int roomId;

		for (RegionRoom roomCurrent : attributes.getRooms()) {
			roomId = roomCurrent.getId();
			_maze.addRoom(roomId, roomCurrent);

			if (hasRoomAdjacentLeft(roomId)) {
				RegionRoom roomLeft = _maze.getRoom(roomId - 1);
				addDoorsLR(roomLeft, roomCurrent);
			}
			if (hasRoomAdjacentUp(roomId)) {
				RegionRoom roomUp = _maze.getRoom(roomId
						- _maze.getRoomTotalSquared());
				addDoorsUD(roomUp, roomCurrent);
			}
		}
		return _maze;
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
