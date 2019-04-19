package mazeTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MazeFormatter {

	public MazeFormatter() {}

	public void formatMaze(Path path, Maze maze) {

		List<List<String>> param_temp = new ArrayList<List<String>>();
		List<List<String>> maze_temp = new ArrayList<List<String>>();
		try {

			Files.lines(path).map(line -> line.split(" ")).filter(line -> line.length < 3)
					.forEach(line -> param_temp.add(Arrays.asList(line)));

			Files.lines(path).map(line -> line.split(" ")).filter(line -> line.length > 2)
					.forEach(line -> maze_temp.add(Arrays.asList(line)));

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		maze.setParameters(param_temp);
		maze.setMaze(maze_temp);

		// try(BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"))){
		// String currentLine = null;
		// while((currentLine = reader.readLine()) != null){//while there is content on the current line
		// System.out.println(currentLine); // print the current line
		// }
		// }catch(IOException ex){
		// ex.printStackTrace(); //handle an exception here
		// }
	}
}
