package mazeTest;

import java.util.Stack;

/**
 * Class MazeRunner Provides methods to run through the maze and stores coordinates of the runned
 * path Moving and check methods are adapted for mazes with wrapping movements
 * 
 * @author antonelli
 *
 */
public class MazeMovement {

	Maze			maze;

	int				row				= 0;
	int				column			= 0;

	int				previousRow		= 0;
	int				previousColumn	= 0;

	Stack<int[]>	runnedPath		= new Stack<int[]>();

	public MazeMovement(Maze maze) {
		this.maze = maze;
	}

	public int getPreviousRow() {
		return previousRow;
	}

	public void setPreviousRow(int previousRow) {
		this.previousRow = previousRow;
	}

	public int getPreviousColumn() {
		return previousColumn;
	}

	public void setPreviousColumn(int previousColumn) {
		this.previousColumn = previousColumn;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
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

	public boolean checkUpValidMove(Stack<int[]> path) {
		if (row == 0) {
			if (previousRow == maze.dimensions[0] - 1 && previousColumn == column) {
				return false;
			} else {
				return true;
			}
		} else {
			if (previousRow == row - 1 && previousColumn == column) {
				return false;
			} else {
				return true;
			}
		}
	}

	public String checkDownMazeElement() {
		if (row == maze.dimensions[0]) {
			return this.maze.getMaze()[0][column];
		} else {
			return this.maze.getMaze()[row + 1][column];
		}
	}

	public boolean checkDownValidMove(Stack<int[]> path) {
		if (row == maze.dimensions[0]) {
			if (previousRow == 0 && previousColumn == column) {
				return false;
			} else {
				return true;
			}
		} else {
			if (previousRow == row + 1 && previousColumn == column) {
				return false;
			} else {
				return true;
			}
		}
	}

	public String checkLeftMazeElement() {
		if (column == 0) {
			return this.maze.getMaze()[row][maze.dimensions[1] - 1];
		} else {
			return this.maze.getMaze()[row][column - 1];
		}
	}

	public boolean checkLeftValidMove(Stack<int[]> path) {
		if (column == 0) {
			if (previousRow == row && previousColumn == maze.dimensions[1] - 1) {
				return false;
			} else {
				return true;
			}
		} else {
			if (previousRow == row && previousColumn == column - 1) {
				return false;
			} else {
				return true;
			}
		}
	}

	public String checkRightMazeElement() {
		if (column == maze.dimensions[1]) {
			return this.maze.getMaze()[row][0];
		} else {
			return this.maze.getMaze()[row][column + 1];
		}
	}

	public boolean checkRightValidMove(Stack<int[]> path) {
		if (column == maze.dimensions[1]) {
			if (previousRow == row && previousColumn == 0) {
				return false;
			} else {
				return true;
			}
		} else {
			if (previousRow == row && previousColumn == column + 1) {
				return false;
			} else {
				return true;
			}
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
