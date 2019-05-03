package companies;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//import org.junit.Assert;
//import org.junit.Test;

public class Salesforce {

  private static int numWinningWays = 0;

  public static void main(String[] args) {
    int res = consecutive(15);
    System.out.println(res);

//    List<Integer> elements = new ArrayList<>();
//    elements.add(1);
//    elements.add(3);
//    elements.add(2);
//    elements.add(2);
//    elements.add(0);
//    elements.add(0);
////      elements.add(3);
////      elements.add(4);
////      elements.add(2);


    //System.out.println(maxPoints(elements));
  }

  public static int consecutive(long num) {
    if (num < 0) return numWinningWays;

    for(int i = 1; i <= num / 2; i++){
      searchAllPossibleCombinations(num, 0,  i);
    }

    return numWinningWays;
  }

  private static void searchAllPossibleCombinations(long num, long currentSum, long nextNum){
    if(currentSum > num){
      //Exceeded the winning number so return
      return;
    } else if (currentSum == num){
      //Found a group of consecutive numbers that add up to the winning number
      numWinningWays++;
    } else {
        //explore
        searchAllPossibleCombinations(num, currentSum + nextNum, nextNum + 1);
    }
  }


//  private static long maxPoints(List<Integer> elements) {
//    if(elements == null || elements.size() == 0) return 0;
//
//    long maxValue = 0;
//    for(int i = elements.size() - 1; i >= 0; i--){
//      long currVal = maxPoints(elements, elements.get(i), i);
//      maxValue = Math.max(maxValue, currVal);
//    }
//
//    return maxValue;
//  }
//
//  private static long maxPoints(List<Integer> elements, long prevHouseCars, int i){
//    if(i < 0) return 0;
//
//    int currHouseCars = elements.get(i);
//
//    // If number of cars in this house is +/- 1 the number of cars
//    // in previous house, then skip
//    if(Math.abs(prevHouseCars - currHouseCars) == 1) return prevHouseCars;
//
//    long maxPointsFromPreviousHouses = maxPoints(elements, prevHouseCars, i - 1);
//
//    return Math.max(currHouseCars + maxPointsFromPreviousHouses, maxPointsFromPreviousHouses);
//  }


    private static long maxPoints(List<Integer> elements){
      if(elements == null || elements.size() == 0) return 0;

      long maxValue = 0;

      for(int i = 0; i < elements.size(); i++){
        long currSum = 0;
        for(int j = i; j < elements.size(); j++){
          if(Math.abs(elements.get(i) - elements.get(j)) == 1) continue;

          currSum += elements.get(j);
        }

        maxValue = Math.max(maxValue, currSum);
      }

      return maxValue;
    }

//  public class SolutionTest {
//
//    @Test
//    public void test() {
//      int result = consecutive(15);
//      Assert.assertEquals(3, result);
//    }
//  }
}
