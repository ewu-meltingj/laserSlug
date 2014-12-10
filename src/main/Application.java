package main;

import model.player.Player;
import model.region.RegionMaze;
import util.maze.Interactive;
import util.maze.MazeBuilder;
import util.maze.MazeTraverser;
import util.text.TextMessage;
import view.View;

import com.googlecode.blacken.helper.ViewerHelper;

import control.player.ControlPlayer;
import control.player.Terminal;

public class Application {

	// simple static method to check if the user completed the maze.
	public static boolean hasCompletedMaze(Player player, RegionMaze maze) {
		return maze.getRoomEnd().contains(player.getPosition());
	}

	public static void main(String[] args) {

		// create terminal
		Terminal mazeIO = new Terminal();

		//initialize maze.
		RegionMaze maze = new RegionMaze(3);
		
		//Instantiate that maze builder.
		MazeBuilder mBuilder = new MazeBuilder();

		//build the maze and capture all interactive elements.
		Interactive elements = new Interactive(mBuilder.create(maze));

		//create player and set him/her in the starting room
		Player player = new Player(maze.getRoomStart());

		// traversable utilty to check if game is traverseable.
		// it is ran each time the player moves.
		MazeTraverser trav = new MazeTraverser(maze, player);

		// create player control.
		ControlPlayer playerControl = new ControlPlayer(mazeIO, player,
				elements);

		// Show Intro Screen
		new ViewerHelper(mazeIO.getTerminal(), "Lazer Slug Trivia Maze",
				TextMessage.START_MESSAGE).run();
		
		//create the view the end user sees.
		View view = new View(maze, player, mazeIO.getTerminal());
		
		//start game loop
		while (!hasCompletedMaze(player, maze) && !player.hasQuit()
				&& maze.isTraversable()) {
			playerControl.checkCommand(); //checks user command
			view.refresh(); //refreshes 
			maze.isTraversable(trav.solveMaze()); // check if traversable
		}
		
		// show end credits
		new ViewerHelper(mazeIO.getTerminal(), "Credits",
				TextMessage.END_MESSAGE).run();
		// close the window
		mazeIO.close();
	}
}