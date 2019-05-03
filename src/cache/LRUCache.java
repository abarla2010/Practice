package cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

  public static void main(String[] args){

  }

  Map<Integer, Node> map = new HashMap<Integer, Node>();
  int capacity;
  int count;
  Node head;
  Node tail;

  LRUCache(int capacity){
    this.capacity = capacity;
    this.head = new Node(0, 0);
    this.tail = new Node(0, 0);
    head.next = tail;
    tail.prev = head;
  }

  public int get(int key){
    if(!map.containsKey(key)) return -1;

    Node node = map.get(key);
    deleteNode(node);
    addToHead(node);
    return node.value;
  }

  public void put(int key, int value){
    if(map.containsKey(key)){
      Node node = map.get(key);
      node.value = value;
      deleteNode(node);
      addToHead(node);
    } else {
      Node node = new Node(key, value);
      map.put(key, node);
      if(count < capacity){
        addToHead(node);
        count++;
      } else {
        addToHead(node);
        map.remove(tail.prev.key);
        deleteNode(tail.prev);
        count++;
      }
    }
  }

  private void deleteNode(Node node){
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }

  private void addToHead(Node node){
    node.next = head.next;
    head.next.prev = node;
    head.next = node;
    node.prev = head;
  }

  class Node {
    int key;
    int value;
    Node prev;
    Node next;

    public Node(int key, int value){
      this.key  = key;
      this.value = value;
    }
  }
}
