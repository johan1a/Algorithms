import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class GraphMaker {
	HashMap<String, LinkedList<String>> neighbors;
	HashMap<String, HashMap<String, Integer>> costs;
	HashMap<String, Integer> heuristics;


	public GraphMaker() {
		neighbors = new HashMap<String, LinkedList<String>>();
		costs = new HashMap<String, HashMap<String, Integer>>();
		heuristics = new HashMap<String, Integer>();
	}

	public void readData(String fileName) {
		System.out.println("Reading data");
		String line;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));

			line = br.readLine();

			String[] tokens;
			while (line.length() > 0) {
				tokens = line.split(" ");
				addHeuristic(tokens);
				line = br.readLine();
				System.out.println(line);
			}

			line = br.readLine();
			
			while (line != null) {
				System.out.println(line);
				tokens = line.split(" ");
				addNeighbor(tokens);
				line = br.readLine();

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// Files.lines(fileName).forEach(split(" ")).forEach(this::addNeighbor);
	}

	private void addHeuristic(String[] tokens) {
		heuristics.put(tokens[0], Integer.parseInt(tokens[1]));
	}

	public void addNeighbor(String[] tokens) {
		LinkedList<String> nodeNeighbors = neighbors.get(tokens[0]);
		if (nodeNeighbors == null) {
			nodeNeighbors = new LinkedList<String>();
			neighbors.put(tokens[0], nodeNeighbors);
		}

		nodeNeighbors.add(tokens[1]);

		HashMap<String, Integer> neighborCosts = costs.get(tokens[0]);

		if (neighborCosts == null) {
			neighborCosts = new HashMap<String, Integer>();
			costs.put(tokens[0], neighborCosts);
		}

		neighborCosts.put(tokens[1], Integer.parseInt(tokens[2]));
	}
	

	public HashMap<String, LinkedList<String>> getNeighbors() {
		return neighbors;
	}

	public HashMap<String, HashMap<String, Integer>> getCosts() {
		return costs;
	}

	public HashMap<String, Integer> getHeuristics() {
		return heuristics;
	}
}
