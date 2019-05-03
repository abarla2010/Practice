package sort;

public class MergeSort {

  public static void main(String[] args){
    int[] arr = new int[]{4,2,7,3,1,5,9,6,8,10};
    arr = mergeSort(arr);
    for(int i : arr) {
      System.out.print(i + " ");
    }
  }

  public static int[] mergeSort(int[] arr) {
    int N = arr.length;
    if(N <= 1) return arr;

    int[] leftArr = new int[N/2];
    int[] rightArr = new int[N - N/2];

    for (int i = 0; i < leftArr.length; i++) {
      leftArr[i] = arr[i];
    }

    for (int i = 0; i < rightArr.length; i++) {
      rightArr[i] = arr[N/2 + i];
    }

    leftArr = mergeSort(leftArr);
    rightArr = mergeSort(rightArr);
    return merge(leftArr, rightArr);
  }

  public static int[] merge(int[] a, int[] b) {
    int[] c = new int[a.length + b.length];

    int i = 0;
    int j = 0;
    for(int k = 0; k < c.length; k++){
      if(i >= a.length)       c[k] = b[j++];
      else if (j >= b.length) c[k] = a[i++];
      else if (a[i] <= b[j])  c[k] = a[i++];
      else                    c[k] = b[j++];
    }

    return c;
  }
}
