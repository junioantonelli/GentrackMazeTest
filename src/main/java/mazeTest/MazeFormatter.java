package mazeTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MazeFormatter {

	LinkedList<Coordinate>	path;
	Path					pathToTheMaze;
	Maze					maze;

	public void setPathToTheMaze(Path pathToTheMaze) {
		this.pathToTheMaze = pathToTheMaze;
	}

	public LinkedList<Coordinate> getPath() {
		return path;
	}

	public void setPath(LinkedList<Coordinate> path) {
		this.path = path;
	}

	public Maze formatMaze() {

		List<int[]> param_temp = new ArrayList<int[]>();
		List<String[]> maze_temp = new ArrayList<String[]>();
		this.maze = new Maze();

		try {

			Files.lines(pathToTheMaze)
							.map(line -> line.split(" "))
							.filter(line -> line.length < 3)
							.map(line -> {
								int[] result = new int[line.length];
								for (int i = 0; i < line.length; i++) {
									result[i] = Integer.parseInt(line[i]);
								}
								return result;
							})
							.forEach(line -> param_temp.add(line));

			Files.lines(pathToTheMaze)
							.map(line -> line.split(" "))
							.filter(line -> line.length > 2)
							.forEach(line -> maze_temp.add(line));

		} catch (IOException ex) {
			ex.printStackTrace();
		}


		// arrange vectors to (row, column) format
		int[] dimensions = new int[] {param_temp.get(0)[1], param_temp.get(0)[0]};
		int[] start = new int[] {param_temp.get(1)[1], param_temp.get(1)[0]};
		int[] end = new int[] {param_temp.get(2)[1], param_temp.get(2)[0]};

		String[][] maze_def = new String[dimensions[0]][dimensions[1]];
		for (int i = 0; i < dimensions[0]; i++) {
			for (int j = 0; j < dimensions[1]; j++) {
				maze_def[i][j] = maze_temp.get(i)[j];
			}
		}

		maze.setDimensions(new Coordinate(dimensions[0], dimensions[1]));
		maze.setStart(new Coordinate(start[0], start[1]));
		maze.setEnd(new Coordinate(end[0], end[1]));
		maze.setMaze(maze_def);

		return maze;
	}

	public String formatSolution() {
		StringBuffer buffer = new StringBuffer();
		if (path != null) {
			for (Coordinate c : path) {
				this.maze.maze[c.getRow()][c.getColumn()] = "X";
			}
			
			this.maze.maze[maze.getStart().getRow()][maze.getStart().getColumn()]="S";
			this.maze.maze[maze.getEnd().getRow()][maze.getEnd().getColumn()]="E";

			for (int i = 0; i < this.maze.dimensions.getRow(); i++) {
				for (int j = 0; j < this.maze.dimensions.getColumn(); j++) {
					String str = this.maze.maze[i][j];
					if (str.equals("1"))
						str = "#";
					if (str.equals("0"))
						str = " ";
					if (j == this.maze.dimensions.getColumn() - 1)
//						str = str + " ";
//					else
						str = str + "\n";
					buffer.append(str);
				}
			}
			return buffer.toString();
		}else {
			return "Sorry, I mean, this is unsolveable.";
		}
	}
}
