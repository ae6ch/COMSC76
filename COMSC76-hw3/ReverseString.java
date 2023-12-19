import java.util.Scanner;

/**
 * Recursion homework part 1
 * Display the reverse of a string
 * 
 * 9/9/23
 * 
 * @author Steve Rubin ID0370442
 */

public class ReverseString {
   public static void main(String[] args) {
      System.out.print("Enter your string: ");

      try (Scanner in = new Scanner(System.in)) {
         String str = in.nextLine();
         reverseDisplay(str);
      }
   }

   /**
    * Displays the reverse of a string
    * @param value The string to reverse
    */
   public static void reverseDisplay(String value) {
      reverseDisplay(value, value.length() - 1);
      System.out.println();

   }

   /**
    * Displays the reverse of a string
    * @param value The string to reverse
    * @param high The index of the last char to print
    */
   public static void reverseDisplay(String value, int high) {
      if (high < 0 || value.isEmpty()) { // the high check is the base case
         return;
      }
      System.out.print(value.charAt(high)); // print the last char
      reverseDisplay(value, high - 1); // call again without the last char, rince and repeat

   }
}