package model.player;

import model.Point;
import model.room.Room;
import util.text.TextSlug;
import contracts.I_GetObserved;

public class Player implements I_GetObserved{

	private int _xCoordinate;

	private int _yCoordinate;

	private Point _oldPosition;

	private int _symbolPlayer;

	private transient Room _currentRoom;

	private int _currentRoomID;

	private boolean _hasQuit;

	private boolean _isStateChanged;

	public Player(Room newRoom) {
		_currentRoom = newRoom;
		_currentRoomID = newRoom.getId();
		this.setPlayerPosition(newRoom.center());
		_oldPosition = new Point(_yCoordinate, _xCoordinate);
		_hasQuit = false;
		_isStateChanged = true;
		_symbolPlayer = TextSlug.LOOKING_DOWN;
	}

	public Point getPlayerPositionPrev() {
		return _oldPosition;
	}

	public int getPlayerSymbol() {
		return _symbolPlayer;
	}

	public Point getPosition() {
		return new Point(_yCoordinate, _xCoordinate);
	}

	public int getRoomCurrentID() {
		return _currentRoomID;
	}

	public int getX() {
		return _xCoordinate;
	}

	public int getY() {
		return _yCoordinate;
	}

	public boolean hasQuit() {
		return _hasQuit;
	}

	public void hasQuit(boolean hasQuit) {
		_hasQuit = hasQuit;
	}

	@Override
	public boolean isStateChanged() {
		return _isStateChanged;
	}

	@Override
	public void isStateChanged(boolean isChanged) {
		_isStateChanged = isChanged;
	}

	public void move(Point direction) {
		_oldPosition = getPosition();
		this.setPlayerPosition(_yCoordinate + direction.getY(), _xCoordinate
				+ direction.getX());
		_isStateChanged = true;
	}

	public Point possible(Point direction) {
		return Point.translate(this.getPosition(), direction);
	}

	public void setPlayerPosition(int yCoord, int xCoord) {
		_xCoordinate = xCoord;
		_yCoordinate = yCoord;
	}

	public void setPlayerPosition(Point position) {
		_xCoordinate = position.getX();
		_yCoordinate = position.getY();
	}

	public void setPlayerPositionPrev(Point prev) {
		_oldPosition = prev;
	}

	public void setPlayerSymbol(int symbolPlayer) {
		_symbolPlayer = symbolPlayer;
	}

	public void setRoom(Room newRoom) {
		_currentRoom = newRoom;
		_currentRoomID = _currentRoom.getId();
	}

}
