package unionfind;

import java.util.*;

/**
 * https://leetcode.com/problems/number-of-islands-ii/description/
 */
public class NumberOfIslands2 {

  public static void main(String[] args){
    int[][] positions = new int[][]{{0, 0}, {0, 1}, {1, 2}, {2, 1}};
    List<Integer> res = numIslands2(3, 3, positions);

    for(Integer i : res){
      System.out.print(i + " ");
    }
  }

  static class UFNode{
    public int pos;
    public UFNode parent;
    public UFNode(int pos){
      this.pos = pos;
    }
  }

  private static int m;
  private static int n;
  private static int islands;
  private static Map<Integer, UFNode> map = new HashMap<Integer, UFNode>();
  private static int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

  public static List<Integer> numIslands2(int rows, int cols, int[][] positions) {
    List<Integer> res = new ArrayList<Integer>();
    if(positions == null || positions.length == 0 || positions[0].length == 0) return res;
    m = rows;
    n = cols;

    int[][] graph = new int[rows][cols];

    for(int[] land : positions){
      unionNeighbors(land, graph);
      res.add(islands);
    }

    return res;
  }

  private static void unionNeighbors(int[] land, int[][] graph){
    int pos = getPosition(land[0], land[1]);
    find(pos);
    graph[land[0]][land[1]] = 1;

    for(int[] dir : directions){
      int x = land[0] + dir[0];
      int y = land[1] + dir[1];
      int newPos = getPosition(x, y);

      if(x >= 0 && y >=0 && x < m && y < n && graph[x][y] == 1){
        union(pos, newPos);
      }
    }
  }


  private static int find(int p){
    UFNode currNode = null;
    if(!map.containsKey(p)){
      currNode = new UFNode(p);
      currNode.parent = currNode;
      map.put(p, currNode);
      islands++;
    }

    currNode = map.get(p);
    while(currNode != currNode.parent) currNode = currNode.parent;
    return currNode.pos;
  }

  private static void union(int p, int q){
    UFNode pParent = map.get(find(p));
    UFNode qParent = map.get(find(q));

    if(pParent == qParent) return;

    pParent.parent = qParent;
    islands--;
  }

  // x*m + y or x + y*n makes the position unique
  private static int getPosition(int x, int y){
    return x*m + y;
  }
}
