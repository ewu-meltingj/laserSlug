package view;

import model.door.Door;
import model.maze.Maze;
import model.player.Player;
import model.question.Question;
import model.room.Room;
import util.text.TextMaze;

import com.googlecode.blacken.grid.BlackenGrid;
import com.googlecode.blacken.grid.BlackenPoint;
import com.googlecode.blacken.terminal.TerminalInterface;
import com.googlecode.blacken.terminal.TerminalView;
import com.googlecode.blacken.terminal.TerminalViewInterface;
import com.googlecode.blacken.terminal.editing.SingleLine;

public class View {

	private final static BlackenPoint MAP_START = new BlackenPoint(0, 0);

	private BlackenPoint _upperLeft = new BlackenPoint(0, 0);

	private TerminalInterface _term;

	private ViewRefresher _refresher;

	private Player _player;

	private Maze _maze;

	private ViewObserver _observer;

	private BlackenGrid<Integer> _guiGrid;

	private ViewDrawer _drawer;

	ViewObserver observer;
	
	ViewQ _questionViewer;

	public View(Maze maze, Player player, TerminalInterface terminal) {
		_term = terminal;
		_player = player;
		_maze = maze;
		_observer = new ViewObserver();
		_guiGrid = new BlackenGrid<Integer>(TextMaze.EMPTY_FLOOR,
				_maze.getHeight(), _maze.getWidth());
		_drawer = new ViewDrawer(_guiGrid);
		_refresher = new ViewRefresher(_term, MAP_START, _upperLeft, _guiGrid,
				_player);
		_questionViewer = new ViewQ(terminal);
		initGUI();
	}
	
	public boolean answerQuestion(Question question) {
		TerminalViewInterface view = new TerminalView(_term);
		_term.clear();
		SingleLine.putString(view, 4, 3, question.getQuestion(), 50, 0);
		String answer = SingleLine.getString(_term, 20, 3, 22, null);

		return question.checkAnswer(answer);
	}

	public void initGUI() {
		_drawer.drawMaze();
		_drawer.drawPassageAll(_maze.getPassages());
		_drawer.drawRoomAll(_maze.getRooms());

		for (Room room : _maze.getRooms()) {
			for (Door door : room.getDoors()) {
				_drawer.drawDoor(door);
				_observer
						.registerDoor(new ViewObserveDoor(door, _drawer, _questionViewer));
			}
		}

		_drawer.drawPlayer(_player);
		_observer.registerPlayer(new ViewObservePlayer(_player, _drawer));
		refresh();
	}

	public void refresh() {
		_observer.notifyAllElements();
		_refresher.refresh();
	}

}
