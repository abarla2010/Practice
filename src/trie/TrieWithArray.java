package trie;

import node.TrieNodeWithArray;

public class TrieWithArray {

  TrieNodeWithArray root;

  public TrieWithArray(TrieNodeWithArray root){
    this.root = root;
  }

  public void insert(String word) {
    if (word == null || word.length() == 0) {
      return;
    }
    TrieNodeWithArray[] children = root.children;
    TrieNodeWithArray currNode = null;
    for (int i = 0; i < word.length(); i++) {
      char currChar = word.charAt(i);
      if (children[currChar - 'a'] != null) {
        currNode = children[currChar - 'a'];
      } else {
        currNode = new TrieNodeWithArray(currChar);
        children[currChar - 'a'] = currNode;
      }

      children = currNode.children;
    }

    currNode.isLeaf = true;
  }

  public boolean search(String word) {
    TrieNodeWithArray node = searchNode(word);
    if (node == null) {
      return false;
    }
    return node.isLeaf;
  }

  public boolean startsWithPrefix(String word) {
    TrieNodeWithArray node = searchNode(word);
    return (node != null);
  }

  public TrieNodeWithArray searchNode(String word) {
    if (word == null || word.length() == 0) {
      return null;
    }
    TrieNodeWithArray[] children = root.children;
    TrieNodeWithArray currNode = null;

    for (int i = 0; i < word.length(); i++) {
      char currChar = word.charAt(i);
      if (children[currChar - 'a'] != null) {
        currNode = children[currChar - 'a'];
      } else {
        return currNode;
      }

      children = currNode.children;
    }

    return currNode;
  }

}
