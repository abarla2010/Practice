package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import node.DijkstraNode;
import node.DijkstraNode.Edge;

public class Dijkstra {

  public static void computePaths(DijkstraNode source) {
    if (source == null) {
      return;
    }
    source.shortestDistanceFromSource = 0;

    PriorityQueue<DijkstraNode> pq = new PriorityQueue<DijkstraNode>(
        (a, b) -> a.shortestDistanceFromSource - b.shortestDistanceFromSource
    );

    pq.add(source);

    while (!pq.isEmpty()) {
      DijkstraNode temp = pq.poll();

      //Visit the closest node (least weight)
      for (Edge e : temp.adjacencies) {
        DijkstraNode node = e.target;
        int nodeWeight = e.weight;

        int shortestDistanceFromSource = temp.shortestDistanceFromSource + nodeWeight;
        if (shortestDistanceFromSource < node.shortestDistanceFromSource) {
          pq.remove(node);
          node.shortestDistanceFromSource = shortestDistanceFromSource;
          node.parent = temp;
          pq.add(node);
        }
      }
    }
  }

  public static List<DijkstraNode> getShortestPathTo(DijkstraNode target) {
    List<DijkstraNode> res = new ArrayList<DijkstraNode>();
    for (DijkstraNode node = target; node != null; node = node.parent) {
      res.add(node);
    }

    Collections.reverse(res);
    return res;
  }


  // Sample Data
  /*
      //initialize the graph base on the Romania map
    DijkstraNode n1 = new DijkstraNode("Arad");
    DijkstraNode n2 = new DijkstraNode("Zerind");
    DijkstraNode n3 = new DijkstraNode("Oradea");
    DijkstraNode n4 = new DijkstraNode("Sibiu");
    DijkstraNode n5 = new DijkstraNode("Fagaras");
    DijkstraNode n6 = new DijkstraNode("Rimnicu Vilcea");
    DijkstraNode n7 = new DijkstraNode("Pitesti");
    DijkstraNode n8 = new DijkstraNode("Timisoara");
    DijkstraNode n9 = new DijkstraNode("Lugoj");
    DijkstraNode n10 = new DijkstraNode("Mehadia");
    DijkstraNode n11 = new DijkstraNode("Drobeta");
    DijkstraNode n12 = new DijkstraNode("Craiova");
    DijkstraNode n13 = new DijkstraNode("Bucharest");
    DijkstraNode n14 = new DijkstraNode("Giurgiu");

    //initialize the edges
    n1.adjacencies = new Edge[]{
        new Edge(n2,75),
        new Edge(n4,140),
        new Edge(n8,118)
    };

    n2.adjacencies = new Edge[]{
        new Edge(n1,75),
        new Edge(n3,71)
    };

    n3.adjacencies = new Edge[]{
        new Edge(n2,71),
        new Edge(n4,151)
    };

    n4.adjacencies = new Edge[]{
        new Edge(n1,140),
        new Edge(n5,99),
        new Edge(n3,151),
        new Edge(n6,80),
    };

    n5.adjacencies = new Edge[]{
        new Edge(n4,99),
        new Edge(n13,211)
    };

    n6.adjacencies = new Edge[]{
        new Edge(n4,80),
        new Edge(n7,97),
        new Edge(n12,146)
    };

    n7.adjacencies = new Edge[]{
        new Edge(n6,97),
        new Edge(n13,101),
        new Edge(n12,138)
    };

    n8.adjacencies = new Edge[]{
        new Edge(n1,118),
        new Edge(n9,111)
    };

    n9.adjacencies = new Edge[]{
        new Edge(n8,111),
        new Edge(n10,70)
    };

    n10.adjacencies = new Edge[]{
        new Edge(n9,70),
        new Edge(n11,75)
    };

    n11.adjacencies = new Edge[]{
        new Edge(n10,75),
        new Edge(n12,120)
    };

    n12.adjacencies = new Edge[]{
        new Edge(n11,120),
        new Edge(n6,146),
        new Edge(n7,138)
    };

    n13.adjacencies = new Edge[]{
        new Edge(n7,101),
        new Edge(n14,90),
        new Edge(n5,211)
    };

    n14.adjacencies = new Edge[]{
        new Edge(n13,90)
    };

    DijkstraNode[] nodes = {n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14};

    //compute paths
    Dijkstra.computePaths(n1);

    //print shortest paths

		for(Node n: nodes){
			System.out.println("Distance to " +
				n + ": " + n.shortestDistance);
    		List<Node> path = getShortestPathTo(n);
    		System.out.println("Path: " + path);
		}

  List<DijkstraNode> path = Dijkstra.getShortestPathTo(n13);
    for(DijkstraNode n : path)
      System.out.println(n.value);
   */

}
