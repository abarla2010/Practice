package node;

public class TrieNodeWithArray {

  public char c;
  public TrieNodeWithArray[] children = new TrieNodeWithArray[26];
  public boolean isLeaf;

  public TrieNodeWithArray(){}

  public TrieNodeWithArray(char c){
    this.c = c;
  }
}
