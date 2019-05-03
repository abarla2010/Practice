package tree.ds;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class SuffixTree {

  class Node {
    int[] data;
    int lastIdx = 0;
    int START_SIZE = 0;
    int INCREMENT = 1;
    Node suffix;


    Map<Character, Edge> edges;

    Node(){
      suffix = null;
      edges = new TreeMap<Character, Edge>();
      data = new int[START_SIZE];
    }
  }

  class Edge {
    String label;
    Node dest;
  }

  class Pair {
    Node node;
    String s;

    Pair(Node node, String s){
      this.node = node;
      this.s = s;
    }
  }

  Node root = new Node();

  public Collection<Integer> search(String word){
    return search(word, -1);
  }

  public Collection<Integer> search(String word, int i){
    return null;
  }

  public Node searchNode(String word){
    return null;
  }

  public void put(String key, int index){

  }

  public Pair canonize(final Node node, final String word){

    return null;
  }
}
