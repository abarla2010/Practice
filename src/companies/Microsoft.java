package companies;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Microsoft {

  public static void main(String[] args){
//    List<Integer> res = reverseFibonnaci(0, 10);
//    for(Integer i : res){
//      System.out.print(i + " ");
//    }

//    String[] input = new String[]{"A", "B", "C", "D", "E"};
//    populateMap();
//    OrderProductsByPriority(input);
//    for(String s: input){
//      System.out.print(s + ", ");
//    }
  }

  //Given an array of alphanumeric product codes, sort them by priority
  private static void OrderProductsByPriority(String[] products){
    if(products == null || products.length == 0) return;
    Map<Integer, List<String>> priorityMap = new TreeMap<Integer, List<String>>();

    for(String s : products){
      int priority = getPriority(s);

      if(!priorityMap.containsKey(priority)){
        priorityMap.put(priority, new ArrayList<>());
      }

      priorityMap.get(priority).add(s);
    }

    int i = 0;
    for(Map.Entry<Integer, List<String>> e : priorityMap.entrySet()){
      for(String s : e.getValue()){
        products[i] = s;
        i++;
      }
    }
  }

  static Map<String, Integer> map = new HashMap<String, Integer>();

  private static void populateMap(){
    map.put("A", 1);
    map.put("C", 1);
    map.put("D", 2);
    map.put("E", 2);
    map.put("B", 3);
  }


  private static int getPriority(String productCode){
    return map.get(productCode);
  }

  // Since the question doesn't specific when to stop,
  // assuming that the next 10 numbers are generated as output
  private static List<Integer> reverseFibonnaci(int a, int b){
    List<Integer> res = new ArrayList<Integer>();
    Deque<Integer> stack = new ArrayDeque<Integer>();

    int prev2 = a;
    int prev1 = b;
    stack.push(prev2);
    stack.push(prev1);
    for(int i = 0; i < 10; i++){
      int sum = prev1 + prev2;
      stack.push(sum);
      prev2 = prev1;
      prev1 = sum;
    }

    while(!stack.isEmpty()){
      res.add(stack.pop());
    }


    return res;
  }

}
