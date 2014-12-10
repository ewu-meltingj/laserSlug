package main;

import model.maze.Interactable;
import model.maze.Maze;
import model.player.Player;
import user.control.Controller;
import user.control.Terminal;
import util.maze.MazeBuilder;
import util.maze.MazeTraverser;
import util.text.TextMessage;
import view.View;

import com.googlecode.blacken.helper.ViewerHelper;

public class Application {

	public static boolean hasCompletedMaze(Player player, Maze maze) {
		return maze.getRoomEnd().contains(player.getPosition());
	}

	public static void main(String[] args) {

		Terminal userIO = new Terminal();

		Maze maze = new Maze(3);

		Interactable elements = new Interactable(MazeBuilder.create(maze));

		Player player = new Player(maze.getRoomStart());

		MazeTraverser trav = new MazeTraverser(maze, player);

		Controller playerControl = new Controller(userIO, player, elements);

		new ViewerHelper(userIO.getTerminal(), "Lazer Slug Trivia Maze",
				TextMessage.START_MESSAGE).run();

		View view = new View(maze, player, userIO.getTerminal());

		while (!hasCompletedMaze(player, maze) && !player.hasQuit()
				&& maze.isTraversable()) {
			playerControl.checkCommand();
			view.refresh();
			maze.isTraversable(trav.solveMaze());
		}

		new ViewerHelper(userIO.getTerminal(), "Credits",
				TextMessage.END_MESSAGE).run();

		userIO.close();
	}
}