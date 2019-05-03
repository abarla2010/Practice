package array;

import org.junit.*;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import static org.junit.Assert.*;

/**
 * https://leetcode.com/problems/pour-water/description/
 */
public class PourWater {
  public static void main(String[] args){
    JUnitCore jUnitCore = new JUnitCore();
    jUnitCore.addListener(new TextListener(System.out));
    Result res = jUnitCore.run(UnitTest.class);
    System.out.println(res.wasSuccessful());
  }

  public static int[] pourWater(int[] heights, int V, int K){
    if(heights == null || heights.length == 0 || K < 0 || K > heights.length - 1) return heights;

    while(V != 0) {
      int curr = K;

      //move left
      while (curr > 0 && heights[curr] >= heights[curr - 1]) curr--;

      //move right
      while (curr < heights.length - 1 && heights[curr] >= heights[curr + 1]) curr++;

      //move left to K if you came too far away from K
      while(curr > K && heights[curr] >= heights[curr - 1]) curr--;

      heights[curr]++;
      V--;
    }

    return heights;
  }

  public static class UnitTest {

    @Test
    public void testWithSpotsOnBothSides(){
      int[] arr = new int[]{2,0,1,2,0,2};
      pourWater(arr, 6, 3);
      assertEquals(2, arr[0]);
      assertEquals(2, arr[1]);
      assertEquals(2, arr[2]);
      assertEquals(3, arr[3]);
      assertEquals(2, arr[4]);
      assertEquals(2, arr[5]);
    }

    @Test
    public void testWithAllSameValues(){
      int[] arr = new int[]{0,0,0};
      pourWater(arr, 5, 1);
      assertEquals(2, arr[0]);
      assertEquals(2, arr[1]);
      assertEquals(1, arr[2]);
    }
  }
}