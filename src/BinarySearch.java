public class BinarySearch {


  public int binarySearch1D(int[] arr, int target){
    if (arr == null || arr.length < 1) return -1;
    return binarySearch1D(arr, 0, arr.length-1, target);
  }

  private int binarySearch1D(int[] arr, int low, int high, int target){
    if(arr == null || arr.length < 1) return -1;

    int mid = low + (high - low)/2;

    if(low <= high){
      if(target == arr[mid]) return mid;
      else if (target < arr[mid]){
        return binarySearch1D(arr, low, mid-1, target);
      } else {
        return binarySearch1D(arr, mid+1, high, target);
      }
    }

    return -1;
  }

  public int[] binarySearch2D(int[][]arr, int target){
    if(arr == null || arr.length < 1 || arr[0].length < 1) return new int[]{-1,-1};

    return binarySearch2D(arr, 0, arr.length-1, target);
  }

  private int[] binarySearch2D(int[][]arr, int low, int high, int target){
    if(arr == null || arr.length < 1 || arr[0].length < 1) return new int[]{-1,-1};

    int mid = low + (high - low) / 2;

    if(low <= high){
      if (target >= arr[mid][0] && target <= arr[mid][arr[0].length - 1]){
        // Target belongs to this row
        return new int[]{mid, binarySearch1D(arr[mid], target)};
      }

      if (target < arr[mid][0]){
        return binarySearch2D(arr, low, mid-1, target);
      }

      return binarySearch2D(arr, mid + 1, high, target);
    }

    return new int[]{-1,-1};
  }
}
