package model.passage;

import model.Point;
import model.door.Door;
import model.door.DoorStateBlocked;
import model.door.DoorStateCleared;
import model.door.DoorStateQuestion;
import model.maze.Interactable;
import model.player.Player;
import model.question.Question;
import model.room.Room;
import contracts.I_UserInteract;

public abstract class A_Passage implements I_UserInteract {

	protected Door _firstDoor;

	protected Door _secondDoor;

	protected Point _passageEnd;

	protected Point _passageOrigin;
	
	public Question _question;

	public A_Passage(Room firstRoom, Room secondRoom) {
		_firstDoor = new Door(new DoorStateQuestion());
		_firstDoor.setPassage(this);

		_secondDoor = new Door(new DoorStateQuestion());
		_secondDoor.setPassage(this);
		
		_question = new Question();
	}
	
	public Question getQuestion() {
		return _question;
	}

	public void blockDoors() {
		_firstDoor.setDoorState(new DoorStateBlocked());
		_secondDoor.setDoorState(new DoorStateBlocked());
	}

	public void clearDoors() {
		_firstDoor.setDoorState(new DoorStateCleared());
		_secondDoor.setDoorState(new DoorStateCleared());
	}

	public Door getDoorFirst() {
		return _firstDoor;
	}

	public Door getDoorSecond() {
		return _secondDoor;
	}

	public Door getDoorSibling(Door door) {
		if (!_firstDoor.equals(door))
			return _firstDoor;
		return _secondDoor;
	}

	public Point getEnd() {
		return _passageEnd;
	}

	public int getHeight() {
		return _passageEnd.getY() - _passageOrigin.getY();
	}

	public Point getOrigin() {
		return _passageOrigin;
	}

	public int getWidth() {
		return _passageEnd.getX() - _passageOrigin.getX();
	}

	@Override
	public void interactWith(Player player, Point direction) {
		player.move(direction);
	}

	@Override
	public void setInteractableBounds(Interactable active) {
		Point origin = _passageOrigin;
		Point end = _passageEnd;
		int lenthHorizontal = end.getX() - origin.getX();
		int lenthVertical = end.getY() - origin.getY();

		for (int y = 0; y < lenthVertical; y++)
			active.put(new Point(y + origin.getY(), origin.getX()), this);

		for (int x = 1; x < lenthHorizontal; x++)
			active.put(new Point(origin.getY(), origin.getX() + x), this);
	}

}
