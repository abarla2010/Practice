package backtracking;

import java.util.*;
import org.junit.*;;
import static org.junit.Assert.*;

/**
 * https://leetcode.com/problems/word-pattern-ii/description/
 */
public class WordPatternII {

  @Test
  public void test(){
    //boolean res = wordPatternMatch1("abab", "redblueredblue");
    boolean res1 = wordPatternMatch1("aabb", "xyzabcxzyabc");
//    boolean res2 = wordPatternMatch1("abab", "xyabxyab");
//    boolean res3 = wordPatternMatch1("", "xyabxyab");
//    boolean res4 = wordPatternMatch1(null, "xyabxyab");

      assertFalse(res1);
//    assertTrue(res1);
//    assertTrue(res2);
//    assertFalse(res3);
//    assertFalse(res4);
  }


  public boolean wordPatternMatch1(String pattern, String str) {
    if(pattern == null || str == null) return false;
    if(pattern.isEmpty() && str.isEmpty()) return true;
    if(pattern.isEmpty()) return str.isEmpty();
    if(str.isEmpty()) return pattern.isEmpty();
    Map<Character, String> map = new HashMap<Character, String>();
    return helper(map, pattern, str, 0, 0);
  }

  private boolean helper(Map<Character, String> map, String pattern, String str, int strIndex, int patternIndex){
    if(strIndex > str.length() || patternIndex > pattern.length()) return false;
    if(strIndex == str.length()) return true;
    if(patternIndex == pattern.length()) return false;

    char patternChar = pattern.charAt(patternIndex);

    if(map.containsKey(patternChar)){
      String temp = map.get(patternChar);
      if(strIndex + temp.length() > str.length()) return false; // remaining string


      if(!temp.equals(str.substring(strIndex, strIndex + temp.length()))) return false;

      //check remaining string
      if(helper(map, pattern, str, strIndex + temp.length(), patternIndex + 1)) return true;

    } else {
      for(int i = strIndex + 1; i <= str.length(); i++){

        //choose
//        System.out.println("String Index: " + strIndex);
//        System.out.println("Pattern Index: " + patternIndex);
//        System.out.println("Pattern char: " + patternChar);
//        System.out.println("String substring: " + str.substring(strIndex, i));


        map.put(patternChar, str.substring(strIndex, i));

//        System.out.println("New String Index: " + strIndex + i);
//        System.out.println("New Pattern Index: " + patternIndex + 1);
//        System.out.println(Arrays.toString(map.entrySet().toArray()));

        //explore
        if(helper(map, pattern, str, strIndex + i, patternIndex + 1)) return true;

        //unchoose
        map.remove(patternChar);
      }
    }

    return false;
  }



  // Do the Word Pattern problem first
  public boolean wordPatternMatch(String pattern, String str) {
    if(pattern == null || str == null) return false;
    if(pattern.isEmpty()) return str.isEmpty();
    Map<Character, String> map = new HashMap<Character, String>();
    Set<String> visited = new HashSet<String>();
    return isMatch(pattern, str, map, visited);
  }

  private boolean isMatch(String pattern, String str, Map<Character, String> map, Set<String> visited){
    if(pattern.isEmpty()) return str.isEmpty();
    char currChar = pattern.charAt(0);

    if(map.containsKey(currChar)){
      // This means that the currChar in pattern has a substring in str associated with it
      // check if we break the str at this point, does it follow the pattern or not
      String currStr = map.get(currChar);
      int currStrLen = currStr.length();
      if(str.length() < currStrLen) return false;  // check if the str has enough length left in it
      if(!str.substring(0, currStrLen).equals(currStr)) return false; // check if the str formed till now matches pattern

      //recurse down to check the remaining
      if(isMatch(pattern.substring(1), str.substring(currStr.length()), map, visited)) return true;
    } else {
      for(int i = 1; i <= str.length(); i++){

        String currStr = str.substring(0, i);
        if(visited.contains(currStr)) continue;

        //choose
        map.put(currChar, currStr);
        visited.add(currStr);

        //explore
        if(isMatch(pattern.substring(1), str.substring(i), map, visited)) return true;

        //unchoose
        visited.remove(currStr);
        map.remove(currChar);
      }
    }

    return false;
  }

}
