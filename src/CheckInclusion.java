import java.util.HashMap;
import java.util.Map;

public class CheckInclusion {

  public static void main(String[] args){
    String s1 = "abcdxabcde";
    String s2 = "abcdeabcdx";
    System.out.println(checkInclusion(s1, s2));
  }

  public static boolean checkInclusion(String s1, String s2) {
    if(s1 == null || s2 == null || s1.length() > s2.length()) return false;
    Map<Character, Integer> map = new HashMap<Character, Integer>();
    for(char c : s1.toCharArray()){
      map.put(c, map.getOrDefault(c, 0) + 1);
    }

    Map<Character, Integer> tempMap = new HashMap<Character, Integer>();
    tempMap.putAll(map);
    int windowSize = s1.length();
    if(windowSize == 0) return true;

    for(int i = 0; i < s2.length() - windowSize + 1; i++){
      for(char c : s2.substring(i, i + windowSize).toCharArray()){
        if(!tempMap.containsKey(c)) break;
        tempMap.put(c, map.get(c) - 1);
        if(tempMap.get(c) == 0) tempMap.remove(c);
      }

      if(tempMap.size() == 0) return true;
      tempMap.clear();
      tempMap.putAll(map);
    }

    return false;
  }

}
