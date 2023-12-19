import java.util.Arrays;
import java.util.Random;

/**
 * Print the various execution times of sorting algorithms
 * 10/27/2023
 * @author Steve Rubin 0370442
 */
public class SortTimes {
   public static void main(String[] args) {
      int[] sizes = { 50000, 100000, 150000, 200000, 250000, 300000 };

      System.out.printf("%15s | %15s%15s%15s%15s%15s\n", "Array Size", "Selection",
            "Merge", "Quick", "Heap", "Radix");
      System.out
            .println("---------------------------------------------------------------------------------------------");
      for (int i : sizes) {
         System.out.printf("%,15d | %15d%15d%15d%15d%15d\n", i,
               selectionSort(fillRandomArray(i)),
               mergeSort(fillRandomArray(i)), quickSort(fillRandomArray(i)),
               heapSort(fillRandomArray(i)),
               radixSort(fillRandomArray(i)));
      }
   }

   /**
    * Fills an array with random numbers
    * 
    * @param size of array
    * @return array filled with random numbers
    */
   public static int[] fillRandomArray(int size) {
      int[] arr = new int[size];
      Random r = new Random();
      for (int i = 0; i < size; i++) {
         arr[i] = r.nextInt(Integer.MAX_VALUE);
      }
      // System.out.println("Unsorted: " + arr);
      return arr;
   }

   /**
    * Quick Sort algorithm
    * 
    * @param arr to be sorted
    * @return runtime in milliseconds
    */
   private static long quickSort(int[] arr) {
      long startTime = System.currentTimeMillis();
      quickSort(arr, 0, arr.length - 1);
      return System.currentTimeMillis() - startTime;
   }

   private static void quickSort(int[] arr, int i, int j) {
      if (i >= j)
         return; // subarray of 1 element
      int pivot = arr[j]; // pivot is the last number in the array, lazy.
      int l = i;
      int r = j;
      l = partition(arr, j, pivot, l, r);
      // sort the left and right partitions
      quickSort(arr, i, l - 1);
      quickSort(arr, l + 1, j);

   }

   /**
    * Partitions the array for quick sort
    * 
    * @param arr   to be sorted
    * @param j     index of pivot
    * @param pivot value of pivot
    * @param l     left index
    * @param r     right index
    * @return index of pivot
    */
   private static int partition(int[] arr, int j, int pivot, int l, int r) {
      while (l < r) { // partition
         while (arr[l] <= pivot && l < r) {
            l++;
         }
         while (arr[r] >= pivot && l < r) {
            r--;
         }
         swap(arr, l, r);
      }
      // l and r met, swap with pivot
      swap(arr, l, j);
      return l;
   }

   /**
    * Swaps two elements in an array
    * 
    * @param arr to swap elements in
    * @param a
    * @param b
    */
   private static void swap(int[] arr, int a, int b) {
      int temp = arr[a];
      arr[a] = arr[b];
      arr[b] = temp;
   }

   /**
    * Merge Sort algorithm
    *
    * @param arr to be sorted
    * @return runtime in milliseconds
    */
   private static long mergeSort(int[] arr) {
      long startTime = System.currentTimeMillis();
      if (arr.length < 2) {
         return 0L;
      }

      int mid = arr.length / 2;
      int[] left = Arrays.copyOfRange(arr, 0, mid);
      int[] right = Arrays.copyOfRange(arr, mid, arr.length);

      mergeSort(left);
      mergeSort(right);
      merge(arr, left, right);

      return System.currentTimeMillis() - startTime;
   }

   /**
    * Merges two arrays
    * 
    * @param arr   destionation array
    * @param left  array 1
    * @param right array 2
    */
   private static void merge(int[] arr, int[] left, int[] right) {
      int i = 0;
      int j = 0;
      int k = 0;
      while (i < left.length && j < right.length) {
         if (left[i] <= right[j]) {
            arr[k] = left[i];
            i++;
         } else {
            arr[k] = right[j];
            j++;
         }
         k++;
      }
      while (i < left.length) {
         arr[k] = left[i];
         i++;
         k++;
      }
      while (j < right.length) {
         arr[k] = right[j];
         j++;
         k++;
      }
   }

   /**
    * Heap Sort algorithm
    * 
    * @param arr to be sorted
    * @return runtime in milliseconds
    */
   private static long heapSort(int[] arr) {
      long startTime = System.currentTimeMillis();

      int n = arr.length;
      for (int i = n / 2 - 1; i >= 0; i--) {
         heapify(arr, n, i);
      }
      for (int i = n - 1; i > 0; i--) {
         swap(arr, 0, i);
         heapify(arr, i, 0);
      }
      return System.currentTimeMillis() - startTime;
   }

   /**
    * Heapify method for heap sort
    * 
    * @param arr to be sorted
    * @param n   size of array
    * @param i   index
    */
   private static void heapify(int[] arr, int n, int i) {
      int largest = i;
      int leftChild = 2 * i + 1;
      int rightChild = 2 * i + 2;

      if (leftChild < n && arr[leftChild] > arr[largest]) {
         largest = leftChild;
      }

      if (rightChild < n && arr[rightChild] > arr[largest]) {
         largest = rightChild;
      }

      if (largest != i) {
         swap(arr, i, largest);
         heapify(arr, n, largest);
      }
   }

   /**
    * Radix Sort algorithm
    * 
    * @param arr to be sorted
    * 
    */
   private static long radixSort(int[] arr) {
      long startTime = System.currentTimeMillis();

      int max = arr[0];
      for (int i = 1; i < arr.length; i++) {
         if (arr[i] > max) {
            max = arr[i];
         }
      }
      for (int exp = 1; max / exp > 0; exp *= 10) {
         countSort(arr, exp);
      }
      return System.currentTimeMillis() - startTime;
   }

   /**
    * Count Sort method for radix sort
    */
   private static void countSort(int[] arr, int exp) {
      int n = arr.length;
      int[] output = new int[n];
      int[] count = new int[10];

      for (int i = 0; i < n; i++) {
         count[(arr[i] / exp) % 10]++;
      }

      for (int i = 1; i < 10; i++) {
         count[i] += count[i - 1];
      }

      for (int i = n - 1; i >= 0; i--) {
         int digit = (arr[i] / exp) % 10;
         output[count[digit] - 1] = arr[i];
         count[digit]--;
      }

      System.arraycopy(output, 0, arr, 0, n);
   }

   /**
    * Selection Sort algorithm
    * 
    * @param arr to be sorted
    * @return runtime in milliseconds
    */

   private static long selectionSort(int[] arr) {
      long startTime = System.currentTimeMillis();

      for (int i = 0; i < arr.length - 1; i++) {
         int min = arr[i];
         int minIndex = i;
         for (int j = i + 1; j <= arr.length - 1; j++) {
            if (arr[j] < min) {
               min = arr[j];
               minIndex = j;
            }
         }
         if (minIndex != i) {
            swap(arr, minIndex, i);
         }
      }
      return System.currentTimeMillis() - startTime;
   }
}
