package greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * https://leetcode.com/problems/employee-free-time/description/
 */
public class EmployeeFreeTime {

  class Interval {
    int start;
    int end;
    Interval(int start, int end){
      this.start = start;
      this.end = end;
    }
  }


  @Test
  public void test(){
    Interval a = new Interval(1, 2);
    Interval b = new Interval(5, 6);
    Interval c = new Interval(1, 3);
    Interval d = new Interval(4, 10);

    List<List<Interval>> input = new ArrayList<List<Interval>>();
    List<Interval> list1 = new ArrayList<Interval>();
    List<Interval> list2 = new ArrayList<Interval>();
    List<Interval> list3 = new ArrayList<Interval>();

    list1.add(a);
    list1.add(b);
    list2.add(c);
    list3.add(d);
    input.add(list1);
    input.add(list2);
    input.add(list3);

    List<Interval> res = employeeFreeTime(input);

    assertEquals(3, res.get(0).start);
    assertEquals(4, res.get(0).end);
  }



  public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
    List<Interval> res = new ArrayList<Interval>();
    if(schedule == null || schedule.size() == 0) return res;
    List<Interval> tempList = new ArrayList<Interval>();

    for(List<Interval> innerList : schedule){
      for(Interval i : innerList){
        tempList.add(i);
      }
    }

    Collections.sort(tempList, (a, b) -> a.start - b.start);
    List<Interval> normalizedList = normalize(tempList);

    for(int i = 0; i + 1 < normalizedList.size(); i++){
      Interval curr = normalizedList.get(i);
      Interval next = normalizedList.get(i + 1);
      Interval result = new Interval(curr.end, next.start);
      res.add(result);
    }

    return res;
  }

  private List<Interval> normalize(List<Interval> list){
    List<Interval> res = new ArrayList<Interval>();
    if(list == null || list.size() == 0) return res;
    Interval prev = list.get(0);

    for(int i = 1; i < list.size(); i++){
      Interval curr = list.get(i);

      //engulfing
      if(prev.end >= curr.end) continue;

      //merging
      if(prev.end >= curr.start){
        prev.end = curr.end;
      } else {
        res.add(prev);
        prev = curr;
        if(i == list.size() - 1) res.add(curr);
      }
    }

    //edge case of intervals containing only one interval
    if(!res.contains(prev)) res.add(prev);

    return res;
  }

}
