import java.util.LinkedList;
import java.util.Queue;
import node.TreeNode;

public class SerializeBinaryTree {
  public String serialize(TreeNode root) {
    if(root == null) return "[]";
    Queue<TreeNode> q1 = new LinkedList<TreeNode>();
    Queue<TreeNode> q2 = new LinkedList<TreeNode>();
    q1.add(root);
    String s = "";
    while(!q1.isEmpty()){
      while(!q1.isEmpty()){
        TreeNode tempNode = q1.remove();
        if(tempNode.left != null){
          q2.add(tempNode.left);
        }
        if(tempNode.right != null){
          q2.add(tempNode.right);
        }

        if(s.length()==0){
          s += "[" + tempNode.val;
        } else {
          s += "," + tempNode.val;
        }
      }


      if(q2.isEmpty()){
        s += "]";
      }
      Queue<TreeNode> tempQ = q1;
      q1 = q2;
      q2 = tempQ;
    }
    return s;
  }

  public TreeNode deserialize(String data) {
    if(data == null || data.length() < 3) return null;
    String d = data.substring(1,data.length()-1);
    String[] arr = d.split(",");
    TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
    TreeNode node = root;
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    q.add(root);
    int counter = 0;
    for(int i = 1; i < arr.length; i++){
      if(counter % 2 == 0){
        node = q.remove();
      }
      counter++;

      if(node.val == Integer.MAX_VALUE){
        continue;
      }

      if(i % 2 != 0){
        if(arr[i].equals("null")){
          node.left = null;
          q.add(new TreeNode(Integer.MAX_VALUE));
        } else {
          TreeNode temp = new TreeNode(Integer.parseInt(arr[i]));
          node.left = temp;
          q.add(temp);
        }
      } else {
        if(arr[i].equals("null")){
          node.right = null;
          q.add(new TreeNode(Integer.MAX_VALUE));
        } else {
          TreeNode temp = new TreeNode(Integer.parseInt(arr[i]));
          node.right = temp;
          q.add(temp);
        }
      }
    }


    q = null;
    return root;
  }
}
