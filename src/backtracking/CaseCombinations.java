package backtracking;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 * Find all the combinations of a string in lowercase and uppercase.
 * For example, string "ab" >>> "ab", "Ab", "aB", "AB".
 * So, you will have 2^n (n = number of chars in the string) output strings.
 * The goal is for you to test each of these strings and see if it matches a hidden string.
 */
public class CaseCombinations {

  @Test
  public void test(){
    List<String> result = allCombinations("ab");
    assertEquals(4, result.size());
    assertTrue(result.contains("ab"));
    assertTrue(result.contains("aB"));
    assertTrue(result.contains("Ab"));
    assertTrue(result.contains("AB"));

    List<String> res = allCombinations("AirBnB");
    assertEquals(64, res.size());
    assertTrue(res.contains("airbnb"));
    assertTrue(res.contains("Airbnb"));
    assertTrue(res.contains("aIRBNB"));
    assertTrue(res.contains("AIRBNB"));
  }

  public List<String> allCombinations(String input){
    List<String> res = new ArrayList<String>();
    if(input == null || input.isEmpty()) return res;
    StringBuilder sb = new StringBuilder();
    helper(input.toLowerCase(), 0, sb, res);
    return res;
  }

  private void helper(String s, int start, StringBuilder sb, List<String> res){
    if(start > s.length()) return;
    if(sb.length() == s.length()){
      res.add(new String(sb.toString()));
    } else {
      for(int i = start; i < s.length(); i++){
        // lowercase permuation
        sb.append(s.charAt(i));
        helper(s, i + 1, sb, res);
        sb.deleteCharAt(sb.length() - 1);

        // uppercase permuation
        String temp =  "" + s.charAt(i);
        sb.append(temp.toUpperCase());
        helper(s, i + 1, sb, res);
        sb.deleteCharAt(sb.length() - 1);
      }
    }
  }
}
