import java.util.Arrays;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		GraphMaker gm = new GraphMaker();
		PathFinder pf = new PathFinder();

		gm.readData("graph");

		LinkedList<String> goals = new LinkedList<String>();
		goals.add("G1");
		goals.add("G2");

		LinkedList<String> path = pf.findPath("S", goals, gm.getNeighbors(),
				gm.getCosts(), gm.getHeuristics());

		if (path != null) {
			System.out.println("Found path:");
			for (String n : path) {
				System.out.print(n + " ");
			}
			System.out.println();
		}
	}
}
