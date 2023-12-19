import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Compares the runtimes of a BST and an AVL tree
 * 
 * 11/26/23
 * @author Steve Rubin 0370442
 * 
 */
public class BstVsAvl {
   public static void main(String[] args) {
      Map<String, Long> bstResults = timeSequence(new BST<>());
      Map<String, Long> avlResults = timeSequence(new AVLTree<>());

      compareResults(bstResults, avlResults);
   }

   /**
    * Runs a sequence of operations on the tree and returns a map of the results
    * 
    * @param tree the tree to run the operations on
    * @return Map of results
    */
   private static Map<String, Long> timeSequence(Tree<Integer> tree) {
      // System.out.printf("--%s--\n", tree.getClass().getSimpleName());
      long startTime = System.nanoTime();

      Map<String, Long> results = new LinkedHashMap<>(); // LinkedHashMap to preserve order of insertion

      results.put("Fill",
            performOperation("Filling Tree Randomly", () -> fillTreeRandomly(tree, 500000, Integer.MAX_VALUE)));
      results.put("Shuffle1", performOperation("Shuffling Tree", tree::shuffle));
      results.put("Search", performOperation("Searching in Tree", () -> tree.search(1000))); // arbitrary search
      results.put("Shuffle2", performOperation("Shuffling Tree Again", tree::shuffle));
      results.put("Clear", performOperation("Clearing Tree", tree::clear));

      long totalTime = System.nanoTime() - startTime;
      results.put("TotalTime", totalTime);

      // System.out.printf("%s Run Time %dns\n", tree.getClass().getSimpleName(),
      // totalTime);

      return results;
   }

   /**
    * Fills the tree with random numbers
    * 
    * @param tree
    * @param size number of elements to add
    * @param max  maximum value of elements
    */
   private static void fillTreeRandomly(Tree<Integer> tree, int size, int max) {
      Random rand = new Random();
      for (int i = 0; i < size; i++) { // This actually can end up with a under i elements because of duplicates
         tree.insert(rand.nextInt(max));
      } 
   }

   /**
    * Performs an operation and returns the time it took
    * 
    * @param operation description of the operation
    * @param task      the operation to perform
    * @return the time it took to perform the operation
    */
   private static long performOperation(String operation, Runnable task) {
      long startTime = System.nanoTime();
      task.run();
      long elapsedTime = System.nanoTime() - startTime;
      /*
       * if (elapsedTime > 1_000_000) { // Checking if time is greater than 1ms
       * (1,000,000 nanoseconds)
       * System.out.printf("%s Time %.2fms\n", operation, elapsedTime / 1_000_000.0);
       * // Convert nanoseconds to milliseconds
       * } else {
       * System.out.printf("%s Time %dns\n", operation, elapsedTime);
       * }
       */
      return elapsedTime;
   }

   /**
    * Compares the results of the two tree runtimes
    * 
    * @param bstResults A map of the results of the BST
    * @param avlResults A map of the results of the AVL
    */
   private static void compareResults(Map<String, Long> bstResults, Map<String, Long> avlResults) {
      System.out.println("\nComparison of Results:");
      System.out.printf("%-20s | %-15s | %-15s | %s\n", "Operation", "BST Time (ns)", "AVL Time (ns)", "Faster Tree");

      bstResults.entrySet().stream()
            .filter(entry -> avlResults.containsKey(entry.getKey()))
            .forEach(entry -> {
               String operation = entry.getKey();
               Long bstTime = entry.getValue();
               Long avlTime = avlResults.get(operation);
               String fasterTree = (bstTime < avlTime) ? "BST" : "AVL";
               System.out.printf("%-20s | %-15d | %-15d | %s\n", operation, bstTime, avlTime, fasterTree);
            });
   }
}
