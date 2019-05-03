package tree.traversals;

import java.util.Stack;
import node.TreeNode;

public class TreeTraversals {

  public void inOrder(TreeNode root){
    inOrderRecursive(root);
  }

  public void preOrder(TreeNode root) {
    preOrderRecursive(root);
  }

  private void preOrderRecursive(TreeNode root){
    if(root == null) return;
    System.out.print(root.val + " ");
    preOrderRecursive(root.left);
    preOrderRecursive(root.right);
  }

  private void preOrderIterative(TreeNode root){

  }

  private void inOrderRecursive(TreeNode root){
    inOrderRecursive(root.left);
    System.out.print(root.val + " ");
    inOrderRecursive(root.right);
  }

  private void inOrderIterative(TreeNode root){
    if(root == null) return;
    Stack<TreeNode> s = new Stack<>();
    TreeNode curr = root;
    while(curr != null || !s.isEmpty()){
      while(curr != null){
        s.push(curr);
        curr = curr.left;
      }
      curr = s.pop();
      System.out.print(curr.val + " ");
      curr = curr.right;
    }
  }

}
