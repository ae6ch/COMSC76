import java.util.Scanner;

public class CharacterCounter {
   public static void main(String[] args) {
      char[] test = { 'T', 'h', 'i', 's', ' ', 'i', 's', ' ', 't', 'h', 'e', ' ', 's', 't', 'r', 'i', 'n', 'g' };

      Scanner in = new Scanner(System.in);
      System.out.print("Enter a character: ");
      char testChar = in.nextLine().charAt(0);
      in.close();

      // Case sensitive display of the count of characters, see below for case insensitive
      int count = charCount(test, testChar);
      System.out.printf("The character '%c' appears %d times in the array.\n", testChar, count);

      // Case insensitive display of the count of characters
      // int countUpper = charCount(test, Character.toUpperCase(testChar));
      // int countLower = charCount(test, Character.toLowerCase(testChar));
      // System.out.printf("The character '%c' (uppercase or lowercase) appears %d times in the array.\n", testChar, countUpper + countLower);

   }

   public static int charCount(char[] array, char ch) {
      return charCount(array, 0, ch);
   }

   public static int charCount(char[] array, int start, char ch) {
      if (start == array.length) { // base case
         return 0;
      }
      if (array[start] == ch) {
         return 1 + charCount(array, start + 1, ch);
      }
      return charCount(array, start + 1, ch);
   }
}
