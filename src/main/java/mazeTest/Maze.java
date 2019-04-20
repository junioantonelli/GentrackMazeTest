package mazeTest;

/**
 * Class Maze
 * 
 * This is a model class to encapsulate the maze and some attributes
 * 
 * @author antonelli
 *
 */
public class Maze {
	String[][]	maze;
	
	int[] dimensions;
	int[] start;
	int[] end;
	
	
	public String[][] getMaze() {
		return maze;
	}

	public void setMaze(String[][] maze) {
		this.maze = maze;
	}

	public int[] getDimensions() {
		return dimensions;
	}

	public void setDimensions(int[] dimensions) {
		this.dimensions = dimensions;
	}

	public int[] getStart() {
		return start;
	}

	public void setStart(int[] start) {
		this.start = start;
	}

	public int[] getEnd() {
		return end;
	}

	public void setEnd(int[] end) {
		this.end = end;
	}
	
	
}
