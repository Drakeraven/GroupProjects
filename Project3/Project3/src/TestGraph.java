/*Test Program for the Java programs 
 * Uses provided Find Path's for reading
 * and generating a graph from a file.
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TestGraph {

	public static void main(String[] args) {
		if(args.length != 2) {
			System.err.println("USAGE: java Paths <vertex_file> <edge_file>");
			System.exit(1);
		}

		MyGraph g = readGraph(args[0],args[1]);

		@SuppressWarnings("resource")
		//Scanner console = new Scanner(System.in);
		Collection<Vertex> v = g.vertices();
                Collection<Edge> e = g.edges();
		System.out.println("Vertices are "+v);
		System.out.println("Edges are "+e);
		
		test1(g);
		test2(g);
		test3(g);
		test4(g);
		test5(g);
		test6(g);

	}
	public static MyGraph readGraph(String f1, String f2) {
		Scanner s = null;
		try {
			s = new Scanner(new File(f1));
		} catch(FileNotFoundException e1) {
			System.err.println("FILE NOT FOUND: "+f1);
			System.exit(2);
		}

		Collection<Vertex> v = new ArrayList<Vertex>();
		while(s.hasNext())
			v.add(new Vertex(s.next()));

		try {
			s = new Scanner(new File(f2));
		} catch(FileNotFoundException e1) {
			System.err.println("FILE NOT FOUND: "+f2);
			System.exit(2);
		}

		Collection<Edge> e = new ArrayList<Edge>();
		while(s.hasNext()) {
			try {
				Vertex a = new Vertex(s.next());
				Vertex b = new Vertex(s.next());
				int w = s.nextInt();
				e.add(new Edge(a,b,w));
			} catch (NoSuchElementException e2) {
				System.err.println("EDGE FILE FORMAT INCORRECT");
				System.exit(3);
			}
		}

		return new MyGraph(v,e);
	}
	
/* file Combos that will not (should not) create a graph:
 * VertexT1 and EdgeT1: Edge to a non-existant vertices.
 * VertexT1 and EdgeT2: Edge with a negative weight.
 * VertexT1 and EdgeT3: Identical Edges with different weights.
 * 
 * File Combos that Will (should) create a graph that should have no duplicates:
 * VertexT1 and EdgeT4: Duplicate Edges
 * VertexT2 and EdgeT5: Duplicate Vertices.
 */
	
	/**
	 * Test that given two vertices with no path,
	 * shows it does not exist.
	 * @param tGraph Loaded Graph.
	 */
	private static void test1(MyGraph tGraph) {
		Vertex b = new Vertex("B");
		Vertex a = new Vertex("A");
		Path output = tGraph.shortestPath(b, a);
		System.out.println("Shortest Path from " + b.getLabel() + " to " + a.getLabel() + ": ");
		if (output == null) {
			System.out.println("Does not exist.");
		} else {
			for (Vertex eachVertex : output.vertices) {
				System.out.print(eachVertex.getLabel() + " ");
			}
			System.out.print("\n");
			System.out.println(output.cost);
		}
	}
	
	/**
	 * Test that given a destination with no edges to it,
	 * returns it does not have a path.
	 * @param tGraph
	 */
	private static void test2(MyGraph tGraph) {
		Vertex a = new Vertex("A");
		Vertex c = new Vertex("C");
		Path output = tGraph.shortestPath(a, c);
		System.out.println("Shortest Path from " + a.getLabel() + " to " + c.getLabel() + ": ");
		if (output == null) {
			System.out.println("Does not exist.");
		} else {
			for (Vertex eachVertex : output.vertices) {
				System.out.print(eachVertex.getLabel() + " ");
			}
			System.out.print("\n");
			System.out.println(output.cost);
		}
	}
	
	/**
	 * Tests that given a source that has no edges from it,
	 * returns that there is no path.
	 * @param tGraph
	 */
	private static void test3(MyGraph tGraph) {
		Vertex c = new Vertex("C");
		Vertex d = new Vertex("D");
		Path output = tGraph.shortestPath(c, d);
		System.out.println("Shortest Path from " + c.getLabel() + " to " + d.getLabel() + ": ");
		if (output == null) {
			System.out.println("Does not exist.");
		} else {
			for (Vertex eachVertex : output.vertices) {
				System.out.print(eachVertex.getLabel() + " ");
			}
			System.out.print("\n");
			System.out.println(output.cost);
		}
	}
	
	/**
	 * Tests when given a valid source and destination,
	 * picks the cheapest path and not the shortest.
	 * @param tGraph
	 */
	private static void test4(MyGraph tGraph) {
		Vertex a = new Vertex("A");
		Vertex b = new Vertex("B");
		Path output = tGraph.shortestPath(a, b);
		System.out.println("Shortest Path from " + a.getLabel() + " to " + b.getLabel() + ": ");
		if (output == null) {
			System.out.println("Does not exist.");
		} else {
			for (Vertex eachVertex : output.vertices) {
				System.out.print(eachVertex.getLabel() + " ");
			}
			System.out.print("\n");
			System.out.println(output.cost);
		}
	}
	
	/**
	 * Tests when given valid source and destination,
	 * Picks the ONLY path available.
	 * @param tGraph
	 */
	private static void test5(MyGraph tGraph) {
		Vertex d = new Vertex("D");
		Vertex b = new Vertex("B");
		Path output = tGraph.shortestPath(d, b);
		System.out.println("Shortest Path from " + d.getLabel() + " to " + b.getLabel() + ": ");
		if (output == null) {
			System.out.println("Does not exist.");
		} else {
			for (Vertex eachVertex : output.vertices) {
				System.out.print(eachVertex.getLabel() + " ");
			}
			System.out.print("\n");
			System.out.println(output.cost);
		}
	}
	
	private static void test6(MyGraph tGraph) {
		Vertex a = new Vertex("A");
		Path output = tGraph.shortestPath(a, a);
		System.out.println("Shortest Path from " + a.getLabel() + " to " + a.getLabel() + ": ");
		if (output == null) {
			System.out.println("Does not exist.");
		} else {
			for (Vertex eachVertex : output.vertices) {
				System.out.print(eachVertex.getLabel() + " ");
			}
			System.out.print("\n");
			System.out.println(output.cost);
		}
	}
}