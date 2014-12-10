/**    *********************************
 *     ******* Team Lazer Slugz ********
 *     *********************************
 *              
 *  This is the current state of the third
 *  iteration for Team Lazer Slugz. The code
 *  uses some more patterns to help seperate
 *  MVC elements. The ultimate goal of this 
 *  iteration is to create a game that can
 *  switch out a GUI on the fly.
 * 	
 */
package view;

import model.door.Door;
import model.maze.Maze;
import model.player.Passage;
import model.question.Question;
import model.room.Room;
import util.text.TextMaze;

import com.googlecode.blacken.grid.BlackenGrid;
import com.googlecode.blacken.grid.BlackenPoint;
import com.googlecode.blacken.terminal.TerminalInterface;
import com.googlecode.blacken.terminal.TerminalView;
import com.googlecode.blacken.terminal.TerminalViewInterface;
import com.googlecode.blacken.terminal.editing.SingleLine;

// TODO: Auto-generated Javadoc
/**
 * The Class View.
 */
public class View {

	/** The Constant MAP_START. */
	private final static BlackenPoint MAP_START = new BlackenPoint(0, 0);

	/** The _upper left. */
	private BlackenPoint _upperLeft = new BlackenPoint(0, 0);

	/** The _term. */
	private TerminalInterface _term;

	/** The _refresher. */
	private ViewRefresher _refresher;

	/** The _player. */
	private Passage _player;

	/** The _maze. */
	private Maze _maze;

	/** The _observer. */
	private ViewObserver _observer;

	/** The _gui grid. */
	private BlackenGrid<Integer> _guiGrid;

	/** The _drawer. */
	private ViewDrawer _drawer;

	/** The observer. */
	ViewObserver observer;

	/** The _question viewer. */
	ViewQuestion _questionViewer;

	/**
	 * Instantiates a new view.
	 * 
	 * @param maze
	 *            the maze
	 * @param player
	 *            the player
	 * @param terminal
	 *            the terminal
	 */
	public View(Maze maze, Passage player, TerminalInterface terminal) {
		_term = terminal;
		_player = player;
		_maze = maze;
		_observer = new ViewObserver();
		_guiGrid = new BlackenGrid<Integer>(TextMaze.EMPTY_FLOOR,
				_maze.getHeight(), _maze.getWidth());
		_drawer = new ViewDrawer(_guiGrid);
		_refresher = new ViewRefresher(_term, MAP_START, _upperLeft, _guiGrid,
				_player);
		_questionViewer = new ViewQuestion(terminal);
		initGUI();
	}

	/**
	 * Answer question.
	 * 
	 * @param question
	 *            the question
	 * @return true, if successful
	 */
	public boolean answerQuestion(Question question) {
		TerminalViewInterface view = new TerminalView(_term);
		_term.clear();
		SingleLine.putString(view, 4, 3, question.getQuestion(), 50, 0);
		String answer = SingleLine.getString(_term, 20, 3, 22, null);

		return question.checkAnswer(answer);
	}

	/**
	 * Inits the gui.
	 */
	public void initGUI() {
		_drawer.drawMaze();
		_drawer.drawPassageAll(_maze.getPassages());
		_drawer.drawRoomAll(_maze.getRooms());

		for (Room room : _maze.getRooms()) {
			for (Door door : room.getDoors()) {
				_drawer.drawDoor(door);
				_observer.registerDoor(new ViewObserveDoor(door, _drawer,
						_questionViewer));
			}
		}

		_drawer.drawPlayer(_player);
		_observer.registerPlayer(new ViewObservePlayer(_player, _drawer));
		refresh();
	}

	/**
	 * Refresh.
	 */
	public void refresh() {
		_observer.notifyAllElements();
		_refresher.refresh();
	}

}
