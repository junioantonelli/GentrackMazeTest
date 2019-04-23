package mazeTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class ParallelBFS {

	Maze		maze;
	Coordinate	start;
	Coordinate	end;

	public ParallelBFS(Maze maze) {
		this.maze = maze;
		this.start = maze.getStart();
		this.end = maze.getEnd();
	}

	Set<String>						visited		= ConcurrentHashMap.newKeySet();
	BlockingQueue<Coordinate>		mainQueue	= new LinkedBlockingQueue<Coordinate>();
	BlockingQueue<Coordinate>		path		= new LinkedBlockingDeque<Coordinate>();
	LinkedList<Coordinate>			temp		= new LinkedList<Coordinate>();
	Map<String, Queue<Coordinate>>	paths		= new ConcurrentHashMap<String, Queue<Coordinate>>();
	LinkedList<Coordinate>			finalPath	= new LinkedList<Coordinate>();
	List<Coordinate>				adjacents	= new ArrayList<Coordinate>();

	public void solve(){
		visited.add(start.toString());
		path.add(start);
		paths.put(start.toString(), path);
		mainQueue.add(start);

		int numCores = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool(numCores);
//		ExecutorService executor = Executors.newCachedThreadPool();

		List<Callable<Void>> taskList = new ArrayList<Callable<Void>>(numCores);

		for (int i = 0; i < numCores; i++) {
			taskList.add(new BreadthSearch());
		}

		try {
			executor.invokeAll(taskList);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			executor.shutdown();
		}

//		for (Coordinate p : path)
//			System.out.println("(" + p.getRow() + "," + p.getColumn() + ")");
		
		executor.shutdown();
	}

	class BreadthSearch implements Callable<Void> {

		Queue<Coordinate> temp = new LinkedList<Coordinate>();

		@Override
		public Void call() throws Exception {
			System.out.println("Thread no" + Thread.currentThread().getName());
			Coordinate c;
			while ((c = mainQueue.poll()) != null) {
				System.out.println(Thread.currentThread().getName() + " coordinate: " + c);
				if (c.getRow() == end.getRow() &&
								c.getColumn() == maze.getEnd().getColumn()) {
					path = new LinkedBlockingQueue<Coordinate>(paths.get(c.toString()));
					break;
				}
				adjacents = maze.getAdjacents(c);
				for (Coordinate adj : adjacents) {
					// synchronized (this) {
					if (visited.add(adj.toString())) {
						temp = new LinkedList<Coordinate>(paths.get(c.toString()));
						// visited.add(adj.toString());
						mainQueue.add(adj);
						temp.add(adj);
						paths.put(adj.toString(), temp);
					}
					// }
				}
			}
			return null;
		}
	}
}
