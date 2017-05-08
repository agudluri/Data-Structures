import java.util.Vector;
import java.util.ArrayList;
import java.util.Collections;

public class Graph {

	int adj[][];
	int max_row, max_col;
	Vector<Vertex> graph = new Vector<Vertex>();

	public Graph(int matrix[][], String[] vert) {
		this.adj = matrix;
		max_row = vert.length;
		max_col = vert.length;
		for (int i = 0; i < vert.length; i++) {
			graph.add(new Vertex(vert[i]));
		}

	}

	public void Sort() {

		for (int i = 0; i < max_row; i++) {
			// Go through each row of the adjacency matrix collecting neighbours
			Vertex v = graph.elementAt(i);
			for (int j = 0; j < max_col; j++) {
				if (adj[i][j] != 0) {
					v.add_edge(new Edge(i, j, adj[i][j]));
				}
			}
			v.order_edges(); /* Order (sort) the neighbours for this vertex */
		} /* based on cost (lowest to highest) */

	}

	PQ<Edge> edges = new PQ<>();
	ArrayList<String> dests = new ArrayList<String>();
	int count = 0;

	public void Prim(Vertex v) {
		v.visited();
		for (Edge e : v.edgelist) {
			for (Vertex vert : graph) {
				if (vert.id == e.destination && vert.state != 2) {
					edges.add(e);
				}
			}
		}

		while (edges.size() != 0) {

			String s = edges.print_min();

			if (!(dests.contains(s.substring(3, 4)))) {
				System.out.println("Add Edge <" + edges.print_min() + ">");
				count += Integer.parseInt(edges.print_min().substring(6,7));
			}

			dests.add(s.substring(3, 4));
			int n = edges.remove().destination;
			for (Vertex vert : graph) {
				if (vert.id == n && vert.state != 2)
					Prim(vert);
			}
		}
	}
	
	public void printcost() {
		System.out.println("Total cost: " + count);
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
			if (this.name.equals(name))
				return true;
			else
				return false;
		}

		public void visited() {
			state = 2;
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

			s.append(Vertex.get_vertex_name(source) + ", " + Vertex.get_vertex_name(destination) + ", " + cost);
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
