import java.util.*;

/**
 * A representation of a graph. Assumes that we do not have negative cost edges
 * in the graph.
 */
public class MyGraph implements Graph {
	// you will need some private fields to represent the graph
	// you are also likely to want some private helper methods
	
	private Collection<Vertex> myVertex;
	
	private Collection<Edge> myEdge;
	
	private HashMap<Vertex, ArrayList<Edge>> myMap;
	

	// YOUR CODE HERE

	/**
	 * Creates a MyGraph object with the given collection of vertices and the
	 * given collection of edges.
	 * 
	 * @param v
	 *            a collection of the vertices in this graph
	 * @param e
	 *            a collection of the edges in this graph
	 */
	public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
		myMap = new HashMap<Vertex, ArrayList<Edge>>();
		if (!checkVertex(v)) { 
			throw new IllegalArgumentException();
		}
		myVertex = v; 
		
		if (!checkEdges(e)) { 
			throw new IllegalArgumentException(); 
		} 
		myEdge = e; 

		//Check for: 
		//Edges to/from a vertex that doesn't exist
		//Negative edge weights
		//Repeated edge in the same direction with different weights
		//If vertex/edge with same name/weight, ignore repeated instance

	}

	/**
	 * Return the collection of vertices of this graph
	 * 
	 * @return the vertices as a collection (which is anything iterable)
	 */
	@Override
	public Collection<Vertex> vertices() {
		Collection<Vertex> temp = new ArrayList<Vertex>();
		Iterator<Vertex> iter = myVertex.iterator();
		while (iter.hasNext()){
			temp.add(iter.next()); 
		} 
		return temp;
	}

	/**
	 * Return the collection of edges of this graph
	 * 
	 * @return the edges as a collection (which is anything iterable)
	 */
	@Override
	public Collection<Edge> edges() {
		Collection<Edge> temp = new ArrayList<Edge>();
		Iterator<Edge> iter = myEdge.iterator();
		while (iter.hasNext()){
			temp.add(iter.next()); 			
		} 
		return temp;
	}
	
	/**
	 * Return the map of vertices and edges of this graph 
	 * @return 
	 * @return 
	 * 
	 * @return the map of vertices and edges 
	 */
	public void printMap() { 
		System.out.println(myMap.entrySet());
	}
	
	/**
	 * Checks collection of vertex for improper elements
	 * 
	 * @return true if the collection contains proper elements
	 */
	private boolean checkVertex(Collection<Vertex> v){
		//iterate over the vertexes using iterator
		//for each element, add it to the map
		if (v.isEmpty()) { 
			return false;
		} else { 
			Vertex temp; 
			Iterator<Vertex> iter = v.iterator();
			while (iter.hasNext()){	
				temp = iter.next(); 
				if(!myMap.containsKey(temp)) {
					myMap.put(new Vertex(temp.getLabel().toLowerCase()), new ArrayList<Edge>());
				}
			}	
			return true;
		}
	}
	
	/**
	 * Checks collection of edges for improper elements
	 * 
	 * @return true if the collection contains proper elements
	 */
	private boolean checkEdges(Collection<Edge> e) {
		boolean flag = true; 
		Edge temp; 
		Iterator<Edge> iter = e.iterator();
		
		//iterate over the edges using iterator 
		//compare from/to to the collection of vertices myVertex
		//check for same directed edge but different weight
		if (e.isEmpty()) { 
			flag = false; 
		} 	

		while(iter.hasNext()) { 
			temp = iter.next();
			if (myMap.containsKey(temp.getSource()) 
					&& myMap.containsKey(temp.getDestination()) 
					&& temp.getWeight() >= 0) {
				myMap.get(temp.getSource()).add(temp);
			} else { 
				flag = false;
			}
		}
		return flag; 		
	}

	/**
	 * Return a collection of vertices adjacent to a given vertex v. i.e., the
	 * set of all vertices w where edges v -> w exist in the graph. Return an
	 * empty collection if there are no adjacent vertices.
	 * 
	 * @param v
	 *            one of the vertices in the graph
	 * @return an iterable collection of vertices adjacent to v in the graph
	 * @throws IllegalArgumentException
	 *             if v does not exist.
	 */
	@Override
	public Collection<Vertex> adjacentVertices(Vertex v) {
		return null;

		// YOUR CODE HERE

	}

	/**
	 * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed
	 * graph. Assumes that we do not have negative cost edges in the graph.
	 * 
	 * @param a
	 *            one vertex
	 * @param b
	 *            another vertex
	 * @return cost of edge if there is a directed edge from a to b in the
	 *         graph, return -1 otherwise.
	 * @throws IllegalArgumentException
	 *             if a or b do not exist.
	 */
	@Override
	public int edgeCost(Vertex a, Vertex b) {
		return 0;

		// YOUR CODE HERE

	}

	/**
	 * Returns the shortest path from a to b in the graph, or null if there is
	 * no such path. Assumes all edge weights are nonnegative. Uses Dijkstra's
	 * algorithm.
	 * 
	 * @param a
	 *            the starting vertex
	 * @param b
	 *            the destination vertex
	 * @return a Path where the vertices indicate the path from a to b in order
	 *         and contains a (first) and b (last) and the cost is the cost of
	 *         the path. Returns null if b is not reachable from a.
	 * @throws IllegalArgumentException
	 *             if a or b does not exist.
	 */
	public Path shortestPath(Vertex a, Vertex b) {
		return null;

		// YOUR CODE HERE (you might comment this out this method while doing
		// Part 1)

	}
	
	public static void main (String[] args) {
		Vertex vert1 = new Vertex("one");
		Vertex vert2 = new Vertex("two");
		
		ArrayList<Vertex> vert = new ArrayList<Vertex>();
		vert.add(vert1);
		vert.add(vert2);
		//System.out.println(vert);
		
		ArrayList<Edge> edge = new ArrayList<Edge>();
		edge.add(new Edge(vert1, vert2, 5));
		edge.add(new Edge(vert1, vert2, 6));
		
		MyGraph graph = new MyGraph(vert, edge);
		graph.printMap();
		
		HashMap<Vertex, ArrayList<Edge>> test = new HashMap<Vertex, ArrayList<Edge>>();
		test.put(vert1, new ArrayList<Edge>());
		test.put(vert2, new ArrayList<Edge>());
		Edge temp; 
		Iterator<Edge> iter = edge.iterator();
		while(iter.hasNext()) { 
			temp = iter.next();
			if (test.containsKey(temp.getSource()) 
					&& test.containsKey(temp.getDestination()) 
					&& temp.getWeight() >= 0) {
				test.get(temp.getSource()).add(temp);
			}
		}
		System.out.println(test);
		
	}

}
