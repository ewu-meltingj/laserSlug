package model.passage;

import model.point.Point;
import model.region.RegionRoom;

public class PassageVertical extends A_Passage {

	public PassageVertical(RegionRoom topRoom, RegionRoom bottomRoom) {
		super(topRoom, bottomRoom);
		_firstDoor.setOrigin(originBottom(topRoom));
		_secondDoor.setOrigin(originTop(bottomRoom));
	}

	private Point originBottom(RegionRoom room) {
		int xCoord = room.getOrigin().getX() + room.getWidth() / 2;
		int yCoord = room.getOrigin().getY() + room.getHeight() - 1;
		Point point = new Point(yCoord, xCoord);
		_passageOrigin = point;
		return point;
	}

	private Point originTop(RegionRoom room) {
		int xCoord = room.getOrigin().getX() + room.getWidth() / 2;
		int yCoord = room.getOrigin().getY();
		Point point = new Point(yCoord, xCoord);
		_passageEnd = point;
		return point;
	}
}
