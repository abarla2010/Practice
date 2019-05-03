package unionfind;

import java.util.HashMap;
import java.util.Map;

public class FriendsCircle {

  public static void main(String[] args){
    int[][] arr = new int[][]{
        {1,1,0},
        {1,1,1},
        {0,0,1}
    };

    System.out.print(findCircleNum(arr));
  }

  static class UnionNode {
    public UnionNode parent;
    public int val;
    public int rank;
    public UnionNode(int val){
      this.val = val;
    }
  }

  static class UnionFind {
    Map<Integer, UnionNode> map = new HashMap<Integer, UnionNode>();
    int groups = 0;
    public UnionFind(int N){
      groups = N;
    }

    public int find(int a){
      UnionNode curr = map.get(a);

      while(map.get(curr.val).parent.val != curr.val){
        curr = curr.parent;
      }

      return curr.val;
    }

    public void union(int p, int q){

      if(!map.containsKey(p)){
        UnionNode temp = new UnionNode(p);
        temp.parent = temp;
        map.put(p, temp);
      }

      if(!map.containsKey(q)){
        UnionNode tempQ = new UnionNode(q);
        tempQ.parent = tempQ;
        map.put(q, tempQ);
      }


      int rootPVal = find(p);
      int rootQVal = find(q);
      if(rootPVal == rootQVal) return;

      UnionNode rootP = map.get(rootPVal);
      UnionNode rootQ = map.get(rootQVal);

      if(rootP.rank > rootQ.rank){
        rootQ.parent = rootP;
        if(rootQ.rank == rootP.rank)
          rootP.rank++;
      } else {
        rootP.parent = rootQ;
        if(rootQ.rank == rootP.rank)
          rootQ.rank++;
      }
      groups--;
    }

    public int getNumberOfGroups(){
      return groups;
    }
  }

  public static int findCircleNum(int[][] M) {
    if(M == null || M.length == 0 || M[0].length == 0) return 0;

    int N = M.length;
    UnionFind uf = new UnionFind(N);

    for(int i = 0; i < M.length; i++){
      for(int j = 0; j < M[i].length; j++){
        if(M[i][j] == 1) uf.union(i, j);
      }
    }

    return uf.getNumberOfGroups();
  }
}
