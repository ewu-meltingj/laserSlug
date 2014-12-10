package view;

import model.door.Door;
import model.player.Player;
import model.region.RegionMaze;
import model.region.RegionRoom;
import util.text.TextMaze;
import view.drawer.Drawer;
import view.observer.ObserveDoor;
import view.observer.ObservePlayer;
import view.observer.Observer;
import view.refresher.Refresher;

import com.googlecode.blacken.grid.BlackenGrid;
import com.googlecode.blacken.grid.BlackenPoint;
import com.googlecode.blacken.terminal.TerminalInterface;

public class View {

	private final static BlackenPoint MAP_START = new BlackenPoint(0, 0);

	private BlackenPoint _upperLeft = new BlackenPoint(0, 0);

	private TerminalInterface _term;

	private Refresher _refresher;

	private Player _player;

	private RegionMaze _maze;

	private Observer _observer;

	private BlackenGrid<Integer> _guiGrid;

	private Drawer _drawer;

	Observer observer;

	public View(RegionMaze maze, Player player, TerminalInterface terminal) {
		_term = terminal;
		_player = player;
		_maze = maze;
		_observer = new Observer();
		_guiGrid = new BlackenGrid<Integer>(TextMaze.EMPTY_FLOOR,
				_maze.getHeight(), _maze.getWidth());
		_drawer = new Drawer(_guiGrid);
		_refresher = new Refresher(_term, MAP_START, _upperLeft, _guiGrid,
				_player);
		initGUI();
	}

	public void initGUI() {
		_drawer.drawMaze();
		_drawer.drawPassageAll(_maze.getPassages());
		_drawer.drawRoomAll(_maze.getRooms());

		for (RegionRoom room : _maze.getRooms()) {
			for (Door door : room.getDoors()) {
				_drawer.drawDoor(door);
				_observer.registerDoor(new ObserveDoor(door, _drawer, _term));
			}
		}

		_drawer.drawPlayer(_player);
		_observer.registerPlayer(new ObservePlayer(_player, _drawer));
		refresh();
	}

	public void refresh() {
		_observer.notifyAllElements();
		_refresher.refresh();
	}

}
