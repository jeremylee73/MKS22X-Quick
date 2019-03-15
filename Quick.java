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
   public static int partition (int [] data, int start, int end) {
    int pivot = (int)(Math.random() * (end - start + 1)) + start;
    int temp = data[start];
    if (pivot != start) {
      data[start] = data[pivot];
      data[pivot] = temp;
      pivot = start;
      start += 1;
    }
    while (start != end) {
      if (data[start] > data[pivot]) {
        int endTerm = data[end];
        data[end] = data[start];
        data[start] = endTerm;
        end -= 1;
      } else {
        start += 1;
      }
    }
    if (data[start] <= data[pivot]) {
      int endTerm = data[start];
      data[start] = data[pivot];
      data[pivot] = endTerm;
      pivot = start;
    } else {
      int endTerm = data[start-1];
      data[start-1] = data[pivot];
      data[pivot] = endTerm;
      pivot = start-1;
    }
    return pivot;
  }

   private static void swap(int[] data, int x, int y) {
     int temp = data[x];
     data[x] = data[y];
     data[y] = temp;
   }

  /*return the value that is the kth smallest value of the array. k=0 is the smallest*/
  public static int quickselect(int[] data, int k){
    int start = 0;
    int end = data.length - 1;
    int pivot = partition(data, start, end);
    if (pivot != k) {
      if (pivot > k) {
        end = pivot - 1;
      } else {
        start = pivot + 1;
      }
      pivot = partition(data, start, end);
    }
    return data[pivot];
  }

  private static int[] partitionDutch(int[] data, int start, int end){
    int lt = start;
    int gt = end;
    int i = lt + 1;
    int pivot = start;
    while (i <= gt) {
      if (data[i] < data[pivot]) {
        swap(data, lt, i);
        lt++;
        i++;
        pivot++;
      } else if (data[i] == data[pivot]) {
        i++;
      } else {
        swap(data, gt, i);
        gt--;
      }
    }
    return new int[] { lt, gt };
  }

  public static void quicksort(int[] data) {
    quickH(data, 0, data.length-1);
  }

  public static void quickH(int[] data, int start, int end) {
    if (start >= end) {
      return;
    }
    int[] partD = partitionDutch(data, start, end);
    quickH(data, start, partD[0]-1);
    quickH(data, partD[1]+1, end);
  }

  public static void main(String[]args){
    System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
    int[]MAX_LIST = {1000000000,500,10};
    for(int MAX : MAX_LIST){
      for(int size = 31250; size < 2000001; size*=2){
        long qtime=0;
        long btime=0;
        //average of 5 sorts.
        for(int trial = 0 ; trial <=5; trial++){
          int []data1 = new int[size];
          int []data2 = new int[size];
          for(int i = 0; i < data1.length; i++){
            data1[i] = (int)(Math.random()*MAX);
            data2[i] = data1[i];
          }
          long t1,t2;
          t1 = System.currentTimeMillis();
          Quick.quicksort(data2);
          t2 = System.currentTimeMillis();
          qtime += t2 - t1;
          t1 = System.currentTimeMillis();
          Arrays.sort(data1);
          t2 = System.currentTimeMillis();
          btime+= t2 - t1;
          if(!Arrays.equals(data1,data2)){
            System.out.println("FAIL TO SORT!");
            System.exit(0);
          }
        }
        System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
      }
      System.out.println();
    }
  }
}
