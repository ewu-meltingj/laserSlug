package util.saver;

import java.io.Serializable;
import java.util.List;

import model.region.RegionMaze;
import model.region.RegionRoom;

public class SaverMaze implements Serializable {

	private static final long serialVersionUID = 2398789582979807509L;

	private int _rooms;

	private List<RegionRoom> _roomsMap;

	private int _mazeWidth;

	private int _mazeHeight;

	public SaverMaze(RegionMaze maze) {
		_rooms = maze.getRoomTotalSquared();
		_roomsMap = maze.getRooms();
		_mazeWidth = maze.getWidth();
		_mazeHeight = maze.getHeight();
	}

	public int getHeight() {
		return _mazeHeight;
	}

	public int getNumberRoom() {
		return _rooms;
	}

	public List<RegionRoom> getRooms() {
		return _roomsMap;
	}

	public int getWidth() {
		return _mazeWidth;
	}
}
