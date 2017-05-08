import java.util.*;
import java.io.*;

public class Prim {

	public static void main(String[] args) throws FileNotFoundException {
		new Prim().run(Integer.parseInt(args[0]));
	}

	public void run(int n) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("input.txt"));
		int len = sc.nextLine().length() / 2;
		String[] vert = new String[len];
		int[][] adj = new int[len][len];

		// Reading the file and storing them into respective arrays
		int count = 0;
		while (sc.hasNext()) {
			String[] split = sc.nextLine().split(" ");
			vert[count] = split[0];

			for (int j = 0; j < len; j++) {
				adj[count][j] = Integer.parseInt(split[j + 1]);
			}

			count++;
		}

		Graph graph = new Graph(adj, vert);
		graph.Sort();
		System.out.println("Start Vertex: " + graph.graph.get(n).get_name());
		graph.Prim(graph.graph.get(n));
		graph.printcost();

		sc.close();
	}
}
