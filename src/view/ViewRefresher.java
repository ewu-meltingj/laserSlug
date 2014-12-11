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

import java.util.EnumSet;

import model.player.Player;
import util.text.TextMaze;
import util.text.TextSlug;
import util.text.TextSpecial;

import com.googlecode.blacken.core.Random;
import com.googlecode.blacken.extras.PerlinNoise;
import com.googlecode.blacken.grid.BlackenGrid;
import com.googlecode.blacken.grid.BlackenPoint;
import com.googlecode.blacken.terminal.CellWalls;
import com.googlecode.blacken.terminal.TerminalInterface;
import com.googlecode.blacken.terminal.TerminalStyle;

// TODO: Auto-generated Javadoc
/**
 * The Class ViewRefresher refreshes a changed state and makes the terminal look
 * super fancy.
 */
public class ViewRefresher {

	/** The _term. */
	private TerminalInterface _term;

	/** The _map start. */
	private BlackenPoint _mapStart;

	/** The _upper left. */
	private BlackenPoint _upperLeft;

	/** The _grid. */
	private BlackenGrid<Integer> _grid;

	/** The _noise plane. */
	private float _noisePlane;

	/** The rand. */
	private Random rand;

	/** The _player. */
	private Player _player;

	/**
	 * Instantiates a new view refresher.
	 * 
	 * @param term
	 *            the term
	 * @param mapStart
	 *            the map start
	 * @param upperLeft
	 *            the upper left
	 * @param grid
	 *            the grid
	 * @param player
	 *            the player
	 */
	public ViewRefresher(TerminalInterface term, BlackenPoint mapStart,
			BlackenPoint upperLeft, BlackenGrid<Integer> grid, Player player) {

		if (term == null || mapStart == null || upperLeft == null
				|| grid == null)
			throw new RuntimeException(
					"uninitialized values given to MapResfresher");
		_term = term;
		_mapStart = mapStart;
		_upperLeft = upperLeft;
		_player = player;
		_grid = grid;
		rand = new Random();
		_noisePlane = rand.nextFloat();
	}

	/**
	 * Checks if is fun element.
	 * 
	 * @param character
	 *            the character
	 * @return true, if is fun element
	 */
	private boolean isFunElement(int character) {
		if (character == TextSpecial.SPECIAL_GRAVE)
			return true;
		return false;
	}

	/**
	 * Checks if is in bounds.
	 * 
	 * @param y1
	 *            the y1
	 * @param x1
	 *            the x1
	 * @return true, if is in bounds
	 */
	private boolean isInBounds(int y1, int x1) {
		if (y1 >= 0 && x1 >= 0 && y1 < _grid.getHeight()
				&& x1 < _grid.getWidth())
			return true;
		return false;
	}

	/**
	 * Checks if is player.
	 * 
	 * @param character
	 *            the character
	 * @return true, if is player
	 */
	private boolean isPlayer(int character) {
		if (character == TextSlug.LOOKING_UP
				|| character == TextSlug.LOOKING_DOWN
				|| character == TextSlug.LOOKING_LEFT
				|| character == TextSlug.LOOKING_RIGHT)
			return true;
		return false;
	}

	/**
	 * Recenter map.
	 */
	public void recenterMap() {
		_upperLeft.setY(_player.getY() - (_term.getHeight() - 2) / 2);
		_upperLeft.setX(_player.getX() - (_term.getWidth() - 2) / 2);
	}

	/**
	 * Refresh.
	 */
	public void refresh() {
		recenterMap();
		int baseColor = 0x09;
		int colorForeground;
		int colorBackground;
		_term.getPalette().rotate(baseColor, 10, 3);
		int endY = _term.getHeight();
		int endX = _term.getWidth();
		for (int y = _mapStart.getY(); y < endY; y++) {
			for (int x = _mapStart.getX(); x < endX; x++) {
				int y1 = y + _upperLeft.getY() - _mapStart.getY();
				int x1 = x + _upperLeft.getX() - _mapStart.getX();
				int gridCharacter = ' ';
				if (isInBounds(y1, x1))
					gridCharacter = _grid.get(y1, x1);
				colorForeground = 3;
				colorBackground = 0;
				EnumSet<CellWalls> walls = EnumSet.noneOf(CellWalls.class);
				if (gridCharacter == TextMaze.EMPTY_FLOOR)
					colorForeground = (int) (Math.floor(PerlinNoise.noise(x1,
							y1, _noisePlane) * 3.0F)) + baseColor;
				else if (isFunElement(gridCharacter))
					colorForeground = 3;
				else if (isPlayer(gridCharacter)) {
					colorForeground = 0;
					colorBackground = 6;
				}
				_term.set(y, x, new String(Character.toChars(gridCharacter)),
						colorForeground, colorBackground,
						EnumSet.noneOf(TerminalStyle.class), walls);
			}
		}
	}
}
