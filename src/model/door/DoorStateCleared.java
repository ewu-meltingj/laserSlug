package model.door;

import model.player.Player;
import model.point.Point;
import contracts.I_HaveDoorState;

public class DoorStateCleared implements I_HaveDoorState {

	public DoorStateCleared() {}

	@Override
	public int getSymbol() {
		return "+".codePointAt(0);
	}

	@Override
	public int getSymbolSimple() {
		return 1;
	}

	@Override
	public void interact(Player player, Point direction) {
		player.move(direction);
	}
}