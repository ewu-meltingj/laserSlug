package main;
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


import model.maze.Interactable;
import model.maze.Maze;
import model.player.Passage;
import user.control.Controller;
import user.control.Terminal;
import util.maze.MazeBuilder;
import util.maze.MazeTraverser;
import util.text.TextMessage;
import view.View;

import com.googlecode.blacken.helper.ViewerHelper;

/**
 * The Class Application.
 */
public class Application {

	/**
	 * Checks for completed maze.
	 * 
	 * @param player
	 *            the player
	 * @param maze
	 *            the maze
	 * @return true, if successful
	 */
	public static boolean hasCompletedMaze(Passage player, Maze maze) {
		return maze.getRoomEnd().contains(player.getPosition());
	}

	/**
	 * The main method. -Firstly it create a user terminal to handle both input.
	 * 
	 * -Secondly it creates a maze that is 3x3. It can be changed on the fly.
	 * There is included a Question Manager that can will set the max possible
	 * rooms depending on question amount.
	 * 
	 * -Thirdly the maze is created with the MazeBuilder and all of the
	 * interactable elements are extracted into a custom class which is just a
	 * hash map behind the scenes. The key value is the current coordinate that
	 * the player is on. This way it is possible to find out if an object is
	 * interactable with a timecost(1).
	 * 
	 * -Fourthly the Maze traversal object is created by using each
	 * elements[origin, width, height]. It will run with each player move to see
	 * if the maze is traversable.
	 * 
	 * -Fifthly the player controller is created. Each key used in the
	 * application is mapped to a unique object which calls the interactable
	 * object to handle user choices.
	 * 
	 * -Sixthly A welcome screen is displayed.
	 * 
	 * -Seventhly the assigned game logic happens. -Lastly the player either won
	 * or lost and another splash screen is displayed to inform the player.
	 */
	public static void main(String[] args) {

		Terminal userIO = new Terminal();

		Maze maze = new Maze(3);

		Interactable elements = new Interactable(MazeBuilder.create(maze));

		Passage player = new Passage(maze.getRoomStart());

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