package mazeTest;

import java.util.List;

public class Maze {
	List<List<String>>	maze;
	List<List<String>>	parameters;

	public Maze(List<List<String>> maze, List<List<String>> parameters) {
		this.maze = maze;
		this.parameters = parameters;
	}
	
	public Maze() {
	}

	public List<List<String>> getMaze() {
		return maze;
	}

	public void setMaze(List<List<String>> maze) {
		this.maze = maze;
	}

	public List<List<String>> getParameters() {
		return parameters;
	}

	public void setParameters(List<List<String>> parameters) {
		this.parameters = parameters;
	}
	
	
}
