package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import org.junit.Test;
import static org.junit.Assert.*;

//https://leetcode.com/problems/cheapest-flights-within-k-stops/
public class CheapestFlightsWithKStops {

  public int findCheapestPrice(int n, int[][] flights, int src, int dist, int K) {
    if(flights == null || flights.length == 0 || flights[0].length == 0 || K < 0 || src < 0 || dist < 0 || n < 1) return -1;
    Map<Integer, Map<Integer, Integer>> prices = new HashMap<Integer, Map<Integer, Integer>>();
    for(int[] flight : flights){
      int source = flight[0];
      int destination = flight[1];
      int price = flight[2];
      if(!prices.containsKey(source)) prices.put(source, new HashMap<Integer, Integer>());
      prices.get(source).put(destination, price);
    }

    Queue<int[]> pq = new PriorityQueue<int[]>((a,b) -> a[0] - b[0]);

    // constructing graph node object to be used in the following BFS. (0, src, k) stands for (price till now, station, numOfStops). During BFS, he uses price to keep track of the current price, and use station to determine if the current node we are visiting is the destination, and also use the numOfStops to keep track of how many stops are there in order to reach to this station
    pq.add(new int[]{0, src, K});

    while(!pq.isEmpty()){
      int[] curr = pq.remove();
      int currPrice = curr[0];
      int currCity = curr[1];
      int currStops = curr[2];

      if(currCity == dist) return currPrice;
      if(currStops >= 0){
        Map<Integer, Integer> adj = prices.getOrDefault(currCity, new HashMap<Integer, Integer>());
        for(int city : adj.keySet()){
          pq.add(new int[]{currPrice + adj.get(city), city, currStops - 1});
        }
      }
    }

    return -1;
  }

  @Test
  public void test(){

    int[][] flights1 = new int[][]{
        {0,1,50},
        {1,2,50},
        {0,2,200}
    };

    int res = findCheapestPrice(3, flights1, 0, 2, 1);
    assertEquals(100, res);

    int res1 = findCheapestPrice(3, flights1, 0, 2, 0);
    assertEquals(200, res1);

  }
}
