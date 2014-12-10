package util.maze;

import java.util.HashMap;
import java.util.Map;

import model.player.Player;
import model.point.Point;
import contracts.I_UserInteract;

public class Interactive {
	private Map<Point, I_UserInteract> _interactiveGrid;

	public Interactive() {
		_interactiveGrid = new HashMap<Point, I_UserInteract>();
	}

	public Interactive(Interactive interactive) {
		_interactiveGrid = interactive.getInteractiveGrid();
	}

	public void addToMap(I_UserInteract active) {
		active.setBounds(this);
	}
	
	public void put(Point point, I_UserInteract active) {
		_interactiveGrid.put(point, active);
	}

	public void addToMapAll(I_UserInteract[] active) {
		for (int i = 0; i < active.length; i++)
			addToMap(active[i]);
	}

	public Map<Point, I_UserInteract> getInteractiveGrid() {
		return _interactiveGrid;
	}

	public void interactWith(Player player, Point direction) {
		Point possibleMove = Point.translate(player.getPosition(), direction);
		_interactiveGrid.get(possibleMove).interact(player, direction);
	}

	public boolean isInteractive(Player player, Point direction) {
		Point possibleMove = player.possible(direction);
		return _interactiveGrid.containsKey(possibleMove);
	}
}
