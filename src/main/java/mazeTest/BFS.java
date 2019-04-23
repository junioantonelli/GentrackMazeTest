package mazeTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

public class BFS {

	Maze maze;

	public BFS(Maze maze) {
		this.maze = maze;
	}

	Set<String>							visited		= new HashSet<String>();
	Queue<Coordinate>					mainQueue	= new LinkedList<Coordinate>();
	LinkedList<Coordinate>				path		= new LinkedList<Coordinate>();
	LinkedList<Coordinate>				temp		= new LinkedList<Coordinate>();
	Map<String, LinkedList<Coordinate>>	paths		= new HashMap<String, LinkedList<Coordinate>>();
	List<Coordinate>					adjacents	= new ArrayList<Coordinate>();

	public LinkedList<Coordinate> solve() {
		Coordinate start = maze.getStart();
		visited.add(start.toString());
		path.add(start);
		paths.put(start.toString(), path);
		mainQueue.add(start);
		while (!mainQueue.isEmpty()) {
			Coordinate c = mainQueue.poll();
			if (c.getRow() == maze.getEnd().getRow() &&
							c.getColumn() == maze.getEnd().getColumn()) {
				path = new LinkedList<Coordinate>(paths.get(c.toString()));
				return path;
			}
			adjacents = maze.getAdjacents(c);
			for (Coordinate adj : adjacents) {
				if (visited.add(adj.toString())) {
					temp = new LinkedList<Coordinate>(paths.get(c.toString()));
					visited.add(adj.toString());
					mainQueue.add(adj);
					temp.add(adj);
					paths.put(adj.toString(), temp);
				}
			}
		}
		return null;
//		for (Coordinate p : path)
//			System.out.println("(" + p.getRow() + "," + p.getColumn() + ")");
	}
}
