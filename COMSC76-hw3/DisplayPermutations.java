import java.util.Scanner;

/**
 * Recursion homework part 2
 * Show all permutations of a string
 * 
 * 9/8/23
 * @author Steve Rubin ID0370442
 */
public class DisplayPermutations {
   public static void main(String[] args) {
      System.out.print("Enter your string: ");

      try (Scanner in = new Scanner(System.in)) {
         String str = in.nextLine();
         displayPermutation(str);
      }
   }

   /**
    * Displays all permutations of a string
    * @param s The string to permute
    */
   public static void displayPermutation(String s) {
      displayPermutation("", s);
   }

   /**
    * Displays all permutations of a string
    * @param s1 The string that has been built so far
    * @param s2 The remaining characters to be permuted
    */
   public static void displayPermutation(String s1, String s2) {
      System.out.println("Stack Size - " + Thread.currentThread().getStackTrace().length);
      //Thread.currentThread().dumpStack();

      if (s2.isEmpty()) {  // the goal is to move all the chars from s2 to s1 and then display
         System.out.println(s1);
         return;
      }
      for (int idx = 0; idx < s2.length(); ++idx) { 
         // for each element of s2, move it to s1 and call again with the new s1 and the remaining s2
        displayPermutation(s1 + s2.charAt(idx), s2.substring(0, idx) + s2.substring(idx + 1));       
      }
   }
}