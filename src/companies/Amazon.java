package companies;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import node.TreeNode;

public class Amazon {

  public static void main(String[] args) {

//    int[] values = new int[]{5,6,3,1,2,4};
//////    findDistance(values, 6, 3, 4);
//////
//////

    System.out.println(findDistinctSubstrings("abcd", 3));

  }


  private static List<String> findDistinctSubstrings(String input, int k){
    List<String> res = new ArrayList<String>();
    if(input == null || input.length() < k) return res;

    for(int i = 0; i + k - 1 < input.length(); i++){
      if(hasKDistinctCharacters(input, i, i + k - 1)){
        res.add(input.substring(i, i + k));
      }
    }

    return res;
  }

  private static boolean hasKDistinctCharacters(String input, int start, int end){
    Set<Character> set = new HashSet<>();
    for(int i = start; i <= end; i++){
      char c  = input.charAt(i);
      if(set.contains(c)) return false;
      set.add(c);
    }

    return true;
  }



  //construct a BST in the order of the elements in values and then find the distance between
  // node1 and node2 in that tree
  private static int findDistance(int[] values, int n, int node1, int node2) {
    if (values == null || values.length == 0) {
      return -1;
    }
    Set<Integer> set = new HashSet<Integer>();
    Deque<Integer> queue = new ArrayDeque<Integer>();

    for (int i : values) {
      set.add(i);
      queue.addFirst(i);
    }
    if (!set.contains(node1) || !set.contains(node2)) {
      return -1;
    }

    TreeNode root = new TreeNode(queue.removeLast());
    while(!queue.isEmpty()) {
      constructBST(queue, root);
    }

    //printLevelOrder(root);



    return 0;
  }

  private static TreeNode constructBST(Deque<Integer> queue, TreeNode root) {
    if (queue.isEmpty()) {
      return null;
    }
    if (root == null) {
      return new TreeNode(queue.removeLast());
    }

    int currVal = queue.getLast();

    if (currVal < root.val) {
      root.left = constructBST(queue, root.left);
    } else {
      root.right = constructBST(queue, root.right);
    }

    return root;
  }

  private static void printLevelOrder(TreeNode root) {
    Deque<TreeNode> queue = new ArrayDeque<>();
    queue.addFirst(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size-- > 0) {
        TreeNode curr = queue.removeLast();
        System.out.print(curr.val + ", ");
        if (curr.left != null) {
          queue.addFirst(curr.left);
        }
        if (curr.right != null) {
          queue.addFirst(curr.right);
        }
      }
      System.out.println();
    }

  }

}
