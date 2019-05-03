public class Sorter {

  public int[] mergeSort(int[] arr){
    if(arr == null || arr.length < 2) return arr;
    return mergeSort(arr, 0, arr.length - 1);
  }

  private int[] mergeSort(int[] arr, int low, int high){
    if(arr == null || arr.length < 2) return arr;

    int mid = low + (high-low)/2;
    if(low < high){
      mergeSort(arr, low, mid);
      mergeSort(arr, mid+1, high);
      arr = merge(arr, low, mid, high);
    }

    return arr;
  }

  private int[] merge(int[] arr, int low, int mid, int high){
    if (arr == null || high < low || mid > high || mid < low || arr.length < 1) return arr;

    int leftSize = mid - low + 1;
    int rightSize = high - low;

    int[] left = new int[leftSize];
    int[] right = new int[rightSize];

    for(int a = 0;  a < leftSize; a++){
      left[a] = arr[low + a];
    }

    for(int b=0; b < rightSize; b++){
      right[b] = arr[mid + 1 + b];
    }

    int k = low;
    int i = 0;
    int j = 0;
    while(i < leftSize && j < rightSize){
      if(left[i] <= right[j]){
        arr[k] = left[i];
        i++;
      } else {
        arr[k] = right[j];
        j++;
      }
      k++;
    }

    while(i < leftSize){
      arr[k] = left[i];
      i++;
      k++;
    }

    while(j < rightSize){
      arr[k] = right[j];
      j++;
      k++;
    }

    return arr;
  }

}
