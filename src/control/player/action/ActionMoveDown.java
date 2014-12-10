package control.player.action;

import model.player.Player;
import model.point.Point;
import util.maze.Interactive;
import util.text.TextSlug;

public class ActionMoveDown implements I_ActionHandler {

	private static final Point MOVE_DOWN = new Point(+1, 0);

	@Override
	public void handleAction(Player player, Interactive _element) {
		player.setPlayerSymbol(TextSlug.LOOKING_DOWN);
		if (_element.isInteractive(player, MOVE_DOWN))
			_element.interactWith(player, MOVE_DOWN);
	}
}
