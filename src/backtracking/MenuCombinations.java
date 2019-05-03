package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * FAQ in AirBnB
 *
 * Given a list of menu items and prices. Print all combinations that match a target price.
 * Eg: target = $3, Menu( A:$1 , B:$2)
 *
 * Print
 * A,A,A
 * A,B
 *
 * But no B,A
 *
 */
public class MenuCombinations {

  class Tuple {
    String item;
    double cost;

    Tuple(double cost, String item){
      this.item = item;
      this.cost = cost;
    }
  }

  class TupleInt{
    String item;
    int cost;

    TupleInt(int cost, String item){
      this.cost = cost;
      this.item = item;
    }
  }

  @Test
  public void test(){
    List<Tuple> list = new ArrayList<>(Arrays.asList(
        new Tuple(1.00, "A"),
        new Tuple(2.00, "B"),
        new Tuple(3.00, "C"),
        new Tuple(1.49, "D"),
        new Tuple(1.51, "E"),
        new Tuple(1.40, "F"),
        new Tuple(0.50, "G")
    ));

    List<List<String>> res = findAllCombinations(list, 3.00);

//    for(List<String> innerList : res){
//      for(String s: innerList){
//        System.out.print(s + " ");
//      }
//
//      System.out.println();
//    }

    assertEquals(res.size(), 8);
    assertTrue(res.contains(new ArrayList<>(Arrays.asList("A", "A", "A"))));
    assertTrue(res.contains(new ArrayList<>(Arrays.asList("A", "B"))));
    assertTrue(res.contains(new ArrayList<>(Arrays.asList("D", "E"))));
    assertTrue(res.contains(new ArrayList<>(Arrays.asList("G", "G", "G", "G", "G", "G"))));
    assertTrue(res.contains(new ArrayList<>(Arrays.asList("G", "G", "A", "A"))));
    assertTrue(res.contains(new ArrayList<>(Arrays.asList("G", "G", "B"))));
    assertTrue(res.contains(new ArrayList<>(Arrays.asList("C"))));
  }

  public List<List<String>> findAllCombinations(List<Tuple> input, double target){
    List<List<String>> res = new ArrayList<List<String>>();
    if(input == null || input.size() == 0) return res;


    //since the input and the target have double, rounding it is easier
    int centsTarget = (int) Math.round(target * 100);
    List<TupleInt> inputInt = new ArrayList<>();

    for(int i = 0 ; i < input.size(); i++){
      Tuple t = input.get(i);
      inputInt.add(i, new TupleInt((int) Math.round(input.get(i).cost * 100), t.item));
    }

    Collections.sort(inputInt, ((a,b) -> a.cost - b.cost));

//    System.out.println("Printing List");
//    for(Tuple t : input){
//      System.out.print(t.item + " " + t.cost);
//      System.out.println();
//    }


    helper(inputInt, 0, centsTarget, res, new ArrayList<String>());
    return res;
  }

  private void helper(List<TupleInt> inputInt, int start, int target, List<List<String>> res, List<String> tempList) {
    if (target < 0)
      return;
    if (target == 0) {
      res.add(new ArrayList<String>(tempList));
    } else {
      for (int i = start; i < inputInt.size(); i++) {
        tempList.add(inputInt.get(i).item);
        helper(inputInt, i, target - inputInt.get(i).cost, res, tempList);
        tempList.remove(tempList.size() - 1);
      }
    }
  }
}
