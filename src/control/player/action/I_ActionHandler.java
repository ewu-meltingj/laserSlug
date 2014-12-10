package control.player.action;

import model.player.Player;
import util.maze.Interactive;

public interface I_ActionHandler {
	public void handleAction(Player _player, Interactive element);
}
