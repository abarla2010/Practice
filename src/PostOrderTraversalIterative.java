import java.util.Stack;
import node.TreeNode;

public class PostOrderTraversalIterative {


  public void postOrder(TreeNode node){
    if(node == null) return;
    Stack<TreeNode> s = new Stack<>();
    TreeNode curr;
    TreeNode prev = null;
    s.push(node);

    while(!s.isEmpty()){
      curr = s.peek();
      if((prev == null) || (prev.right == curr) || (prev.left == curr)){
        //Going down
        if(curr.left!=null){
          s.push(curr.left);
        } else if (curr.right != null){
          s.push(curr.right);
        } else {
          s.pop();
          System.out.print(curr.val + " ");
        }
      }
      else if(curr.left == prev){
        //Going up - LEFT
        if(curr.right != null){
          s.push(curr.right);
        } else {
          s.pop();
          System.out.print(curr.val + " ");
        }
      } else if (curr.right == prev){
        //Going up - RIGHT
        s.pop();
        System.out.print(curr.val + " ");
      }

      prev = curr;
    }
  }
}
