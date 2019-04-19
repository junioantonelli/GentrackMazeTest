package mazeTest;

import java.util.List;

/**
 * @author antonelli
 *
 */
public class MazeSolver {
	public MazeSolver() {}

	public String solver(Maze maze) {
		
		
//		Assign the orientation and sense of the path vector
//		ORIENTATION: NORTH OR SOUTH
//		SENSE: EAST OR WEST
		
		String orientation = "SOUTH";
		String sense = "WEST";
		
		List<String> start = maze.getParameters().get(1);
		List<String> end = maze.getParameters().get(2);
		
		// start coordinates
		int a = Integer.parseInt(start.get(0));
		int b = Integer.parseInt(end.get(0));
		
		// end coordinates
		int c = Integer.parseInt(start.get(1));
		int d = Integer.parseInt(end.get(1));
		
		if((c - a) > 0) {
			sense = "EAST";
		}else {
			sense = "WEST";
		}
		
		if((d - b) > 0) {
			orientation = "SOUTH";
		}else {
			orientation = "NORTH";
		}
		
		// vector index;
		int i = a;
		int j = b;
		
		
		
		
		
		return null;
	}
}
