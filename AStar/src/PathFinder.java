import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class PathFinder {

	public LinkedList<String> findPath(String start, LinkedList<String> goals,
			HashMap<String, LinkedList<String>> neighbors,
			HashMap<String, HashMap<String, Integer>> costs,
			HashMap<String, Integer> heuristics) {
		HashSet<String> closedSet = new HashSet<String>(); // The set of nodes
															// already
															// evaluated.
		HashSet<String> openSet = new HashSet<String>(); // The set of tentative
															// nodes to be
															// evaluated,
		openSet.add(start);

		HashMap<String, String> cameFrom = new HashMap<String, String>();

		// Cost from start along best known path.
		HashMap<String, Integer> gScore = new HashMap<String, Integer>();
		gScore.put(start, 0);

		// Estimated total cost from start to goal through y.
		HashMap<String, Integer> fScore = new HashMap<String, Integer>();

		fScore.put(start, gScore.get(start) + heuristics.get(start));
		System.out.println("Opened nodes: ");
		while (!openSet.isEmpty()) {
			String current = getLowestF(openSet, fScore);
			System.out.print(current + " ");
			if (goals.contains(current)) {
				return reconstructPath(cameFrom, current);
			}

			openSet.remove(current);
			closedSet.add(current);

			for (String neighbor : neighbors.get(current)) {
				if (closedSet.contains(neighbor)) {
					continue;
				}

				int tentativeGScore = gScore.get(current)
						+ costs.get(current).get(neighbor);

				if (!openSet.contains(neighbor)
						|| tentativeGScore < gScore.get(neighbor)) {
					cameFrom.put(neighbor, current);
					gScore.put(neighbor, tentativeGScore);
					fScore.put(neighbor,
							gScore.get(neighbor) + heuristics.get(neighbor));
					if (!openSet.contains(neighbor)) {
						openSet.add(neighbor);
					}
				}
			}
		}
		return null; // failure
	}

	private LinkedList<String> reconstructPath(
			HashMap<String, String> cameFrom, String current) {
		LinkedList<String> path;

		if (cameFrom.containsKey(current)) {
			path = reconstructPath(cameFrom, cameFrom.get(current));
			path.add(current);
		} else {
			path = new LinkedList<String>();
			path.add(current);
		}
		return path;
	}

	private String getLowestF(HashSet<String> openSet,
			HashMap<String, Integer> fScore) {
		int min = Integer.MAX_VALUE;
		String best = null;

		int score;
		for (String node : fScore.keySet()) {
			if (!openSet.contains(node)) {
				continue;
			}
			score = fScore.get(node);
			if (score < min) {
				min = score;
				best = node;
			}
		}
		return best;
	}
}
