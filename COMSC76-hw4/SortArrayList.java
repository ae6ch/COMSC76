import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Sorts any list of comparable elements using quicksort
 * Should work with anything that extends list (LinkedList, ArrayList, etc.) and
 * any type that is comparable (Integer, String, Double, etc.)
 * 
 * 9/17/23
 * 
 * @author Steve Rubin ID0370442
 * 
 */
public class SortArrayList {
   public static void main(String[] args) {
      // Create 4 lists of different types, init them, sort them, and print them
      ArrayList<Integer> intList = new ArrayList<>(Arrays.asList(2, 4, 3));
      ArrayList<String> strList = new ArrayList<>(Arrays.asList("Bob", "Alice", "Ted", "Carol"));
      ArrayList<Double> doubleList = new ArrayList<>(Arrays.asList(3.4, 1.2, -12.3));
      Stack<Byte> charStack = new Stack<>(); // Is there really not a constructor that takes an array for Stack?
      charStack.push((byte) 1);
      charStack.push((byte) 42);
      charStack.push((byte) 3);
      charStack.push((byte) -14);

      sort(intList);
      sort(doubleList);
      sort(strList);
      sort(charStack);

      System.out.printf("Sorted Integer Objects: %s\n", listToString(intList));
      System.out.printf("Sorted Double Objects: %s\n", listToString(doubleList));
      System.out.printf("Sorted String Objects: %s\n", listToString(strList));
      System.out.printf("Sorted Byte Objects: %s\n", listToString(charStack));
   }

   /**
    * Sorts a list of comparable elements using quicksort
    * 
    * @param <E>  Comparable type
    * @param list list to sort
    */
   public static <E extends Comparable<E>> void sort(List<E> list) {
      sort(list, 0, list.size() - 1);
   }

   /**
    * Sorts a list of comparable elements using quicksort
    * 
    * @param <E>  Comparable type
    * @param list list to sort
    * @param i    start index
    * @param j    end index
    */
   public static <E extends Comparable<E>> void sort(List<E> list, int i, int j) {
      if (i >= j) {
         return; // subarray of 1 element
      }
      // Choose pivot randomly
      int pivotIndex = new Random().nextInt(j - i) + i;
      swap(list, pivotIndex, j); // Move pivot to end
      E pivot = list.get(j);

      int leftIndex = partition(list, i, j, pivot);
      sort(list, i, leftIndex - 1);
      sort(list, leftIndex + 1, j);

   }

   /**
    * Partitions a list around a pivot
    * @param <E> Comparable type
    * @param list list to partition
    * @param i left index
    * @param j right index
    * @param pivot pivot element
    * @return partition index
    */
   private static <E extends Comparable<E>> int partition(List<E> list, int i, int j, E pivot) {
      int leftIndex = i;
      int rightIndex = j;
      while (leftIndex < rightIndex) { // Walk the indexds inwards until they meet
         while (list.get(leftIndex).compareTo(pivot) <= 0 && leftIndex < rightIndex) {
            leftIndex++;
         }
         while (list.get(rightIndex).compareTo(pivot) >= 0 && leftIndex < rightIndex) {
            rightIndex--;
         }
         swap(list, leftIndex, rightIndex);
      }
      // indexs met, swap with pivot
      swap(list, leftIndex, j);
      // quicksort the left and right partitions
      return leftIndex;
   }

   /**
    * Swaps two elements in a list
    * 
    * @param <E>  type
    * @param list list to swap elements in
    * @param a    index of first element
    * @param b    index of second element
    */
   public static <E> void swap(List<E> list, int a, int b) {
      E temp = list.get(a);
      list.set(a, list.get(b));
      list.set(b, temp);

   }

   /**
    * Converts a list to a string with no delimiters or brackets
    * 
    * @param <E>  type
    * @param list list to convert
    * @return string representation of list
    */
   public static <E> String listToString(List<E> list) {
      return list.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(", "));
   }
}