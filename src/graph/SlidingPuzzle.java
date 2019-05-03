package graph;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

/**
 * https://leetcode.com/problems/sliding-puzzle/description/
 */
public class SlidingPuzzle {

  @Test
  public void test(){
    int[][] arr = new int[][]{{4,1,2}, {5,0,3}};
    int res = slidingPuzzle(arr);
    assertEquals(5, res);
  }

  public int slidingPuzzle(int[][] board){
    if(board == null || board.length != 2 || board[0].length != 3) return -1;
    String target = "123450";
    String start = "";

    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[0].length; j++){
        start += board[i][j];
      }
    }

    Set<String> visited = new HashSet<String>();
    Deque<String> queue = new ArrayDeque<String>();

    // when zero is in position:0 it can go to cell {1,3}, position:1 it can go {0,2,4} and so on,
    // also when i mean position i'm talking about the index of zero in the string not the input array.
    int[][] directions = new int[][]{
        {1, 3},
        {0, 2, 4},
        {1, 5},
        {0, 4},
        {1, 3, 5},
        {2, 4}
    };


    // each level signifies one transformation
    // BFS finds the shortest path, so that level is the shortest level
    int level = 0;
    queue.addFirst(start);
    visited.add(start);

    while(!queue.isEmpty()){
      int size = queue.size();

      while(size-- > 0){
        String curr = queue.removeLast();
        if(curr.equals(target)) return level;

        int zeroIndex = curr.indexOf('0');

        // for all possible places that zero can move from the zeroIndex, swap
        for(int dir : directions[zeroIndex]){
          String next = swap(curr, zeroIndex, dir);
          if(visited.contains(next)) continue;
          visited.add(next);
          queue.addFirst(next);
        }
      }

      level++;
    }

    return -1;
  }

  private String swap(String str, int i, int j){
    if(str == null) return str;
    char[] arr = str.toCharArray();
    char temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
    return new String(arr);
  }
}
