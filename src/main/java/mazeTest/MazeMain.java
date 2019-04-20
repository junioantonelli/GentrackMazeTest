package mazeTest;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class MazeMain {
	public static void main(String[] args) {
		
		// provide a runtime path to the file
//		JFileChooser chooser = new JFileChooser();
//		chooser.showOpenDialog(null);
//		File file = chooser.getSelectedFile();
//		Path path = file.toPath();

		MazeFormatter formatter = new MazeFormatter();
		Path path = Paths.get("src/main/resources/samples/small_wrap_input.txt");
		Maze maze = formatter.formatMaze(path);
		MazeRunner runner = new MazeRunner(maze);
		runner.solveMaze();
	}
}
