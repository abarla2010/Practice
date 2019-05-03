package bfs;

import java.util.*;

/**
 * https://leetcode.com/problems/open-the-lock/description/
 */
public class OpenTheLock {

  public static void main(String[] args){

    String[] deads = new String[]{"8888"};
    int res = openLock(deads, "0009");
    System.out.println(res);
  }

  public static int openLock(String[] deadends, String target) {
    if(deadends == null) return -1;
    Set<String> deadEndsSet = new HashSet<String>(Arrays.asList(deadends));
    Set<String> visited = new HashSet<String>();
    Deque<String> queue = new ArrayDeque<String>();
    String initial = "0000";
    int level = 0;

    if(!deadEndsSet.contains(initial)) {
      queue.addFirst(initial);
      visited.add(initial);
    }

    while(!queue.isEmpty()){
      int size = queue.size();
      while(size-- > 0){
        String curr = queue.removeLast();

        if(curr.equals(target)) return level;

        StringBuilder sb = new StringBuilder(curr);

        for(int i = 0; i < 4; i++){
          char[] chars  = curr.toCharArray();
          chars[i] = (char) (chars[i] == '9' ? '0' : chars[i] + 1);
          String movedRightStr = new String(chars);
          if(!visited.contains(movedRightStr) && !deadEndsSet.contains(movedRightStr)){
            queue.addFirst(movedRightStr);
            visited.add(movedRightStr);
          }

          chars = curr.toCharArray();
          chars[i] = (char)(chars[i] == '0' ? '9' : chars[i] - 1);
          String movedLeftStr = new String(chars);
          if(!visited.contains(movedLeftStr) && !deadEndsSet.contains(movedLeftStr)){
            queue.addFirst(movedLeftStr);
            visited.add(movedLeftStr);
          }
        }
      }

      level++;
    }

    return -1;
  }

}
