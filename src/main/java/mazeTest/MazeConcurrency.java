package mazeTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class MazeConcurrency {

	// list of callables/tasks
	List<Callable<List<Stack<int[]>>>> callables = new ArrayList<Callable<List<Stack<int[]>>>>();

	Maze maze;
	
	// start coordinates
	int a = maze.getStart()[0];
	int b = maze.getStart()[1];
	
	// end coordinates
	int c = maze.getEnd()[0];
	int d = maze.getEnd()[1];
	
	public MazeConcurrency(Maze maze) {
		this.maze = maze;
	}

	public void solveMaze(Maze maze) {
		Stack<int[]> runnedPath = new Stack<int[]>();

		// start point
		runnedPath.push(new int[] {a, b});

		ExecutorService executor = Executors
						.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		
		boolean finished = false;
		
		// first callable is using the start point
		callables.add(task(new MazeRunner(maze), runnedPath));
		
		
		while(!finished) {
			try {
				List<Object> result = executor.invokeAll(callables)
				.stream()
				.map(future -> {
				    try {
				        return future.get();
				    }
				    catch (ExecutionException | InterruptedException e) {
				        e.printStackTrace();
				        return null;
				    }
				})
				.map(possiblePaths -> treatPossiblePaths(possiblePaths))
				.filter(foundExit -> foundExit.equals(true))
				.collect(Collectors.toList());
				if(!result.isEmpty()) {
					finished = true;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public boolean treatPossiblePaths(List<Stack<int[]>> possiblePaths) {
		
		for(Stack<int[]>p:possiblePaths) {
			// if one of paths reached the end
			if(p.peek()[0] == c && p.peek()[1] == d) {
				
			}
		}
		
//		callables
		
		return true;
	}

	/**
	 * @param mazeRunner
	 * @param runnedPath
	 * @return List<Stack<int[]>> a list of possible paths to run through
	 */
	Callable<List<Stack<int[]>>> task(MazeRunner mazeRunner, Stack<int[]> runnedPath) {
		return () -> {

			// the task itself does not decide if there will be more tasks or threads,
			// just show the possible paths.

			// configuring the pointers
			int row = runnedPath.peek()[0];
			int column = runnedPath.peek()[1];

			// a list of many possible paths
			List<Stack<int[]>> returnable = new ArrayList<Stack<int[]>>();

			if (mazeRunner.checkDownMazeElement() == "0") {
				Stack<int[]> path1 = runnedPath;
				path1.add(mazeRunner.downMove(row, column));
				returnable.add(path1);
			}
			if (mazeRunner.checkUpMazeElement() == "0") {
				Stack<int[]> path2 = runnedPath;
				path2.add(mazeRunner.upMove(row, column));
				returnable.add(path2);
			}
			if (mazeRunner.checkLeftMazeElement() == "0") {
				Stack<int[]> path3 = runnedPath;
				path3.add(mazeRunner.leftMove(row, column));
				returnable.add(path3);
			}
			if (mazeRunner.checkRightMazeElement() == "0") {
				Stack<int[]> path4 = runnedPath;
				path4.add(mazeRunner.rightMove(row, column));
				returnable.add(path4);
			}
			// if there is no way on maze the list will be empty
			return returnable;
		};
	}
}
