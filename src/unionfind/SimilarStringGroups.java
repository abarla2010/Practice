package unionfind;

import java.util.*;

/**
 * https://leetcode.com/problems/similar-string-groups/description/
 */
public class SimilarStringGroups {


  public static void main(String[] args){
    String[] a = new String[]{"aa", "aa"};
    int res = numSimilarGroups(a);
    System.out.println(res);
  }

  static class Node {
    String val;
    Node parent;
    Node(String val){
      this.val = val;
    }
  }

  private static Map<String, Node> map = new HashMap<String, Node>();
  private static int numberOfGroups = 0;

  public static int numSimilarGroups(String[] A) {
    if(A == null || A.length == 0) return 0;
    numberOfGroups = A.length;

    // group nodes, if the strings are similar
    for(int i = 0; i < A.length; i++){
      for(int j = i + 1; j < A.length; j++){
        if(areSimilar(A[i], A[j])) union(A[i], A[j]);
      }
    }

    return numberOfGroups;
  }

  // checks if a can be transformed into b by a swap of any two chars
  // to do this, all the characters in the two strings should be equal, except two.
  // when the number of different characters exceeds 2, then we can return false, else true
  private static boolean areSimilar(String a, String b){
    int n = 0;
    for(int i = 0; i < a.length(); i++){
      if(a.charAt(i) != b.charAt(i)){
        n++;

        if(n > 2) return false;
      }
    }

    return true;
  }

  private static String find(String curr){
    if(!map.containsKey(curr)){
      Node n = new Node(curr);
      n.parent = n;
      map.put(curr, n);
    }

    Node currNode = map.get(curr);
    while(currNode != currNode.parent){
      currNode = currNode.parent;
    }

    return currNode.val;
  }

  private static void union(String a, String b){
    Node aParent = map.get(find(a));
    Node bParent = map.get(find(b));

    if(aParent == bParent) return;

    aParent.parent = bParent;
    numberOfGroups--;
  }
}
