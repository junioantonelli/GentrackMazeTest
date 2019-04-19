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
		
		List<String> dimensions = maze.getParameters().get(0);
		List<String> start = maze.getParameters().get(1);
		List<String> end = maze.getParameters().get(2);
		List<List<String>> matrix = maze.getMaze();
		
		// maze dimensions
		int width = Integer.parseInt(dimensions.get(0));
		int height = Integer.parseInt(dimensions.get(1));
		
		// start coordinates
		int a = Integer.parseInt(start.get(0));
		int b = Integer.parseInt(end.get(0));
		
		// end coordinates
		int c = Integer.parseInt(start.get(1));
		int d = Integer.parseInt(end.get(1));
		
		String up = "0";
		String down = "0";
		String left = "0";
		String right = "0";
		
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
		
		while (i != c && j != d) {
//		check around if there is a way
			up = matrix.get(i-1).get(j);
			down = matrix.get(i+1).get(j);
			left = matrix.get(i).get(j-1);
			right = matrix.get(i).get(j+1);
			
//		check if there is an edge with way to wrap
			if(up.equals("0") || up.equals("0") || up.equals("0") || up.equals("0")) {
				if ((i-1) < 0 || (j-1) < 0 || (i+1) > width || (j+1) > height) {
					
				}
			}
		}
		
		
		
		return null;
	}
}
