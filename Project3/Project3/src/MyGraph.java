import java.util.*;

/**
 * A representation of a graph. Assumes that we do not have negative cost edges
 * in the graph.
 */
public class MyGraph implements Graph {
	// you will need some private fields to represent the graph
	// you are also likely to want some private helper methods
	
	private Collection<Vertex> myVertex;
	
	private HashSet<Vertex> tempVertex = new HashSet<Vertex>();
	
	private Collection<Edge> myEdge;
	
	private HashMap<Vertex, ArrayList<Edge>> myMap;

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
			throw new IllegalArgumentException("Invalid Edges."); 
		} 
		myEdge = e; 

	}

	/**
	 * Return the collection of vertices of this graph
	 * 
	 * @return the vertices as a collection (which is anything iterable)
	 */
	@Override
	public Collection<Vertex> vertices() {
		Collection<Vertex> temp = new HashSet<Vertex>();
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
	 * @param v the collection of vertex objects
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
					myMap.put(temp, new ArrayList<Edge>());
				}
			}	
			return true;
		}
	}
	
	private void addToMap(HashSet<Vertex> v) { 
		Vertex temp; 
		Iterator<Vertex> iter = v.iterator();
		while (iter.hasNext()){	
			temp = iter.next(); 
			if(!myMap.containsKey(temp)) {
				myMap.put(temp, new ArrayList<Edge>());
			}
		}	
	}
	
	/**
	 * Checks collection of edges for improper elements
	 * 
	 * @param e the collection of edge objects
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
					&& temp.getWeight() >= 0
					&& !dupEdge(e, temp)
					&& temp.getSource() != temp.getDestination()) {
				myMap.get(temp.getSource()).add(temp);
			} else { 
				flag = false;
			}
		}
		return flag; 		
	}
	
	/**
	 * 
	 * @param e
	 * @param edge
	 * @return
	 */
	private boolean dupEdge(Collection<Edge> e, Edge edge) {
		boolean flag = false;
		for(Edge temp: e) {
			if(temp.getSource().equals(edge.getSource())
					&& temp.getDestination().equals(edge.getDestination())
					&& temp.getWeight() != edge.getWeight()) { 
				flag = true;
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
		Collection<Vertex> temp = new ArrayList<Vertex>();
		if(myMap.containsKey(v)){ 
			//HAVE TO LOOK FOR THE VERTEX
			Iterator<Edge> iter = myMap.get(v).iterator();
			while (iter.hasNext()){
				temp.add(iter.next().getDestination()); 			
			} 
		}
		return temp;

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
		
		//FIX THE SELF EDGE PROBLEM
		int cost = 0; 
		Collection<Vertex> vert = new ArrayList<Vertex>();
		Collection<Edge> edge = new ArrayList<Edge>();
		
		if(myMap.containsKey(a)) { 
			vert = adjacentVertices(a);
			if(vert.contains(b)) { 
				//look for the edge that has destination as b
				edge = myMap.get(a);
				for(Edge e: edge) { 
					if (e.getDestination().equals(b)) { 
						cost = e.getWeight();
					}
				}
			}
		} else { 
			cost = -1;
		}
		
		return cost; 
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
		if (a.equals(b)) {
			LinkedList<Vertex> result = new LinkedList<Vertex>();
			result.add(a);
			Path rPath = new Path(result, 0);
			return rPath;
		} else {
			for (Vertex eachV : myVertex) {
				eachV.cost = Integer.MAX_VALUE;
				eachV.known = false;	
			}
			localizeV(a).cost = 0;
			localizeV(a).path = null;
			PriorityQueue<Vertex> vert = new PriorityQueue<Vertex>(myVertex);
			while (!vert.isEmpty()) {
				Vertex curr = vert.poll();
				localizeV(curr).known = true;
				for ( Vertex eaEdge : this.adjacentVertices(curr)) {
					eaEdge = localizeV(eaEdge);
					if (!eaEdge.known) {
						if (curr.cost  + this.edgeCost(curr, eaEdge) < eaEdge.cost) {
							eaEdge.cost = (curr.cost  + this.edgeCost(curr, eaEdge)); 
							eaEdge.path = curr;
							vert.remove(eaEdge);
							vert.add(eaEdge);
						}
					}
				}
			}
			return tracePath(b);
		}
	}
	
/**
 * Takes in a passed in Vertex and assures it points to its version in myVertex.	
 * @param query Vertex to alter
 * @return MyVertex's copy of query.
 */
private Vertex localizeV(Vertex query) {
	for (Vertex eV : myVertex) {
		if (eV.equals(query)) {
			query = eV;
		}
	}
	return query;
}

/**
 * Used to return the shortest path calculated by shortestPath
 * @param b Destination Vertex
 * @return Path to the destination Vertex.
 */
private Path tracePath(Vertex b) {
	LinkedList<Vertex> result = new LinkedList<Vertex>();
	b = localizeV(b);
	if (b.path == null) {
		return null;
	} else {
		int pathSum = 0;
		while (b!= null) {
			result.addFirst(b);
			if (edgeCost(b.path, b) != -1) {
				pathSum += edgeCost(b.path, b);
			}
			b = b.path;
		}
		return new Path(result, pathSum);
	}
}
	public static void main (String[] args) {
		Vertex vA = new Vertex("A");
		Vertex vv = new Vertex("A");
		Vertex vB = new Vertex("B");
		Vertex vC = new Vertex("C");
		Vertex vD = new Vertex("D");
		Vertex vE = new Vertex("E");
		Vertex vF = new Vertex("F");
		Vertex vG = new Vertex("G");
		
		ArrayList<Vertex> vert = new ArrayList<Vertex>();
		vert.add(vA);
		vert.add(vB);
		vert.add(vC);
		vert.add(vD);
		vert.add(vE);
		vert.add(vF);
		vert.add(vG);
		vert.add(vv);
		
		ArrayList<Edge> edge = new ArrayList<Edge>();
		edge.add(new Edge(vA, vC, 2));
		edge.add(new Edge(vA, vD, 1));
		edge.add(new Edge(vB, vA, 2));
		edge.add(new Edge(vC, vD, 1));
		edge.add(new Edge(vC, vF, 2));
		edge.add(new Edge(vD, vB, 5));
		edge.add(new Edge(vD, vE, 1));
		edge.add(new Edge(vD, vG, 5));
		edge.add(new Edge(vD, vF, 6));
		edge.add(new Edge(vE, vB, 1));
		edge.add(new Edge(vF, vG, 10));
		edge.add(new Edge(vG, vE, 3));
		
		MyGraph graph = new MyGraph(vert, edge);
		graph.printMap();
		System.out.println(graph.vertices());
		graph.shortestPath(vA, vB);
		System.out.println("A: " + vA.path);
		System.out.println("B: " + vB.path);
		System.out.println("C: " + vC.path);
		System.out.println("D: " + vD.path);
		System.out.println("E: " + vE.path);
		System.out.println("F: " + vF.path);
		System.out.println("G: " + vG.path);
		
		
		
		/*Vertex vert1 = new Vertex("one");
		Vertex vert2 = new Vertex("two");
		Vertex vert3 = new Vertex("three");
		Vertex vert4 = new Vertex("four");
		
		ArrayList<Vertex> vert = new ArrayList<Vertex>();
		vert.add(vert1);
		vert.add(vert2);
		vert.add(vert3);
		vert.add(vert4);
		//System.out.println(vert);
		
		ArrayList<Edge> edge = new ArrayList<Edge>();
		edge.add(new Edge(vert1, vert4, 3000));
		edge.add(new Edge(vert1, vert2, 6000));
		edge.add(new Edge(vert2, vert4, 1));
		
		MyGraph graph = new MyGraph(vert, edge);
		graph.printMap();
		System.out.println(graph.edgeCost(vert1, vert2));
		*/
		
		/**USE THIS TO MESS WITH CHECKVERTEX/EDGE INDEPENDENTLY**/
//		HashMap<Vertex, ArrayList<Edge>> test = new HashMap<Vertex, ArrayList<Edge>>();
//		test.put(vert1, new ArrayList<Edge>());
//		test.put(vert2, new ArrayList<Edge>());
//		Edge temp; 
//		Iterator<Edge> iter = edge.iterator();
//		while(iter.hasNext()) { 
//			temp = iter.next();
//			if (test.containsKey(temp.getSource()) 
//					&& test.containsKey(temp.getDestination()) 
//					&& temp.getWeight() >= 0) {
//				test.get(temp.getSource()).add(temp);
//			}
//		}
//		System.out.println(test);
		
	}

}
