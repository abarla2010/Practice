package sort;

public class QuickSort {

  public static void main(String[] args){

    int[] arr = new int[]{3,5,6,2,9,1,10,4,8,7,11};
    doQuickSort(arr);

    for(int i = 0; i < arr.length; i++){
      System.out.print(arr[i] + " ");
    }
  }


  private static int[] doQuickSort(int[] arr){
    if(arr == null || arr.length == 0) return arr;
    quickSort(arr, 0, arr.length - 1);
    return arr;
  }

  private static void quickSort(int[] arr, int left, int right){
    if(left >= right) return;
    int pivot = arr[(left + right) / 2];
    int index = partition(arr, left, right, pivot);
    quickSort(arr, left, index - 1);
    quickSort(arr, index, right);
  }

  private static int partition(int[] arr, int left, int right, int pivot){
      while (left <= right){

        // Find element that is greater than the pivot since it needs to be moved to right side of pivot
        while(arr[left] < pivot){
          left++;
        }

        while(arr[right] > pivot){
          right--;
        }

        if(left <= right){
          swap(arr, left, right);
          left++;
          right--;
        }
      }

      return left;
  }

  private static void swap(int[] arr, int i, int j){
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

}

