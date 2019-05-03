package dynamicprogramming;

import java.util.*;
import org.junit.*;
import static  org.junit.Assert.*;


/**
 * https://leetcode.com/problems/coin-change/description/
 */
public class CoinChange {


  @Test
  public void test(){
    int[] coins = new int[]{1,2,5};
    int res = coinChange(coins, 11);
    assertEquals(3, res);
  }


  // BFS approach
  public int coinChange(int[] coins, int amount){
    if(coins == null || coins.length == 0 || amount < 1) return 0;

    Deque<Integer> queue = new ArrayDeque<Integer>();
    Set<Integer> visited = new HashSet<Integer>();
    queue.addFirst(amount);
    visited.add(amount);
    int level = 0;

    while(!queue.isEmpty()){
      int size = queue.size();

      while(size-- > 0){
        int curr = queue.removeLast();
        if(curr == 0) return level;

        if(curr < 0) continue;

        for(int coin : coins){
          int next = curr - coin;
          if(next >= 0 && !visited.contains(next)){
            queue.addFirst(next);
            visited.add(next);
          }
        }
      }

      level++;
    }

    return -1;
  }
}
