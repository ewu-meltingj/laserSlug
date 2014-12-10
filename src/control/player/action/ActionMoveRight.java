package control.player.action;

import model.player.Player;
import model.point.Point;
import util.maze.Interactive;
import util.text.TextSlug;

public class ActionMoveRight implements I_ActionHandler {

	private static final Point MOVE_RIGHT = new Point(0, +1);

	@Override
	public void handleAction(Player player, Interactive _element) {
		player.setPlayerSymbol(TextSlug.LOOKING_RIGHT);
		if (_element.isInteractive(player, MOVE_RIGHT))
			_element.interactWith(player, MOVE_RIGHT);
	};
}
