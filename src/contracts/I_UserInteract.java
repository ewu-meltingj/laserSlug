package contracts;

import model.Point;
import model.maze.Interactable;
import model.player.Player;

public interface I_UserInteract {
	public void interactWith(Player player, Point direction);

	public void setInteractableBounds(Interactable active);
}
