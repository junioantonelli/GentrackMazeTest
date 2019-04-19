package mazeTest;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class MazeMain {
	public static void main(String[] args) {
		MazeFormatter formatter = new MazeFormatter();
		
		// provide a runtime path to the file
//		JFileChooser chooser = new JFileChooser();
//		chooser.showOpenDialog(null);
//		File file = chooser.getSelectedFile();
//		Path path = file.toPath();

		Path path = Paths.get("src/main/resources/samples/sparse_large.txt");
		Maze maze = new Maze();
		formatter.formatMaze(path, maze);
		System.out.println(maze.getParameters());
//		System.out.println(maze.getMaze());
//		System.out.println(maze.getMaze().get(1).get(1));
	}
}
