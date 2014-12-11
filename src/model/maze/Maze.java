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
package model.maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Region;
import model.passage.A_Passage;
import model.room.Room;

// TODO: Auto-generated Javadoc
/**
 * The Class Maze.
 */
public class Maze extends Region {

	/** The _room total squared. */
	private int _roomTotalSquared;

	/** The _rooms map. */
	private HashMap<Integer, Room> _roomsMap;

	/** The _passage list. */
	private List<A_Passage> _passageList;

	/** The _ id room end. */
	private int _IdRoomEnd;

	/** The _ id room start. */
	private int _IdRoomStart;

	/** The _is traversable. */
	private boolean _isTraversable;

	/**
	 * Instantiates a new maze.
	 * 
	 * @param roomTotalSquared
	 *            the room total squared
	 */
	public Maze(int roomTotalSquared) {
		super(1);
		_roomTotalSquared = roomTotalSquared;
		_roomsMap = new HashMap<Integer, Room>(roomTotalSquared
				* roomTotalSquared);
		_passageList = new ArrayList<A_Passage>();
		_IdRoomEnd = roomTotalSquared * roomTotalSquared - 1;
		_IdRoomStart = 0;
		_isTraversable = true;
	}

	/**
	 * Adds the passage.
	 * 
	 * @param passage
	 *            the passage
	 */
	public void addPassage(A_Passage passage) {
		_passageList.add(passage);
	}

	/**
	 * Adds the room.
	 * 
	 * @param id
	 *            the id
	 * @param room
	 *            the room
	 */
	public void addRoom(Integer id, Room room) {
		_roomsMap.put(id, room);
	}

	/**
	 * Gets the passages.
	 * 
	 * @return the passages
	 */
	public List<A_Passage> getPassages() {
		return _passageList;
	}

	/**
	 * Gets the room.
	 * 
	 * @param roomId
	 *            the room id
	 * @return the room
	 */
	public Room getRoom(int roomId) {
		if (_roomsMap.containsKey(roomId))
			return _roomsMap.get(roomId);
		throw new RuntimeException("No room");
	}

	/**
	 * Gets the room end.
	 * 
	 * @return the room end
	 */
	public Room getRoomEnd() {
		return _roomsMap.get(_IdRoomEnd);
	}

	/**
	 * Gets the rooms.
	 * 
	 * @return the rooms
	 */
	public List<Room> getRooms() {
		return new ArrayList<Room>(_roomsMap.values());
	}

	/**
	 * Gets the room start.
	 * 
	 * @return the room start
	 */
	public Room getRoomStart() {
		return _roomsMap.get(_IdRoomStart);
	}

	/**
	 * Gets the room total.
	 * 
	 * @return the room total
	 */
	public int getRoomTotal() {
		return _roomTotalSquared * _roomTotalSquared;
	}

	/**
	 * Gets the room total squared.
	 * 
	 * @return the room total squared
	 */
	public int getRoomTotalSquared() {
		return _roomTotalSquared;
	}

	/**
	 * Checks for room.
	 * 
	 * @param roomId
	 *            the room id
	 * @return true, if successful
	 */
	public boolean hasRoom(int roomId) {
		return _roomsMap.containsKey(roomId);
	}

	/**
	 * Checks if is traversable.
	 * 
	 * @return true, if is traversable
	 */
	public boolean isTraversable() {
		return _isTraversable;
	}

	/**
	 * Checks if is traversable.
	 * 
	 * @param isTraversable
	 *            the is traversable
	 */
	public void isTraversable(boolean isTraversable) {
		_isTraversable = isTraversable;
	}

}
