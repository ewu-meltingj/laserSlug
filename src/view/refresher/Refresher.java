package view.refresher;

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

public class Refresher {

	private TerminalInterface _term;

	private BlackenPoint _mapStart;

	private BlackenPoint _upperLeft;

	private BlackenGrid<Integer> _grid;

	private float _noisePlane;

	private Random rand;

	private Player _player;

	public Refresher(TerminalInterface term, BlackenPoint mapStart,
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

	private boolean isFunElement(int character) {
		if (character == TextSpecial.SPECIAL_GRAVE)
			return true;
		return false;
	}

	private boolean isInBounds(int y1, int x1) {
		if (y1 >= 0 && x1 >= 0 && y1 < _grid.getHeight()
				&& x1 < _grid.getWidth())
			return true;
		return false;
	}

	private boolean isPlayer(int character) {
		if (character == TextSlug.LOOKING_UP
				|| character == TextSlug.LOOKING_DOWN
				|| character == TextSlug.LOOKING_LEFT
				|| character == TextSlug.LOOKING_RIGHT)
			return true;
		return false;
	}

	public void recenterMap() {
		_upperLeft.setY(_player.getY() - (_term.getHeight() - 2) / 2);
		_upperLeft.setX(_player.getX() - (_term.getWidth() - 2) / 2);
	}

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
