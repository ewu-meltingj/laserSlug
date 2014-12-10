package model.passage;

import model.Point;
import model.room.Room;

public class PassageHorizontal extends A_Passage {

	public PassageHorizontal(Room leftRoom, Room rightRoom) {
		super(leftRoom, rightRoom);
		_firstDoor.setOrigin(originRight(leftRoom));
		_secondDoor.setOrigin(originLeft(rightRoom));
	}

	private Point originLeft(Room room) {
		int xCoord = room.getOrigin().getX();
		int yCoord = room.getOrigin().getY() + room.getHeight() / 2;
		Point point = new Point(yCoord, xCoord);
		_passageEnd = point;
		return point;
	}

	private Point originRight(Room room) {
		int xCoord = room.getOrigin().getX() + room.getWidth() - 1;
		int yCoord = room.getOrigin().getY() + room.getHeight() / 2;
		Point point = new Point(yCoord, xCoord);
		_passageOrigin = point;
		return point;
	}

}
