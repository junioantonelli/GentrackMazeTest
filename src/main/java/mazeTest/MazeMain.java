package mazeTest;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class MazeMain {
	public static void main(String[] args) {
		Path pathToTheMaze = null;

		Scanner reader = new Scanner(System.in);

		String menu = "Welcome to Maze Solver\n"
						+ "Type 1 to choose the maze file\n"
						+ "Type 2 to solve it\n"
						+ "Type 3 to exit";
		System.out.println(menu);
		int n = reader.nextInt();

		while (true) {
			switch (n) {
				case 3:
					System.out.println("Good bye!");
					System.exit(0);
				case 1:
					JFileChooser chooser = new JFileChooser();
					chooser.showOpenDialog(null);
					File file = chooser.getSelectedFile();
					if(file == null) {
						System.out.println("Please select a file");
						break;
					}
					pathToTheMaze = file.toPath();
					break;
				case 2:
					MazeFormatter formatter = new MazeFormatter();
					// pathToTheMaze = Paths.get("src/main/resources/samples/large_input_unsolveable.txt");
					if (pathToTheMaze == null) {
						System.out.println("Please select a file");
						break;
					}
					formatter.setPathToTheMaze(pathToTheMaze);
					Maze maze = formatter.formatMaze();
					BFS bfs = new BFS(maze);
					// ParallelBFS pbfs = new ParallelBFS(maze);
					long time1 = System.currentTimeMillis();
					formatter.setPath(bfs.solve());
					// pbfs.solve();
					long time2 = System.currentTimeMillis();
					System.out.println("Solved in " + (time2 - time1) / 1000.0 + " seconds.");
					String solution = formatter.formatSolution();
					System.out.println(solution);
					break;
				default:
					System.out.println("Please type a valid option");
					break;
			}
			n = reader.nextInt();
		}


	}
}
