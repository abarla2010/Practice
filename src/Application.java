import graph.AllNodesShortestPaths;
import graph.Dijkstra;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import node.DijkstraNode;
import node.DijkstraNode.Edge;
import node.TreeNode;
import node.TrieNodeWithArray;
import node.TrieNodeWithHashMap;
import trie.TrieWithArray;

public class Application {

  public static void main(String[] args) {
    AllNodesShortestPaths shortestPaths = new AllNodesShortestPaths();
    int[][] graph = new int[][]{
        {0, 4, 0, 0, 0, 0, 0, 8, 0},
        {4, 0, 8, 0, 0, 0, 0, 11, 0},
        {0, 8, 0, 7, 0, 4, 0, 0, 2},
        {0, 0, 7, 0, 9, 14, 0, 0, 0},
        {0, 0, 0, 9, 0, 10, 0, 0, 0},
        {0, 0, 4, 14, 10, 0, 2, 0, 0},
        {0, 0, 0, 0, 0, 2, 0, 1, 6},
        {8, 11, 0, 0, 0, 0, 1, 0, 7},
        {0, 0, 2, 0, 0, 0, 6, 7, 0}
    };

    //shortestPaths.dijkstra(graph, 0);

    System.out.print(longestPalindrome("a"));

  }

  public static String longestPalindrome(String s) {
    if(s == null || s.length() == 0) return s;

    int maxLen = 0;
    String maxPalindrome = "";

    for(int i = 0; i < s.length(); i ++){
      for(int j = i; j < s.length(); j++){
        String temp = s.substring(i, j + 1);
        if(isPalindrome(temp)){
          if(j - i >= maxLen){
            maxPalindrome = temp;
            maxLen = j - i;
          }
        }
      }
    }

    return maxPalindrome;
  }

  private static boolean isPalindrome(String s){
    if(s == null || s.length() == 0) return false;
    int i = 0;
    int j = s.length() - 1;

    while(i < j){
      if(s.charAt(i) != s.charAt(j)) return false;
      i++;
      j--;
    }

    return true;
  }

  public static boolean ConvertToNumber(String str) {
    if (str == null || str.length() < 1) {
      return false;
    }
    boolean canConvert = false;
    boolean retVal = false;

    try {
      int n = Integer.parseInt(str);
      if (n != 0) {
        canConvert = true;
      }
    } catch (Exception ex) {
      System.out.println("Exception occurred while trying to convert string to number: " + ex);
    }

    if (canConvert) {
      retVal = true;
    }

    return retVal;
  }

  // Iterative method to do level order traversal line by line
  static void printLevelOrder(TreeNode root) {
    // Base Case
    if (root == null) {
      return;
    }

    // Create an empty queue for level order traversal
    Queue<TreeNode> q = new LinkedList<TreeNode>();

    // Enqueue Root and initialize height
    q.add(root);

    while (true) {

      // nodeCount (queue size) indicates number of nodes
      // at current level.
      int nodeCount = q.size();
      if (nodeCount == 0) {
        break;
      }

      // Dequeue all nodes of current level and Enqueue all
      // nodes of next level
      while (nodeCount > 0) {
        TreeNode node = q.peek();
        System.out.print(node.val + " ");
        q.remove();
        if (node.left != null) {
          q.add(node.left);
        }
        if (node.right != null) {
          q.add(node.right);
        }
        nodeCount--;
      }
      System.out.println();
    }
  }

//  static void printVerticalOrder(TrieNode root){
//    Map<Integer, List<TrieNode>> map = new TreeMap<>();
//    verticalOrderHelper(root, map, 0);
//
//    for(Entry<Integer, List<TrieNode>> e : map.entrySet()){
//      List<TrieNode> level = e.getValue();
//      for(TrieNode n : level){
//        System.out.print(n.c + " ");
//      }
//      System.out.println();
//    }
//  }

//  static void verticalOrderHelper(TrieNode node, Map<Integer, List<TrieNode>> map, Integer level){
//    if(node == null) return;
//
//    HashMap<Character, TrieNode> children = node.children;
//    int size = children.size();
//    int midpoint = size/2;
//    int i = 0;
//    List<TrieNode> mapList = map.getOrDefault(level, new ArrayList<>());
//    mapList.add(node);
//    map.put(level, mapList);
//    for(Entry<Character, TrieNode> e : children.entrySet()){
//      verticalOrderHelper(e.getValue(), map, level - midpoint + i);
//    }
//  }

  static void printLevelOrder(TrieNodeWithHashMap root) {
    if (root == null) {
      return;
    }
    Queue<TrieNodeWithHashMap> q = new LinkedList<TrieNodeWithHashMap>();
    TrieNodeWithHashMap curr;
    q.offer(root);
    while (!q.isEmpty()) {
      int size = q.size();
      while (size-- > 0) {
        TrieNodeWithHashMap temp = q.remove();
        for (Entry<Character, TrieNodeWithHashMap> e : temp.children.entrySet()) {
          System.out.print(e.getKey() + " ");
          q.offer(e.getValue());
        }
      }

      System.out.println();
    }
  }

  static List<Double> averageOfLevels(TreeNode root) {
    if (root == null) {
      return new ArrayList<Double>();
    }
    Queue<TreeNode> q1 = new LinkedList<TreeNode>();
    Queue<TreeNode> q2 = new LinkedList<TreeNode>();
    List<Integer> level = new ArrayList<Integer>();
    List<Double> levelAverages = new ArrayList<Double>();
    q1.add(root);
    while (!q1.isEmpty()) {
      TreeNode temp = q1.remove();
      if (temp.left != null) {
        q2.add(temp.left);
      }
      if (temp.right != null) {
        q2.add(temp.right);
      }
      level.add(temp.val);

      if (q1.isEmpty()) {
        Queue<TreeNode> tempQ = q1;
        q1 = q2;
        q2 = tempQ;
        Double levelSum = 0.0;
        for (Integer i : level) {
          levelSum += Double.valueOf(i);
        }
        levelAverages.add(levelSum / Double.valueOf(level.size()));
        level = new ArrayList<Integer>();
      }
    }

    return levelAverages;
  }
}
