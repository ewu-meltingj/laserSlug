package contracts;

import model.player.Player;
import model.point.Point;

public interface I_HaveDoorState {

	public int getSymbol();

	public int getSymbolSimple();

	public void interact(Player player, Point direction);
}
