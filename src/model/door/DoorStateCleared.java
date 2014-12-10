package model.door;

import model.Point;
import model.player.Player;
import contracts.I_HaveDoorState;

public class DoorStateCleared implements I_HaveDoorState {

	@Override
	public int getSymbol() {
		return "+".codePointAt(0);
	}

	@Override
	public int getSymbolSimple() {
		return 1;
	}

	@Override
	public void interact(Player player, Point direction, Door parentDoor) {
		player.move(direction);
	}
}