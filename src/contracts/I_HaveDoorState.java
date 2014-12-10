package contracts;

import model.Point;
import model.door.Door;
import model.player.Player;

public interface I_HaveDoorState {

	public int getSymbol();

	public int getSymbolSimple();
	
	public void interact(Player player, Point direction, Door parentDoor);
}
