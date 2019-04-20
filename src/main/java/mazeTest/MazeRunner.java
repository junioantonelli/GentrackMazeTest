package mazeTest;

import java.util.Stack;

/**
 * Class MazeRunner
 * Provides methods to run through the maze and stores coordinates of the runned path
 * Moving and check methods are adapted for mazes with wrapping movements
 * 
 * @author antonelli
 *
 */
public class MazeRunner {

	Maze			maze;

	int				row			= 0;
	int				column		= 0;

	Stack<int[]>	runnedPath	= new Stack<int[]>();

	public MazeRunner(Maze maze) {
		this.maze = maze;
	}

	public Stack<int[]> getRunnedPath() {
		return runnedPath;
	}

	public void setRunnedPath(Stack<int[]> runnedPath) {
		this.runnedPath = runnedPath;
	}

	public Maze getMaze() {
		return maze;
	}

	public void setMaze(Maze maze) {
		this.maze = maze;
	}

	public String checkUpMazeElement() {
		if (row == 0) {
			return this.maze.getMaze()[maze.dimensions[0] - 1][column];
		} else {
			return this.maze.getMaze()[row - 1][column];
		}
	}

	public String checkDownMazeElement() {
		if (row == maze.dimensions[0]) {
			return this.maze.getMaze()[0][column];
		} else {
			return this.maze.getMaze()[row + 1][column];
		}
	}

	public String checkLeftMazeElement() {
		if (column == 0) {
			return this.maze.getMaze()[row][maze.dimensions[1]];
		} else {
			return this.maze.getMaze()[row][column - 1];
		}
	}

	public String checkRightMazeElement() {
		if (column == maze.dimensions[1]) {
			return this.maze.getMaze()[row][0];
		} else {
			return this.maze.getMaze()[row][column + 1];
		}
	}

	public int[] upMove(int row, int column) {
		if (row == 0) {
			row = maze.dimensions[0] - 1;
		} else {
			row--;
		}
		return new int[] {row, column};
	}

	public int[] downMove(int row, int column) {
		if (row == maze.dimensions[0]) {
			row = 0;
		} else {
			row++;
		}
		return new int[] {row, column};
	}

	public int[] leftMove(int row, int column) {
		if (column == 0) {
			column = maze.dimensions[1];
		} else {
			column--;
		}
		return new int[] {row, column};
	}

	public int[] rightMove(int row, int column) {
		if (column == maze.dimensions[1]) {
			column = 0;
		} else {
			column++;
		}
		return new int[] {row, column};
	}

}
