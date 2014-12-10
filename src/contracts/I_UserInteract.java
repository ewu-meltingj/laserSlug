package contracts;

import model.Point;
import model.maze.Interactable;
import model.player.Player;

public interface I_UserInteract {
	public void interact(Player player, Point direction);
	
	public void setBounds(Interactable active);
}
