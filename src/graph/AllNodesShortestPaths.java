package graph;

import java.util.Arrays;

// Using Dijkstra's algorithm
public class AllNodesShortestPaths {

  int len = 0;


  public void printShortestPaths(int dist[], int n){
    System.out.println("Vertex   Distance from Source");
    for (int i = 0; i < len; i++)
      System.out.println(" " + i + "                  " + dist[i]);
  }

  //Finds the next non-visited min value vertex and returns it index
  private int minDistance(int[] dist, boolean[] visited){
    int minValue = Integer.MAX_VALUE;
    int minIndex = -1;

    for(int i = 0; i < len; i++){
      if(!visited[i] && dist[i] < minValue){
          minValue = dist[i];
          minIndex = i;
      }
    }

    return minIndex;
  }

  public void dijkstra(int graph[][], int src){
    len = graph.length;
    int[] dist = new int[len];
    boolean[] visited = new boolean[len];

    Arrays.fill(dist, Integer.MAX_VALUE);
    Arrays.fill(visited, false);

    dist[src] = 0;
    for(int i = 0; i < len; i++){
      int nextMinIndex = minDistance(dist, visited);
      visited[nextMinIndex] = true;

      // For each of the adjacent vertices, update the dist value
      // Update only if the index is not visited
      // and if there is an edge from the nextMinIndex to its adjacent vertices
      // and if dist[nextMinIndex] != Integer.MAX_VALUE, that means it's processed but there's no shortest path
      // and if total weight from nextMinIndex to its neighbor is smaller than the neighbor's current shortest distance
      for(int j = 0; j < len; j++){
        if(!visited[j] &&
            graph[nextMinIndex][j] != 0 &&
            dist[nextMinIndex] != Integer.MAX_VALUE &&
            dist[nextMinIndex] + graph[nextMinIndex][j] < dist[j]
            ){
          dist[j] = dist[nextMinIndex] + graph[nextMinIndex][j];
        }
      }
    }

    printShortestPaths(dist, len);
  }
}
