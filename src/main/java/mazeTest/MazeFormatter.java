package mazeTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MazeFormatter {

	public MazeFormatter() {}

	public Maze formatMaze(Path path) {

		List<int[]> param_temp = new ArrayList<int[]>();
		List<String[]> maze_temp = new ArrayList<String[]>();
		Maze maze = new Maze();

		try {

			Files.lines(path)
							.map(line -> line.split(" "))
							.filter(line -> line.length < 3)
							.map(line -> {
								int[] result = new int[line.length];
								for (int i = 0;i<line.length;i++) {
									result[i] = Integer.parseInt(line[i]);
								}
								return result;
							})
							.forEach(line -> param_temp.add(line));

			Files.lines(path)
							.map(line -> line.split(" "))
							.filter(line -> line.length > 2)
							.forEach(line -> maze_temp.add(line));

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		


		int[] dimensions = param_temp.get(0);
		int[] start = param_temp.get(1);
		int[] end = param_temp.get(2);
		
		String[][] maze_def = new String[dimensions[0]][dimensions[1]];
		for(int i = 0;i<dimensions[0];i++) {
			for(int j = 0; j<dimensions[1]; j++) {
				maze_def[i][j] = maze_temp.get(i)[j];
			}
		}

		maze.setDimensions(dimensions);
		maze.setStart(start);
		maze.setEnd(end);
		maze.setMaze(maze_def);
		
		return maze;
	}
}
