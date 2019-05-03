package graph;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

// https://leetcode.com/problems/alien-dictionary/description/
public class AlienDictionary {

  public static String alienOrder(String[] words) {
    if (words == null || words.length == 0) {
      return null;
    }

    StringBuilder sb = new StringBuilder();

    Map<Character, Set<Character>> relMap = new HashMap<>();
    Map<Character, Integer> inDegree = new HashMap<Character, Integer>();
    Deque<Character> queue = new ArrayDeque<>();

    for (String s : words) {
      for (char c : s.toCharArray()) {
        if (!inDegree.containsKey(c)) {
          inDegree.put(c, 0);
        }
      }
    }

    populateRelationships(relMap, words, inDegree);

    for (Map.Entry<Character, Integer> e : inDegree.entrySet()) {
      if (e.getValue() == 0) {
        queue.addFirst(e.getKey());
      }
    }

    while (!queue.isEmpty()) {
      Character curr = queue.removeLast();
      sb.append(curr);
      if (!relMap.containsKey(curr)) {
        continue;
      }
      for (char c : relMap.get(curr)) {
        inDegree.put(c, inDegree.get(c) - 1);
        if (inDegree.get(c) == 0) {
          queue.addFirst(c);
        }
      }
    }

//    if (sb.length() != inDegree.size()) {
//      return "";
//    }

    return sb.toString();
  }

  private static void populateRelationships(Map<Character, Set<Character>> map, String[] words,
      Map<Character, Integer> inDegree) {
    for (int i = 0; i < words.length - 1; i++) {
      String curr = words[i];
      String next = words[i + 1];

      int minLen = Math.min(curr.length(), next.length());
      for (int x = 0; x < minLen; x++) {
        char currC = curr.charAt(x);
        char nextC = next.charAt(x);
        if (currC != nextC) {
          if (!map.containsKey(currC)) {
            map.put(currC, new HashSet<>());
          }
          Set<Character> set = map.get(currC);
          if (!set.contains(nextC)) {
            set.add(nextC);
            map.put(currC, set);
            inDegree.put(nextC, inDegree.get(nextC) + 1);
          }
          //break;
        }
      }
    }
  }

  @Test
  public void test() {
    String[] words = new String[]{"z", "x"};
    String[] words1 = new String[]{"z", "x", "z"};
    String[] words2 = new String[]{"xaq", "xaf", "ea", "eqq", "afq"};

    String res = alienOrder(words);
    String res1 = alienOrder(words1);
    String res2 = alienOrder(words2);

    assertEquals("zx", res);
    assertEquals("", res1);
    assertEquals("xeaqf", res2);
  }
}