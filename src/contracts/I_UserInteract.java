package contracts;

import util.maze.Interactive;
import model.player.Player;
import model.point.Point;

public interface I_UserInteract {
	public void interact(Player player, Point direction);
	
	public void setBounds(Interactive active);
}
