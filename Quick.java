import java.util.*;

public class Quick{
    /*Modify the array such that:
   *1. Only the indices from start to end inclusive are considered in range
   *2. A random index from start to end inclusive is chosen, the corresponding
   *   element is designated the pivot element.
   *3. all elements in range that are smaller than the pivot element are placed before the pivot element.
   *4. all elements in range that are larger than the pivot element are placed after the pivot element.
   *@return the index of the final position of the pivot element.
   */
  public static int partition(int[] data, int start, int end){
    int pivotIndex = (int)(Math.random() * data.length);
    int pivot = data[pivotIndex];
    swap(data, start, pivotIndex);
    while (start != end){
      if (data[start] > pivot) {
        swap(data, start, end);
        end -= 1;
      } else {
        start += 1;
      }
    }

    if (pivot >= start){
      swap(data, pivotIndex, start);
      return start;
    } else {
      swap(data, pivotIndex, start-1);
      return start-1;
    }
  }

  private static void swap(int[] data, int a, int b){
    int temp = data[a];
    data[a] = data[b];
    data[b] = temp;
  }

  /*return the value that is the kth smallest value of the array. k=0 is the smallest*/
  // public static int quickselect(int[] data, int k){
  //   if (k == partition(data,0,data.length-1)){
  //     return data[pivotIndex];
  //   }
  //   if (k > partition(data, 0, data.length-1)){
  //     int[] newData = new int[e-pivotIndex];
  //     for (int i=0; i<newData.length; i++){
  //       newData[i] = data[i+pivotIndex];
  //     }
  //     return quickselect(newData, k);
  //   }
  //   if (k < partition(data, 0, data.length-1)){
  //     int[] newData = new int[pivotIndex+1];
  //     for (int i=0; i<newData.length; i++){
  //       newData[i] = data[i];
  //     }
  //     return quickselect(newData, k);
  //   }
  //   return 0;
  // }

  public static void quicksort(int[] data, int lo, int hi){
    if (lo >= hi){
      return;
    }
    int pivot = partition(data, lo, hi);
    System.out.println("Pivot #:  "+data[pivot]);
    for (int i=0; i<data.length; i++){
      System.out.print(data[i] + " ");
    }
    System.out.println();
    // for (int i=lo; i<=hi; i++){
    //   System.out.print(data[i] + " ");
    // }
    // System.out.println();
    quicksort(data, lo, pivot-1);
    quicksort(data, pivot+1, hi);
  }

  public static void main(String[] args){
    int[] test = {17,61,67,47,93,12,20,4,44,68};
    for (int i=0; i<test.length; i++){
      System.out.print(test[i] + " ");
    }
    System.out.println();

    System.out.println(test[partition(test,0,test.length-1)]);
    for (int i=0; i<test.length; i++){
      System.out.print(test[i] + " ");
    }
    System.out.println();
  }
}
