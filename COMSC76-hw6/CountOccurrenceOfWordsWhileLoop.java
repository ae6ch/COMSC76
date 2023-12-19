import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CountOccurrenceOfWordsWhileLoop {
   public static void main(String[] args) {
      if (args.length != 1) {
         System.out.println("Usage: java CountOccurrenceOfWords filename");
         return;
      }
      // args[0] is a file, make sure it exists
      File file = new File(args[0]);
      if (!file.exists()) {
         System.out.println("File " + args[0] + " does not exist");
         return;
      }

      // Create a TreeMap to hold words as key and count as value
      Map<String, Integer> map = new TreeMap<>();

      try (Scanner input = new Scanner(new FileReader(file))) {
         // This delimeter turns sun’s into sun and s
         // input.useDelimiter("[\\s+\\p{P}]");
         // This delimter leave sun’s as one word
         input.useDelimiter("[\\s+\\p{Punct}]");
         System.out.println("File " + args[0] + " opened successfully");
         while (input.hasNext()) {
            System.out.println("top of loop");
            String key = input.next().toLowerCase();
            // Various precondition checks, if any of these fail, skip to next iteration
            if ((key == null) || (key.isBlank()) || (!Character.isLetter(key.charAt(0)))) {
               System.out.println("Skipping " + key);
               continue;
            }   
              
            map.merge(key, 1, Integer::sum); // Increment count for key, doesn't work with Java < 8
         }
      } catch (FileNotFoundException fnfe) {
         System.out.println("File not found");
         return;
      }
      // Display key and value for each entry
      map.forEach((k, v) -> System.out.println(v + "\t" + k));
   }
}
