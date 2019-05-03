package math;

// Find square root of a number until the given precision
public class SquareRoot {
  public static void main(String[] args){

    System.out.println(squareRoot(50, 3));
  }

  private static float squareRoot(int x, int precision){
    if(x < 1) return 0;
    if(x == 1) return 1;

    int start = 1;
    int end = x;
    double res = 0.0;

    while(start < end) {
      int mid = (start + end) / 2;
      if (mid * mid == x)
        return (float) res;
      else if (mid * mid < x) {
        start = mid + 1;
        res = mid;
      } else {
        end = mid - 1;
      }
    }

    // For computing the fractional part
    // of square root up to given precision
    double increment = 0.1;
    for(int i = 0; i < precision; i++) {
      while (res * res <= x) {
        res += increment;
      }

      // The while loop made the res go above increment, so deduct it
      // and try with a lesser decimal value (increment / 10)
      res -= increment;
      increment = increment / 10;
    }

    return (float)res;
  }
}
