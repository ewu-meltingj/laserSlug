package model.passage;

import model.Point;
import model.room.Room;

public class PassageVertical extends A_Passage {

	public PassageVertical(Room topRoom, Room bottomRoom) {
		super(topRoom, bottomRoom);
		_firstDoor.setOrigin(originBottom(topRoom));
		_secondDoor.setOrigin(originTop(bottomRoom));
	}

	private Point originBottom(Room room) {
		int xCoord = room.getOrigin().getX() + room.getWidth() / 2;
		int yCoord = room.getOrigin().getY() + room.getHeight() - 1;
		Point point = new Point(yCoord, xCoord);
		_passageOrigin = point;
		return point;
	}

	private Point originTop(Room room) {
		int xCoord = room.getOrigin().getX() + room.getWidth() / 2;
		int yCoord = room.getOrigin().getY();
		Point point = new Point(yCoord, xCoord);
		_passageEnd = point;
		return point;
	}
}
