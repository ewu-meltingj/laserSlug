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

import java.util.List;
import java.util.Random;

import model.Coordinate;
import model.door.Door;
import model.passage.A_Passage;
import model.player.Player;
import model.room.Room;
import util.text.TextMaze;
import util.text.TextSpecial;

import com.googlecode.blacken.grid.BlackenGrid;
import com.googlecode.blacken.grid.BlackenPoint;

// TODO: Auto-generated Javadoc
/**
 * The Class ViewDrawer.
 */
public class ViewDrawer {

	/** The _b grid. */
	private BlackenGrid<Integer> _bGrid;

	/** The _old block. */
	private Integer _oldBlock = TextMaze.FLOOR; // feels like code smell

	/**
	 * Instantiates a new view drawer.
	 * 
	 * @param bGrid
	 *            the b grid
	 */
	public ViewDrawer(BlackenGrid<Integer> bGrid) {
		_bGrid = bGrid;
	}

	/**
	 * Draw door.
	 * 
	 * @param door
	 *            the door
	 */
	public void drawDoor(Door door) {
		Coordinate origin = door.getOrigin();
		_bGrid.set(origin.getY(), origin.getX(), door.getSymbol());
	}

	/**
	 * Draw door all.
	 * 
	 * @param doorList
	 *            the door list
	 */
	public void drawDoorAll(List<Door> doorList) {
		for (Door door : doorList)
			drawDoor(door);
	}

	/**
	 * Draw maze.
	 */
	public void drawMaze() {
		_bGrid.box(_bGrid.getHeight(), _bGrid.getWidth(), 0, 0,
				TextMaze.MAZE_WALL_LEFT, TextMaze.MAZE_WALL_RIGHT,
				TextMaze.MAZE_WALL_TOP, TextMaze.MAZE_WALL_BOTTOM,
				TextMaze.MAZE_WALL_TOP_LEFT, TextMaze.MAZE_WALL_TOP_RIGHT,
				TextMaze.MAZE_WALL_BOTTOM_LEFT,
				TextMaze.MAZE_WALL_BOTTOM_RIGHT, TextMaze.EMPTY_FLOOR);
		drawMazeRandoms();
	}

	/**
	 * Draw maze randoms.
	 */
	private void drawMazeRandoms() {
		drawMazeRandoms(TextSpecial.SPECIAL_GRAVE);
		drawMazeRandoms(TextSpecial.SPECIAL_FLOWER);
	}

	/**
	 * Draw maze randoms.
	 * 
	 * @param what
	 *            the what
	 */
	private void drawMazeRandoms(int what) {
		int randElements = 15;
		Random rand = new Random();
		int numberElements = 0;
		int[] placement = { -1, -1 };
		for (int t = 0; t < _bGrid.getHeight() * _bGrid.getWidth()
				&& numberElements < randElements; t++) {
			int x = rand.nextInt(_bGrid.getWidth());
			int y = rand.nextInt(_bGrid.getHeight());
			if (_bGrid.get(y, x) == TextMaze.EMPTY_FLOOR) {
				_bGrid.set(y, x, new Integer(what));
				placement[0] = y;
				placement[1] = x;
				numberElements++;
			}
		}
	}

	/**
	 * Draw passage.
	 * 
	 * @param passage
	 *            the passage
	 */
	public void drawPassage(A_Passage passage) {
		Coordinate pointOrigin = passage.getOrigin();
		Coordinate pointEnd = passage.getEnd();

		int lenthHorizontal = pointEnd.getX() - pointOrigin.getX();
		int lenthVertical = pointEnd.getY() - pointOrigin.getY();

		for (int y = 0; y < lenthVertical; y++)
			_bGrid.set(y + pointOrigin.getY(), pointOrigin.getX(),
					TextMaze.FLOOR_HALL);

		for (int x = 0; x < lenthHorizontal; x++)
			_bGrid.set(pointOrigin.getY(), pointOrigin.getX() + x,
					TextMaze.FLOOR_HALL);
	}

	/**
	 * Draw passage all.
	 * 
	 * @param passageList
	 *            the passage list
	 */
	public void drawPassageAll(List<A_Passage> passageList) {
		for (A_Passage passage : passageList)
			drawPassage(passage);
	}

	/**
	 * Draw player.
	 * 
	 * @param player
	 *            the player
	 */
	public void drawPlayer(Player player) {
		BlackenPoint playerPointCurr = new BlackenPoint(player.getPosition()
				.getY(), player.getPosition().getX());
		BlackenPoint playerPointPrev = new BlackenPoint(player
				.getPlayerPositionPrev().getY(), player.getPlayerPositionPrev()
				.getX());

		_bGrid.set(playerPointPrev, _oldBlock);
		_oldBlock = _bGrid.get(playerPointCurr);
		_bGrid.set(playerPointCurr, player.getPlayerSymbol());
		player.setPlayerPositionPrev(player.getPosition());
	}

	/**
	 * Draw room.
	 * 
	 * @param room
	 *            the room
	 */
	public void drawRoom(Room room) {
		BlackenGrid<Integer> roomGrid = new BlackenGrid<Integer>(0,
				room.getHeight(), room.getWidth(), room.getOrigin().getY(),
				room.getOrigin().getX());
		roomGrid.box(room.getHeight(), room.getWidth(),
				room.getOrigin().getY(), room.getOrigin().getX(),
				TextMaze.ROOM_WALL_LEFT, TextMaze.ROOM_WALL_RIGHT,
				TextMaze.ROOM_WALL_TOP, TextMaze.ROOM_WALL_BOTTOM,
				TextMaze.ROOM_WALL_TOP_LEFT, TextMaze.ROOM_WALL_TOP_RIGHT,
				TextMaze.ROOM_WALL_BOTTOM_LEFT,
				TextMaze.ROOM_WALL_BOTTOM_RIGHT, TextMaze.FLOOR);
		_bGrid.addGrid(roomGrid);
	}

	/**
	 * Draw room all.
	 * 
	 * @param roomList
	 *            the room list
	 */
	public void drawRoomAll(List<Room> roomList) {
		for (Room room : roomList)
			drawRoom(room);
	}
}
