package backtracking;

import java.util.*;

public class NextClosestTime {

  public static void main(String[] args){
    String res = nextClosestTime("11:11");
  }

  // find all valid times, then find the closest one out of them
  public static String nextClosestTime(String time) {
    if(time == null || time.length() != 5) return time;

    //remove colon from time
    int colonIndex = time.indexOf(':');
    if(colonIndex == - 1) return null;
    time = time.substring(0, colonIndex) + time.substring(colonIndex + 1);

    int[] digits = new int[4];
    for(int i = 0; i < 4; i++){
      digits[i] = Integer.parseInt(String.valueOf(time.charAt(i)));
    }

    Set<String> permutations = new HashSet<String>();

    // get all possible permutations
    dfs(digits, permutations, new StringBuilder());

    List<String> permsList = new ArrayList<String>(permutations);
    if(permsList.size() == 0) return null;
    Collections.sort(permsList); // sorting is needed to get the next closest time

    int indexOfTime = permsList.indexOf(time);

    // Since the list is sorted, if this is the last element in the list, then return the first
    // else return the next
    String res = "";
    if(indexOfTime == permsList.size() - 1) res = permsList.get(0);
    else res = permsList.get(indexOfTime + 1);

    res = res.substring(0, 2) + ":" + res.substring(2);
    return res;
  }

  private static void dfs(int[] digits, Set<String> permutations, StringBuilder sb){
    if(sb.length() > 4) return;
    if(sb.length() == 4){
      permutations.add(sb.toString());
      return;
    }

    for(int i = 0; i < digits.length; i++){
      if(isValid(digits[i], sb)){
        sb.append(digits[i]);
        dfs(digits, permutations, sb);
        sb.deleteCharAt(sb.length() - 1);
      }
    }
  }

  private static boolean isValid(int val, StringBuilder sb){
    if(sb == null) return false;
    if(sb.length() == 0 && val > 2) return false;
    if(sb.length() == 1 && sb.charAt(0) == '2' && val > 3) return false;
    if(sb.length() == 2 && val > 5) return false;
    return true;
  }

}
