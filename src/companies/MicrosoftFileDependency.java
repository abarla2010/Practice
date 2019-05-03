package companies;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://reginaldlong.com/compile-a-file-and-its-dependencies/
 * Ps: It's topological sort
 */
public class MicrosoftFileDependency {

  public static void main(String[] args){

    Node file1 = new Node("assert.h");
    Node file2 = new Node("stdlib.h");
    file2.dependencies.add(file1);
    Node file3 = new Node("stdio.h");
    file3.dependencies.add(file2);
    file2.dependencies.add(file3);
    Node file4 = new Node("time.h");
    Node file5 = new Node("main.c");
    file5.dependencies.add(file2);
    file5.dependencies.add(file3);
    file5.dependencies.add(file4);

    List<String> res = findCompilationOrder(new ArrayList<Node>(Arrays.asList(file1, file2, file3, file4, file5)), 5);
    for(String s : res){
      System.out.print(s + ", ");
    }
  }


  public static List<String> findCompilationOrder(List<Node> nodes, int n){
    List<String> res = new ArrayList<String>();
    if(nodes == null || nodes.size() == 0) return res;

    int[] indegree = new int[n];
    Map<String, Set<String>> map = new HashMap<String, Set<String>>();
    for(Node node : nodes){
      if(!map.containsKey(node.file)){
        map.put(node.file, new HashSet<String>());
      }

      Set<String> set = map.get(node.file);
      for(Node tempNode : node.dependencies){
        set.add(tempNode.file);
      }
      map.put(node.file, set);
    }

    Deque<String> queue = new ArrayDeque<String>();
    Set<String> visited = new HashSet<String>();
    for(Map.Entry<String, Set<String>> e : map.entrySet()){
      if(e.getValue().size() == 0){
        queue.addFirst(e.getKey());
      }
    }

    while(!queue.isEmpty()){
      String currFile = queue.removeLast();
      visited.add(currFile);

      for(Map.Entry<String, Set<String>> e: map.entrySet()){
        if(e.getValue().contains(currFile)){
          e.getValue().remove(currFile);
          if(e.getValue().size() == 0) queue.addFirst(e.getKey());
        }
      }

      res.add(currFile);
    }

    return res;
  }

  static class Node {
    String file;
    List<Node> dependencies = new ArrayList<Node>();

    public Node(String file) {
      this.file = file;
    }
  }
}
