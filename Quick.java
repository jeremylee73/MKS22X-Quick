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
    System.out.println(pivot);
    for (int i=0; i<data.length; i++){
      System.out.print(data[i] + " ");
    }
    System.out.println();
    swap(data, start, pivotIndex);
    while (start != end){
      if (data[start] > pivot) {
        swap(data, start, end);
        end -= 1;
      } else {
        start += 1;
      }
    }

    if (data[data.length-1] <= pivot){
      swap(data, 0, data.length-1);
    }

    for (int i=0; i<data.length-1; i++){
      if (data[i] <= pivot && data[i+1] >= pivot){
        swap(data, 0, i);
        pivotIndex = i;
      }
    }
    for (int i=0; i<data.length; i++){
      System.out.print(data[i] + " ");
    }
    System.out.println();
    return pivotIndex;
  }

  private static void swap(int[] data, int a, int b){
    int temp = data[a];
    data[a] = data[b];
    data[b] = temp;
  }

  public static void main(String[] args){
    int[] test = new int[] {17,61,67,47,93,12,20,4,44,68};
    System.out.println(partition(test,0,test.length-1));
  }
}
