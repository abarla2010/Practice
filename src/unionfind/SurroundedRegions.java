package unionfind;

import java.util.*;

/**
 * https://leetcode.com/problems/surrounded-regions/description/
 */
public class SurroundedRegions {

  private int rows;
  private int cols;

  public void solve(char[][] board) {
    if(board == null || board.length == 0 || board[0].length == 0) return;
    rows = board.length;
    cols = board[0].length;

    // last one is dummy, all outer O are connected to this dummy
    UnionFind uf = new UnionFind(rows * cols + 1);
    int dummyNode = rows * cols;

    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols; j++){
        if(board[i][j] == 'O'){
          //if 'O' is at the boundary, connect it to the dummy node
          if(i == 0 || i == rows - 1 || j == 0 || j == cols - 1){
            uf.union(getPosition(i, j), dummyNode);
          } else {
            if(i > 0 && board[i - 1][j] == 'O') uf.union(getPosition(i, j), getPosition(i - 1, j));
            if(i < rows && board[i + 1][j] == 'O') uf.union(getPosition(i, j), getPosition(i + 1, j));
            if(j > 0 && board[i][j - 1] == 'O') uf.union(getPosition(i, j), getPosition(i, j - 1));
            if(j < cols && board[i][j + 1] == 'O') uf.union(getPosition(i, j), getPosition(i, j + 1));
          }
        }
      }
    }


    // If any 'O' is connected to the dummyNode that means it's connected to a 'O' node or is the 'O' that's
    // on the boundary, so mark it as 'O'
    // capture all others
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols; j++){
        if(uf.isConnected(getPosition(i, j), dummyNode)) board[i][j] = 'O';
        else board[i][j] = 'X';
      }
    }
  }


  // since the board is of characters, there needs to be some way to convert their
  // positions on the board to int
  // to do this, we need to find a unique way to convert (i, j) into a single integer
  // using any arbitary number like 100 and multiplying i or j would fail in some cases (where there are more than 100 rows/cols), so use the number of rows or cols
  private int getPosition(int i, int j) {
    return i + j * rows; //return i * rows + j also works!
  }


  class UnionFind {
    class Node {
      int val;
      Node parent;
      int rank;
      Node(int val){
        this.val = val;
      }
    }

    Map<Integer, Node> map = new HashMap<Integer, Node>();
    int n;

    UnionFind(int n){
      this.n = n;
    }

    public int find(int x){
      if(!map.containsKey(x)){
        Node node = new Node(x);
        node.parent = node;
        node.rank++;
        map.put(x, node);
      }

      Node curr = map.get(x);

      while(curr != curr.parent) curr = curr.parent;
      return curr.val;
    }

    public boolean isConnected(int x, int y){
      return find(x) == find(y);
    }

    public void union(int x, int y){
      int xParent = find(x);
      int yParent = find(y);
      Node xParentNode = map.get(xParent);
      Node yParentNode = map.get(yParent);

      if(xParentNode == yParentNode) return;

      if(xParentNode.rank < yParentNode.rank){
        xParentNode.parent = yParentNode;
        yParentNode.rank += xParentNode.rank;
      } else {
        yParentNode.parent = xParentNode;
        xParentNode.rank += yParentNode.rank;
      }

      n--;
    }
  }
}
