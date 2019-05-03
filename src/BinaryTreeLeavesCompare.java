import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import node.TreeNode;

public class BinaryTreeLeavesCompare {


  public boolean areSameLeaves(TreeNode node1, TreeNode node2){
    if(node1 == null && node2 == null) return true;
    if(node1 == null || node2 == null) return false;
    return getLeaves(node1).equals(getLeaves(node2).size());
  }

  List<Integer> getLeaves(TreeNode root){
    if(root == null) return new ArrayList<Integer>();
    Queue<TreeNode> q1 = new LinkedList<TreeNode>();
    Queue<TreeNode> q2 = new LinkedList<TreeNode>();
    List<Integer> leaves = new ArrayList<Integer>();
    q1.offer(root);
    while(!q1.isEmpty()){
      TreeNode temp = q1.remove();
      if(temp.left == null && temp.right == null){
        leaves.add(temp.val);
      }
      if(temp.left != null){
        q2.offer(temp.left);
      }

      if(temp.right != null){
        q2.offer(temp.right);
      }

      if(q1.isEmpty()){
        q1 = q2;
      }
    }

    return leaves;
  }
}
