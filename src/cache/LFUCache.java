package cache;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {

  class Node {
    int key;
    int value;
    int frequency;
    Node prev;
    Node next;

    Node(int key, int value) {
      this.key = key;
      this.value = value;
      this.frequency = 1;
    }
  }


  private int capacity;
  private int count;
  private Node head;
  private Node tail;
  private Map<Integer, Node> map = new HashMap<>();

  public LFUCache(int capacity){
    this.capacity = capacity;

  }
}
