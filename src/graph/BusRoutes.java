package graph;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
/**
 * https://leetcode.com/problems/bus-routes/description/
 */
public class BusRoutes {

  @Test
  public void test(){
    int[][] arr = new int[][]{
        {1, 2, 7},
        {3, 6, 7}
    };

    int res = numBusesToDestination(arr, 1, 6);
    assertEquals(2, res);
  }

  public int numBusesToDestination(int[][] routes, int S, int T){
    if(routes == null || routes.length == 0) return -1;

    Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
    Set<Integer> visitedBuses = new HashSet<Integer>();
    Deque<Integer> queue = new ArrayDeque<Integer>();

    for(int i = 0; i < routes.length; i++){
      for(int j = 0; j < routes[i].length; j++){
        List<Integer> busesInThisBusStop = map.getOrDefault(routes[i][j], new ArrayList<Integer>());
        busesInThisBusStop.add(i);
        map.put(routes[i][j], busesInThisBusStop);
      }
    }

    queue.addFirst(S);
    int level = 0;
    while(!queue.isEmpty()){
      level++;
      int size = queue.size();

      while(size-- > 0){
        int currStop = queue.removeLast();

        // Get all buses for this stop
        for(int bus : map.get(currStop)){
          if(visitedBuses.contains(bus)) continue;
          visitedBuses.add(bus);


          // Get all the stops for this bus
          for(int j = 0; j < routes[bus].length; j++) {
            if (routes[bus][j] == T)
              return level;
            queue.addFirst(routes[bus][j]);
          }
        }
      }
    }

    return -1;
  }
}
