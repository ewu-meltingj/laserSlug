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
package util.maze;

import model.Coordinate;
import model.door.Door;
import model.maze.Maze;
import model.passage.A_Passage;
import model.player.Player;
import model.room.Room;

/**
 * The Class MazeTraverser was found within seconds of looking online.
 * http://en.wikipedia.org/wiki/Maze_solving_algorithm I added some additional
 * methods so that it can draw the maze given origin, width, and height.
 */
public class MazeTraverser {

	/** The _maze to check. */
	private int[][] _mazeToCheck;

	/** The _was here. */
	private boolean[][] _wasHere;

	/** The _maze. */
	private Maze _maze;

	/** The _maze height. */
	private int _mazeHeight;

	/** The _maze width. */
	private int _mazeWidth;

	/** The _player. */
	private Player _player;

	/** The _start. */
	private Coordinate _start;

	/** The _end. */
	private Coordinate _end;

	/**
	 * Instantiates a new maze traverser.
	 * 
	 * @param maze
	 *            the maze
	 * @param player
	 *            the player
	 */
	public MazeTraverser(Maze maze, Player player) {
		_maze = maze;
		_mazeHeight = _maze.getHeight();
		_mazeWidth = _maze.getWidth();
		_mazeToCheck = new int[_mazeHeight][_mazeWidth];
		_wasHere = new boolean[_mazeHeight][_mazeWidth];
		_player = player;
		_start = _player.getPosition();
		_end = maze.getRoomEnd().center();
		drawMaze();
		drawPassages();
	}

	/**
	 * Draw maze.
	 */
	private void drawMaze() {
		for (int y = 0; y < _mazeHeight; y++)
			for (int x = 0; x < _mazeWidth; x++)
				_mazeToCheck[y][x] = 2;
	}

	/**
	 * Draw passages.
	 */
	private void drawPassages() {
		for (A_Passage passage : _maze.getPassages()) {

			Coordinate origin = passage.getOrigin();
			Coordinate end = passage.getEnd();
			int lenthHorizontal = end.getX() - origin.getX();
			int lenthVertical = end.getY() - origin.getY();

			for (int y = 0; y < lenthVertical; y++)
				_mazeToCheck[y + origin.getY()][origin.getX()] = 1;

			for (int x = 0; x < lenthHorizontal; x++)
				_mazeToCheck[origin.getY()][origin.getX() + x] = 1;
		}
	}

	/**
	 * Draw rooms and doors.
	 */
	private void drawRoomsAndDoors() {
		for (Room room : _maze.getRooms()) {
			int yRoomOrigin = room.getOrigin().getY();
			int xRoomOrigin = room.getOrigin().getX();
			for (int y = 0; y < room.getHeight(); y++)
				for (int x = 0; x < room.getWidth(); x++)
					_mazeToCheck[y + yRoomOrigin][x + xRoomOrigin] = 1;

			for (Door door : room.getDoors()) {
				int yDoorOrigin = door.getOrigin().getY();
				int xDoorOrigin = door.getOrigin().getX();
				_mazeToCheck[yDoorOrigin][xDoorOrigin] = door.getSymbolSimple();
			}
		}
	}

	/**
	 * Inits the room and door drawer
	 */
	private void init() {
		drawRoomsAndDoors();
	}

	/**
	 * Prints the maze.
	 */
	public void printMaze() {
		for (int y = 0; y < _mazeHeight; y++) {
			for (int x = 0; x < _mazeWidth; x++)
				System.out.print(_mazeToCheck[y][x]);
			System.out.println("");
		}
		System.out.println("\n");
	}

	/**
	 * Recursive solve.
	 * 
	 * @param y
	 *            the y coordinate starting position.
	 * @param x
	 *            the x coordinate starting position.
	 * @return true, if successful the game will continue
	 */
	public boolean recursiveSolve(int y, int x) {
		if (y == _end.getY() && x == _end.getX())
			return true;
		if (_mazeToCheck[y][x] == 2 || _wasHere[y][x])
			return false;
		_wasHere[y][x] = true;
		if (x != 0)
			if (recursiveSolve(y - 1, x)) {
				return true;
			}
		if (x != _mazeWidth - 1)
			if (recursiveSolve(y + 1, x)) {
				return true;
			}
		if (y != 0)
			if (recursiveSolve(y, x - 1)) {
				return true;
			}
		if (y != _mazeHeight - 1)
			if (recursiveSolve(y, x + 1)) {
				return true;
			}
		return false;
	}

	/**
	 * Solve maze.
	 * 
	 * @return true, if successful the game will continue
	 */
	public boolean solveMaze() {
		init();
		for (int row = 0; row < _mazeHeight; row++)
			for (int col = 0; col < _mazeWidth; col++)
				_wasHere[row][col] = false;
		return recursiveSolve(_start.getY(), _start.getX());
	}
}
