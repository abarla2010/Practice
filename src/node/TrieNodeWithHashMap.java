package node;

import java.util.HashMap;

public class TrieNodeWithHashMap {
  public char c;
  public HashMap<Character, TrieNodeWithHashMap> children = new HashMap<Character, TrieNodeWithHashMap>();
  public boolean isLeaf;

  public TrieNodeWithHashMap(){
  }

  public TrieNodeWithHashMap(char c){
    this.c = c;
  }
}
