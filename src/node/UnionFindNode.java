package node;

public class UnionFindNode {
  public UnionFindNode parent;
  public int rank;
  public int val;

  public UnionFindNode(int val){
    this.val = val;
  }
}
