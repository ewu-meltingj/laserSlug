package model.region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.passage.A_Passage;

public class RegionMaze extends A_Region {

	private int _roomTotalSquared;

	private HashMap<Integer, RegionRoom> _roomsMap;

	private List<A_Passage> _passageList;

	private int _IdRoomEnd;

	private int _IdRoomStart;

	private boolean _isTraversable;

	public RegionMaze(int roomTotalSquared) {
		super(1);
		_roomTotalSquared = roomTotalSquared;
		_roomsMap = new HashMap<Integer, RegionRoom>(roomTotalSquared
				* roomTotalSquared);
		_passageList = new ArrayList<A_Passage>();
		_IdRoomEnd = roomTotalSquared * roomTotalSquared - 1;
		_IdRoomStart = 0;
		_isTraversable = true;
	}

	public void addPassage(A_Passage passage) {
		_passageList.add(passage);
	}

	public void addRoom(Integer id, RegionRoom room) {
		_roomsMap.put(id, room);
	}

	public List<A_Passage> getPassages() {
		return _passageList;
	}

	public RegionRoom getRoom(int roomId) {
		if (_roomsMap.containsKey(roomId))
			return _roomsMap.get(roomId);
		throw new RuntimeException("No room");
	}

	public RegionRoom getRoomEnd() {
		return _roomsMap.get(_IdRoomEnd);
	}

	public List<RegionRoom> getRooms() {
		return new ArrayList<RegionRoom>(_roomsMap.values());
	}

	public RegionRoom getRoomStart() {
		return _roomsMap.get(_IdRoomStart);
	}

	public int getRoomTotal() {
		return _roomTotalSquared * _roomTotalSquared;
	}

	public int getRoomTotalSquared() {
		return _roomTotalSquared;
	}

	public boolean hasRoom(int roomId) {
		return _roomsMap.containsKey(roomId);
	}

	public boolean isTraversable() {
		return _isTraversable;
	}

	public void isTraversable(boolean isTraversable) {
		_isTraversable = isTraversable;
	}

}
