package model.region;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import util.maze.Interactive;

import model.door.Door;
import model.player.Player;
import model.point.Point;
import contracts.I_UserInteract;

public class RegionRoom extends A_Region implements Serializable,
		I_UserInteract {

	private static final long serialVersionUID = -6898900693260805420L;

	private int _id;

	private transient List<Door> _doorList;

	public RegionRoom(int id, Point origin, int width, int height) {
		super(height, width, origin, id);
		_id = id;
		_doorList = new ArrayList<Door>();
	}

	public void addDoor(Door door) {
		_doorList.add(door);
	}

	public List<Door> getDoors() {
		return _doorList;
	}

	@Override
	public void interact(Player player, Point direction) {
			player.move(direction);
	}

	private void readObject(ObjectInputStream stream) throws IOException,
			ClassNotFoundException {
		stream.defaultReadObject();
		_doorList = new ArrayList<Door>();
	}

	@Override
	public String toString() {
		return "ID:" + _id + " Height:" + _height + " Width:" + _width;
	}

	@Override
	public void setBounds(Interactive active) {
		int startY = _origin.getY() + 1;
		int startX = _origin.getX() + 1;
		int endY = startY + _height - 2;
		int endX = startX + _width - 2;
		for(int y = startY; y < endY; y++)
			for(int x = startX; x < endX; x++)
				active.put(new Point(y, x), this);
	}
}
