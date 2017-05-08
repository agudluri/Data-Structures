import java.io.*;
import java.util.*;

public class BFS {

	Vector<Vertex> graph = new Vector<Vertex>();
	StringBuilder result = new StringBuilder();
	Queue<Vertex> vertices = new LinkedList<Vertex>();

	public static void main(String[] args) throws IOException {
		new BFS().run();
	}

	public void run() throws IOException {
		String commandLine;
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		// Prompt and read what the user entered
		System.out.print("lab13> Enter Starting Vertex: ");
		commandLine = console.readLine();

		int inf = Integer.MAX_VALUE;
		/* A B C D E F G H I */
		int adj[][] = { /* A */ { inf, 8, inf, 10, inf, inf, 12, inf, inf },
				/* B */ { 8, inf, inf, inf, 12, 18, inf, inf, inf },
				/* C */ { inf, inf, inf, inf, inf, 2, inf, 10, inf },
				/* D */ { 10, inf, inf, inf, inf, 8, inf, inf, inf },
				/* E */ { inf, 12, inf, inf, inf, inf, 24, inf, inf },
				/* F */ { inf, 18, 2, 8, inf, inf, inf, inf, inf },
				/* G */ { 12, inf, inf, inf, 24, inf, inf, inf, inf },
				/* H */ { inf, inf, 10, inf, inf, inf, inf, inf, inf },
				/* I */ { inf, inf, inf, inf, inf, inf, inf, 3, inf } };

		graph.add(new Vertex("A"));
		graph.add(new Vertex("B"));
		graph.add(new Vertex("C"));
		graph.add(new Vertex("D"));
		graph.add(new Vertex("E"));
		graph.add(new Vertex("F"));
		graph.add(new Vertex("G"));
		graph.add(new Vertex("H"));
		graph.add(new Vertex("I"));

		for (int i = 0; i < 9; i++) {
			// Collecting neighbours
			Vertex v = graph.elementAt(i);
			for (int j = 0; j < 9; j++) {
				if (adj[i][j] != inf) {
					v.add_edge(new Edge(i, j, adj[i][j]));
				}
			}
			v.order_edges();
		} /* based on cost (lowest to highest) */

		Vertex temp = null;
		boolean flag = false;
		for (Vertex v : graph) {
			if (v.match_name(commandLine)) {
				temp = v;			//Checking if the entered vertex is in the list of vertices of the graph.
				flag = true;
				break;
			}
		}

		if (flag == false) {
			System.out.println("Invalid vertex entered. Try again.\n");
		} else {
			result.append(temp.name + " ");
			BFirst(temp);
			System.out.println("For starting vertex " + temp.name + ", the breadth-first traversal produces: \n"
					+ result.toString());
			result.setLength(0);
			vertices.clear();
			graph.clear();
		}
	}

	/**
	 * A recursive method to calculate the Breadth first traversal path (based on queue)
	 * @param vert, the starting node of the traversal
	 */
	private void BFirst(Vertex vert) {
		vert.visited();
		for (Edge e : vert.edgelist)
			for (Vertex V : graph)
				if (V.state != 2 && V.id == e.destination) {
					V.visited();
					result.append(V.name + " ");
					vertices.add(V);
				}

		if (vertices.size() != 0)
			BFirst(vertices.remove());
	}

	// --------------------------------------------------------------------
	static class Vertex {
		private String name;
		private int id; /* Integral id of vertex: [0, n-1] */
		private int state; /* 0: undiscovered; 1: discovered; 2: visited */
		private int pred; /* Predecessor node. Unused here */
		private Vector<Edge> edgelist;

		private static int counter = 0;

		public Vertex(String name) {
			this.name = name;
			state = 0;
			pred = -1;
			id = counter++;
			edgelist = null;
		}

		public void order_edges() {
			Collections.sort(edgelist);
		}

		public String toString() {
			StringBuffer s = new StringBuffer();

			s.append("Vertex: " + name + "(" + id + ")");
			s.append(" (" + state + ", " + pred + ")\n");
			s.append("  Neighbours: ");
			for (Edge e : edgelist) {
				s.append(e);
				s.append(" ");
			}

			return s.toString();
		}

		public void add_edge(Edge e) {
			if (edgelist == null) {
				edgelist = new Vector<Edge>();
			}
			edgelist.add(e);
		}

		public boolean match_name(String name) {
			if (this.name.equalsIgnoreCase(name))
				return true;
			else
				return false;
		}

		public void visited() {
			state = 2;
		}

		public void discovered() {
			state = 1;
		}

		public String get_name() {
			return name;
		}

		public static int get_vertex_index(String name) {
			int v = -1;

			switch (name) {
			case "A":
				v = 0;
				break;
			case "B":
				v = 1;
				break;
			case "C":
				v = 2;
				break;
			case "D":
				v = 3;
				break;
			case "E":
				v = 4;
				break;
			case "F":
				v = 5;
				break;
			case "G":
				v = 6;
				break;
			case "H":
				v = 7;
				break;
			case "I":
				v = 8;
				break;
			default:
				System.out.println("get_vertex_index: invalid name");
				break;
			}
			return v;
		}

		public static String get_vertex_name(int index) {
			String v = "null";
			switch (index) {
			case 0:
				v = "A";
				break;
			case 1:
				v = "B";
				break;
			case 2:
				v = "C";
				break;
			case 3:
				v = "D";
				break;
			case 4:
				v = "E";
				break;
			case 5:
				v = "F";
				break;
			case 6:
				v = "G";
				break;
			case 7:
				v = "H";
				break;
			case 8:
				v = "I";
				break;
			default:
				System.out.println("get_vertex_name: invalid index");
				break;
			}
			return v;
		}
	} // Class Vertex

	static class Edge implements Comparable<Edge> {
		private int source;
		private int destination;
		private int cost;

		public Edge(int s, int d, int c) {
			source = s;
			destination = d;
			cost = c;
		}

		public String toString() {
			StringBuffer s = new StringBuffer();

			s.append("(" + Vertex.get_vertex_name(source) + ", " + Vertex.get_vertex_name(destination) + ", " + cost
					+ ")");
			return s.toString();
		}

		public int compareTo(Edge o) {
			if (this.cost < o.cost)
				return -1;
			else if (this.cost > o.cost)
				return 1;
			else
				return 0;
		}

	} // Class Edge
}

