package util.saver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.player.Player;
import model.region.RegionMaze;
import util.maze.MazeBuilder;

public class SaverGame {

	public SaverGame() {
	}

	public void clean() {
		new File("assets/player.ser").delete();
		new File("assets/mazeAttributes.ser").delete();
	}

	public RegionMaze loadMaze() {
		try {
			FileInputStream fileInputStream;
			fileInputStream = new FileInputStream("assets/mazeAttributes.ser");
			ObjectInputStream objectinputstream = new ObjectInputStream(
					fileInputStream);
			SaverMaze attributes = (SaverMaze) objectinputstream.readObject();
			RegionMaze maze = new SaverMazeLoader().create(attributes);
			return maze;
		} catch (Exception e) {
			RegionMaze maze = new RegionMaze(3);
			new MazeBuilder().create(maze);
			return maze;
		}
	}

	public Player loadPlayer(RegionMaze maze) {
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream("assets/player.ser");
			ObjectInputStream objectinputstream = new ObjectInputStream(
					fileInputStream);
			Player player = (Player) objectinputstream.readObject();
			player.setRoom(maze.getRoom(player.getRoomCurrentID()));
			return player;
		} catch (Exception e) {
			return new Player(maze.getRoomStart());
		}
	}

	public void save(RegionMaze maze, Player player) {
		try {
			saveMaze(maze);
			savePlayer(player);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveMaze(RegionMaze maze) throws IOException {
		SaverMaze attributes = new SaverMaze(maze);
		FileOutputStream fout;
		fout = new FileOutputStream("assets/mazeAttributes.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(attributes);
	}

	private void savePlayer(Player player) throws IOException {
		FileOutputStream fout;
		fout = new FileOutputStream("assets/player.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(player);
	}
}
