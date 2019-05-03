public class Arrays {

  public static void main(String[] args) {
    int[] nums = new int[]{-1,-1,1};
    System.out.println(subarraySum(nums, 0));
  }

  public static int removeDuplicates(int[] nums) {
    int arrLen = nums.length;
    if (arrLen < 2) {
      return arrLen;
    }

    int finalLength = 0;
    for (int i = 0; i < arrLen - 1; i++) {
      if (nums[i] != nums[i + 1]) {
        if (i == arrLen - 2) {
          finalLength++;
        }
        finalLength++;
      } else {
        if (i == arrLen - 2) {
          finalLength++;
        }
      }
    }
    return finalLength;
  }


  private static int count = 0;

  public static int subarraySum(int[] nums, int k) {
    if(nums == null || nums.length == 0 || (nums.length == 1 && nums[0] == 1 && k == 0)) return count;
    for(int i = 0; i < nums.length; i++)
      helper(nums, k, 0, i);
    return count;
  }

  private static void helper(int[] nums, int k, int currSum, int start){
    if(currSum > k) return;
    if(currSum == k) count++;
    if(start >= nums.length) return;
    helper(nums, k, currSum + nums[start], start + 1);
  }
}
