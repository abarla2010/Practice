package node;

public class DijkstraNode {

  public String value;
  public Edge[] adjacencies;
  public int shortestDistanceFromSource = Integer.MAX_VALUE;
  public DijkstraNode parent;

  public DijkstraNode(String value) {
    this.value = value;
  }


  public static class Edge {

    public DijkstraNode target;
    public int weight;

    public Edge(DijkstraNode target, int weight){
      this.target = target;
      this.weight = weight;
    }
  }
}
