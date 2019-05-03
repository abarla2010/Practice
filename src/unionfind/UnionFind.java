package unionfind;

import java.util.HashMap;
import java.util.Map;
import node.UnionFindNode;

public class UnionFind {

  int numberOfGroups = 0;
  Map<Integer, UnionFindNode> valToNodeMap = new HashMap<Integer, UnionFindNode>();

  public UnionFind(int n){
    this.numberOfGroups = n;
  }

  // checks if x and y are in the same group
  public boolean connected(int x, int y){
    return find(x) == find(y);
  }

  public int find(int x){
    UnionFindNode curr = null;
    if(!valToNodeMap.containsKey(x)){
      curr = new UnionFindNode(x);
      curr.parent = curr;
      curr.rank++;
      valToNodeMap.put(x, curr);
    } else {
      curr = valToNodeMap.get(x);
    }

    UnionFindNode temp = curr;
    //Find the representative of the group and return that
    while(temp.parent != temp){
      temp = temp.parent;
    }

    //Now temp is the representative of the group
    // But, to compress the path, we need to assign it as
    // the curr's parent. Therefore, curr is now one level below the representative of the
    // group
    curr.parent = temp;

    return temp.val;
  }

  public void union(int x, int y){
    int repX = find(x);
    int repY = find(y);
    UnionFindNode repXNode = valToNodeMap.get(repX);
    UnionFindNode repYNode = valToNodeMap.get(repY);

    // If they're already in the same group
    if(repXNode == repYNode) return;

    if(repXNode.rank <= repYNode.rank){
      repXNode.parent = repYNode;
      repYNode.rank++;
    } else {
      repYNode.parent = repXNode;
      repXNode.rank++;
    }

    numberOfGroups--;
  }
}
