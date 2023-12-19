import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Count the occurrences of words in a text file) 
 * 
 * 10/14/23
 * @author Steve Rubin 0370442 
 */
public class CountOccurrenceOfWords {
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: java CountOccurrenceOfWords filename");
      return;
    }
    // make sure file exists
    File file = new File(args[0]);
    if (!file.exists() || !file.canRead()) {
      System.out.println("File " + args[0] + " does not exist or is not readable");
      System.exit(1);
    }
    // using the BufferedReader is optional, but it is more i/o efficient to read into a buffer first,
    // instead of char-a-time like FileReader does on its own into scanner
    try (Scanner input = new Scanner(new BufferedReader(new FileReader(file)))) {

      input.useDelimiter("[\\s+\\p{P}]"); // This delimeter turns sun’s into sun and s

      // input.useDelimiter("[\\s+\\p{Punct}]"); // This delimter leave sun’s as one word

      // Create a treemap to hold words as key and count as value.
      TreeMap<String, Integer> map = input.tokens()
          .map(String::toLowerCase) // Parse each word from scanner, check some preconditions, and insert or
                                    // increment the map.
          .filter(k -> k != null && !k.isBlank() && Character.isLetter(k.charAt(0)))
          .collect(Collectors.toMap(k -> k, k -> 1, Integer::sum, TreeMap::new));

      System.out.println("Display words and their count in ascending order of the words:");
      map.forEach((k, v) -> System.out.println(v + "\t" + k)); // print out the k,v stored in the map
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
