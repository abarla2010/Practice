package trie;

import java.util.HashMap;
import node.TrieNodeWithHashMap;

public class TrieWithHashMap {

  TrieNodeWithHashMap root;

  public TrieWithHashMap(){
    this.root = new TrieNodeWithHashMap();
  }

  public TrieWithHashMap(TrieNodeWithHashMap root){
    this.root = root;
  }

  public void insert(String word){
    if(word == null || word.length() < 1) return;
    HashMap<Character, TrieNodeWithHashMap> children = root.children;

    for(int i = 0; i < word.length(); i++) {
      Character currChar = word.charAt(i);
      TrieNodeWithHashMap currNode;
      if (children.containsKey(currChar)) {
        currNode = children.get(currChar);
      } else {
        currNode = new TrieNodeWithHashMap(currChar);
        children.put(currChar, currNode);
      }

      children = currNode.children;


      // Reached last char, so set isLeaf
      if (i == word.length() - 1) {
        currNode.isLeaf = true;
      }
    }
  }

  public boolean search(String word){
    TrieNodeWithHashMap t = searchNode(word);
    if(t != null && t.isLeaf) return true;
    return false;
  }

  public boolean startsWithPrefix(String word){
    return (searchNode(word) == null) ? false : true;
  }

  public TrieNodeWithHashMap searchNode(String word){
    if(word == null || word.length() < 1) return null;

    HashMap<Character, TrieNodeWithHashMap> children = root.children;

    TrieNodeWithHashMap finalNode = null;
    for(int i = 0; i < word.length(); i++){
      Character c = word.charAt(i);
      if(!children.containsKey(c)){
        return finalNode;
      } else {
        finalNode = children.get(c);
        children = finalNode.children;
      }
    }

    return finalNode;
  }
}
