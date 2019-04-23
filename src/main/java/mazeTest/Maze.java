package mazeTest;

import java.util.ArrayList;
import java.util.List;

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
	
	Coordinate dimensions;
	Coordinate start;
	Coordinate end;
	
	
	public List<Coordinate> getAdjacents(Coordinate current){
		List<Coordinate> adjacents = new ArrayList<Coordinate>();
		String element = null;
		Coordinate coordinate = null;
//		up adjacent
		if(current.getRow() == 0) {
			coordinate = new Coordinate(dimensions.getRow()-1, current.getColumn());
			element = this.maze[coordinate.getRow()][coordinate.getColumn()];
		}else {
			coordinate = new Coordinate(current.getRow()-1, current.getColumn());
			element = this.maze[coordinate.getRow()][coordinate.getColumn()];
		}
		if(element.equals("0"))
			adjacents.add(coordinate);
		element = null;
		coordinate = null;
		
//		down adjacent
		if(current.getRow() == dimensions.getRow()-1) {
			coordinate = new Coordinate(0, current.getColumn());
			element = this.maze[coordinate.getRow()][coordinate.getColumn()];
		}else {
			coordinate = new Coordinate(current.getRow()+1, current.getColumn());
			element = this.maze[coordinate.getRow()][coordinate.getColumn()];
		}
		if(element.equals("0"))
			adjacents.add(coordinate);
		element = null;
		coordinate = null;
		
//		left adjacent
		if(current.getColumn() == 0) {
			coordinate = new Coordinate(current.getRow(), dimensions.getColumn()-1);
			element = this.maze[coordinate.getRow()][coordinate.getColumn()];
		}else {
			coordinate = new Coordinate(current.getRow(), current.getColumn()-1);
			element = this.maze[coordinate.getRow()][coordinate.getColumn()];
		}
		if(element.equals("0"))
			adjacents.add(coordinate);
		element = null;
		coordinate = null;
		
//		right adjacent
		if(current.getColumn() == dimensions.getColumn()-1) {
			coordinate = new Coordinate(current.getRow(), 0);
			element = this.maze[coordinate.getRow()][coordinate.getColumn()];
		}else {
			coordinate = new Coordinate(current.getRow(), current.getColumn()+1);
			element = this.maze[coordinate.getRow()][coordinate.getColumn()];
		}
		if(element.equals("0"))
			adjacents.add(coordinate);
		element = null;
		coordinate = null;
		return adjacents;
	}
	
	public String[][] getMaze() {
		return maze;
	}

	public void setMaze(String[][] maze) {
		this.maze = maze;
	}

	public Coordinate getDimensions() {
		return dimensions;
	}

	public void setDimensions(Coordinate dimensions) {
		this.dimensions = dimensions;
	}

	public Coordinate getStart() {
		return start;
	}

	public void setStart(Coordinate start) {
		this.start = start;
	}

	public Coordinate getEnd() {
		return end;
	}

	public void setEnd(Coordinate end) {
		this.end = end;
	}
	
	
}
