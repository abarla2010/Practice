package bfs;

import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * https://leetcode.com/problems/perfect-squares/description/
 */
public class PerfectSquares {

  @Test
  public void test(){

    assertEquals(3, numSquares(12));
    assertEquals(2, numSquares(13));
  }


  public int numSquares(int n) {
    if(n < 0) return 0;
    Deque<Integer> queue = new ArrayDeque<Integer>();
    int level = 0;
    queue.addFirst(n);
    while(!queue.isEmpty()){
      int size = queue.size();
      level++;

      while(size-- > 0){
        int curr = queue.removeLast();
        if(curr < 0) continue;

        for(int i = curr; i >= 1; i--){
          // deal only with perfect squares
          int sqRoot = (int) Math.sqrt(i);
          if(sqRoot * sqRoot == i){
            int temp = curr - i;
            if(temp == 0) return level;
            if(temp > 0) queue.addFirst(temp);
          }
        }
      }
    }

    return 0;
  }

}
