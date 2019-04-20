package mazeTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class MazeRunner {

	// list of callables/tasks
	List<Callable<List<Stack<int[]>>>>	callables	=
					new ArrayList<Callable<List<Stack<int[]>>>>();

	Maze								maze;

	// start coordinates
	int									a			= 0;
	int									b			= 0;

	// end coordinates
	int									c			= 0;
	int									d			= 0;

	// final path found
	Stack<int[]>						finalPath	= new Stack<int[]>();

	public MazeRunner(Maze maze) {
		this.maze = maze;
		this.a = maze.getStart()[1];
		this.b = maze.getStart()[0];

		// end coordinates
		this.c = maze.getEnd()[1];
		this.d = maze.getEnd()[0];

	}

	public void solveMaze() {
		Stack<int[]> runnedPath = new Stack<int[]>();

		// start point
		runnedPath.push(new int[] {a, b});

		ExecutorService executor = Executors
						.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		boolean finished = false;

		// first callable is using the start point
		callables.add(task(new MazeMovement(maze), runnedPath));


		while (!finished) {
			try {
				List<Object> result = executor.invokeAll(callables)
								.stream()
								.map(future -> {
									try {
										List<Stack<int[]>> a = future.get();
										// System.out.println(a);
										return a;
									} catch (ExecutionException
													| InterruptedException e) {
										e.printStackTrace();
										return null;
									}
								})
								.map(possiblePaths -> treatPossiblePaths(possiblePaths))
								.filter(foundExit -> foundExit.equals(true))
								.collect(Collectors.toList());
				if (!result.isEmpty()) {
					finished = true;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	public boolean treatPossiblePaths(List<Stack<int[]>> possiblePaths) {

		// reset the callables list
		callables = new ArrayList<Callable<List<Stack<int[]>>>>();

		if (!possiblePaths.isEmpty()) {
			for (Stack<int[]> p : possiblePaths) {
				// if one of paths reached the end
				if (p.peek()[0] == c && p.peek()[1] == d) {
					this.finalPath = p;
					System.out.println(p);
					return true;
				} else {
					callables.add(task(new MazeMovement(maze), p));
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param mazeMover
	 * @param runnedPath
	 * @return List<Stack<int[]>> a list of possible paths to run through
	 */
	Callable<List<Stack<int[]>>> task(MazeMovement mazeMover, Stack<int[]> runnedPath) {
		return () -> {

			System.out.println("callable");

			// the task itself does not decide if there will be more tasks or threads,
			// just show the possible paths.

			// configuring the pointers
			int row = runnedPath.peek()[0];
			int column = runnedPath.peek()[1];
			runnedPath.pop();
			mazeMover.setRow(row);
			mazeMover.setColumn(column);
			if (!runnedPath.isEmpty()) {
				mazeMover.setPreviousRow(runnedPath.peek()[0]);
				mazeMover.setPreviousColumn(runnedPath.peek()[1]);
			}

			// a list of many possible paths
			List<Stack<int[]>> returnable = new ArrayList<Stack<int[]>>();

			System.out.println("check down maze = " + mazeMover.checkDownMazeElement());
			System.out.println("check up maze = " + mazeMover.checkUpMazeElement());
			System.out.println("check left maze = " + mazeMover.checkLeftMazeElement());
			System.out.println("check right maze = " + mazeMover.checkRightMazeElement());
			if (mazeMover.checkDownValidMove(runnedPath)
							&& mazeMover.checkDownMazeElement().equals("0")) {
				Stack<int[]> path1 = runnedPath;
				path1.add(new int[] {row, column});
				path1.add(mazeMover.downMove(row, column));
				System.out.println("mazemover down move: " + path1.peek()[0] + ", "
								+ path1.peek()[1]);
				returnable.add(path1);
			}
			if (mazeMover.checkUpValidMove(runnedPath)
							&& mazeMover.checkUpMazeElement().equals("0")) {
				Stack<int[]> path2 = runnedPath;
				path2.add(mazeMover.upMove(row, column));
				System.out.println("mazemover up move: " + path2.peek()[0] + ", "
								+ path2.peek()[1]);
				returnable.add(path2);
			}
			if (mazeMover.checkLeftValidMove(runnedPath)
							&& mazeMover.checkLeftMazeElement().equals("0")) {
				Stack<int[]> path3 = runnedPath;
				path3.add(mazeMover.leftMove(row, column));
				System.out.println("mazemover left move: " + path3.peek()[0] + ", "
								+ path3.peek()[1]);
				returnable.add(path3);
			}
			if (mazeMover.checkRightValidMove(runnedPath)
							&& mazeMover.checkRightMazeElement().equals("0")) {
				Stack<int[]> path4 = runnedPath;
				path4.add(mazeMover.rightMove(row, column));
				System.out.println("mazemover right move: " + path4.peek()[0] + ", "
								+ path4.peek()[1]);
				returnable.add(path4);
			}
			// if there is no way on maze the list will be empty
			return returnable;
		};
	}
}
