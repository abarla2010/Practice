package dynamicprogramming;

import java.util.*;

/**
 * https://leetcode.com/problems/decode-ways/description/
 * My solution: https://leetcode.com/problems/decode-ways/discuss/199694/Java-AC-Memoized-Recursion
 */
public class DecodeWays {

  public static void main(String[] args){
    int res = numDecodings("1234");
    System.out.println(res);
  }

  public static int numDecodings(String s) {
    if(s == null || s.length() == 0) return 0;

    // Index to NumOfWays mapping
    Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

    return helper(s, 0, memo);
  }

  private static int helper(String s, int start, Map<Integer, Integer> memo){
    if(start > s.length() + 1) return 0;
    if(start == s.length()){
      return 1;
    }

    int total = 0;

    if(memo.containsKey(start)) return memo.get(start);

    //choose single digit
    String currStr = s.substring(start, start + 1);
    Integer currInt = Integer.valueOf(currStr);
    if(currInt == 0) return 0;
    if(currInt >= 1 && currInt <= 26)
      total = helper(s, start + 1, memo);

    //choose two digits (if possible)
    if(start < s.length() - 1){
      currStr = s.substring(start, start + 2);
      currInt = Integer.valueOf(currStr);
      if(currInt >= 1 && currInt <= 26)
        total += helper(s, start + 2, memo);
    }

    memo.put(start, total);
    return total;
  }

}
