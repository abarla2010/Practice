package unionfind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/accounts-merge/description/
 */
public class AccountsMerge {

  public static void main(String[] args) {
    List<List<String>> input = new ArrayList<List<String>>();
    List<String> tempList1 = new ArrayList<>();
    List<String> tempList2 = new ArrayList<>();
    List<String> tempList3 = new ArrayList<>();
    List<String> tempList4 = new ArrayList<>();

    tempList1.add("John");
    tempList1.add("johnsmith@mail.com");
    tempList1.add("john00@mail.com");

    tempList2.add("John");
    tempList2.add("johnnybravo@mail.com");

    tempList3.add("John");
    tempList3.add("johnsmith@mail.com");
    tempList3.add("john_newyork@mail.com");

    tempList4.add("Mary");
    tempList4.add("mary@mail.com");

    input.add(tempList1);
    input.add(tempList2);
    input.add(tempList3);
    input.add(tempList4);

    List<List<String>> res = accountsMerge(input);

    for (List<String> innerList : res) {
      for (String s : innerList) {
        System.out.print(s + " ");
      }

      System.out.println();
    }
  }

  // email -> email
  private static Map<String, String> owners = new HashMap<String, String>();

  // email -> user name
  private static Map<String, String> parents = new HashMap<String, String>();

  public static List<List<String>> accountsMerge(List<List<String>> accounts) {
    List<List<String>> res = new ArrayList<List<String>>();
    if(accounts == null || accounts.size() == 0) return res;

    //establish a union-find structure, point each email to itself
    for(List<String> a : accounts){
      String owner = a.get(0);

      for(int i = 1; i < a.size(); i++){
        String email = a.get(i);
        parents.put(email, email);
        owners.put(email, owner);
      }
    }

    //union the emails in the same group
    for(List<String> a: accounts){

      // If the list has only one element, then it's a single node,
      // that is taken care of as we're creating the email -> parents relationships beforehand
      for(int i = 1; i < a.size() - 1; i++){
        union(a.get(i), a.get(i + 1));
      }
    }

    // now the everything is connected
    // find all connected components by iterate through this UF structure/hashmap
    // create a parent email -> all children email data structure
    // also don't forget to include the parent email in the children list as we're gonna
    // use this list later
    Map<String, List<String>> emails = new HashMap<String, List<String>>();
    for(String currEmail : parents.keySet()){

      String currParent = find(currEmail);
      List<String> currList = emails.getOrDefault(currParent, new ArrayList<String>());
      currList.add(currEmail);
      emails.put(currParent, currList);
    }

    // Add the name and sort the list
    for(Map.Entry<String, List<String>> e : emails.entrySet()){

      List<String> list = e.getValue();
      Collections.sort(list);
      List<String> resultList = new ArrayList<String>();
      resultList.add(owners.get(e.getKey()));
      resultList.addAll(list);
      res.add(resultList);
    }

    return res;
  }

  private static String find(String email){
    String currEmail = email;
    while(currEmail != parents.get(currEmail)) currEmail = parents.get(currEmail);
    return currEmail;
  }

  private static  void union(String xEmail, String yEmail){
    String xParent = find(xEmail);
    String yParent = find(yEmail);

    if(xParent.equals(yParent)) return;

    // The order of which group joins which, doesn't really matter
    parents.put(xParent, yParent);
  }
}
