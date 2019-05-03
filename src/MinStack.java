import java.util.Stack;

public class MinStack {

  Stack<Integer> mainStack;
  Stack<Integer> minStack;
  Stack<Integer> helperStack;

  /** initialize your data structure here. */
  public MinStack() {
    mainStack = new Stack<Integer>();
    minStack = new Stack<Integer>();
    helperStack = new Stack<Integer>();
  }

  public Integer peek(){
    if(mainStack.size() == 0) return null;
    else return mainStack.peek();
  }

  public void push(int x) {
    if(mainStack.size() == 0 || minStack.peek() > x){
      minStack.push(x);
    }
    else {
      while(true){
        Integer temp = minStack.peek();
        if(temp < x){
          helperStack.push(minStack.pop());
        }

        if(minStack.size() == 0 || minStack.peek() > x){
          minStack.push(x);
          transferFromHelperToMin();
          break;
        }
      }
    }

    mainStack.push(x);
  }

  public void pop() {
    removeFromMinStack(mainStack.peek());
    mainStack.pop();
  }

  private void transferFromHelperToMin(){
    while(true){
      if(helperStack.size() == 0) return;
      minStack.push(helperStack.pop());
    }
  }

  /**
   * Removes element from minStack
   **/
  private void removeFromMinStack(Integer num){
    int maxIterations = minStack.size();
    if(maxIterations == 0) return;

    while(true){
      if(maxIterations == 0){
        // Item not found in minStack
        break;
      }
      Integer temp = minStack.peek();
      if(temp == num){
        minStack.pop();
        if(helperStack.size() != 0){
          transferFromHelperToMin();
        }
        break;
      }
      else {
        helperStack.push(minStack.pop());
      }
      maxIterations--;
    }
  }

  public int top() {
    return mainStack.peek();
  }

  public int getMin() {
    return minStack.peek();
  }
}