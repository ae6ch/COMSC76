import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * TestBST modified from 25.5 to add tests for toArray (both toArray() and
 * toArray(E[] a) methods), retainAll,
 * removeAll, addAll, and containsAll methods in Tree.java
 * 
 * 11/19/23
 * 
 * @author Steve Rubin 0370442
 */
public class TestBST {
  public static void main(String[] args) {
    // Create a BST
    BST<String> tree = new BST<>();
    tree.insert("George");
    tree.insert("Michael");
    tree.insert("Tom");
    tree.insert("Adam");
    tree.insert("Jones");
    tree.insert("Peter");
    tree.insert("Daniel");
    for (String s : tree)
      System.out.print(s + " ");
    System.out.println();

    System.out.println("toArray: " + Arrays.toString(tree.toArray())); // This also tests the generic method toArray()
                                                                       // in Tree.java

    tree.retainAll(Arrays.asList("Peter", "Daniel", "Tom", "Adam"));
    System.out.println("retainAll: " + Arrays.toString(tree.toArray()) + " Should be just peter daniel,tom,adam");

    tree.removeAll(Arrays.asList("Peter", "Daniel"));
    System.out.println("removeAll: " + Arrays.toString(tree.toArray()) + " remove  peter and daniel");

    tree.addAll(Arrays.asList("Peter", "Daniel"));
    System.out.println("addAll: " + Arrays.toString(tree.toArray()) + " add  peter and daniel back in");

    System.out.println(
        "containsAll: " + tree.containsAll(Arrays.asList("Peter", "Daniel", "Tom", "Adam")) + " Should be true");
    System.out.println("containsAll: "
        + tree.containsAll(Arrays.asList("Peter", "Daniel", "Tom", "Adam", "George", "Michael", "Jones", "ted"))
        + " Should be false");

    // Traverse tree
    System.out.print("Inorder (sorted): ");
    tree.inorder();
    System.out.print("\nPostorder: ");
    tree.postorder();
    System.out.print("\nPreorder: ");
    tree.preorder();
    System.out.print("\nThe number of nodes is " + tree.getSize());

    // Search for an element
    System.out.print("\nIs Peter in the tree? " +
        tree.search("Peter"));

    // Get a path from the root to Peter
    System.out.print("\nA path from the root to Peter is: ");
    ArrayList<BST.TreeNode<String>> path = tree.path("Peter");
    for (int i = 0; path != null && i < path.size(); i++)
      System.out.print(path.get(i).element + " ");
    System.out.println();

    tree.delete("Adam");
    tree.delete("Michael");
    System.out.println(
        "After delete Adam and Michal: " + Arrays.toString(tree.toArray()) + " Should be just peter, daniel,tom");
    Integer[] numbers = { 2, 4, 3, 1, 8, 5, 6, 7 };
    BST<Integer> intTree = new BST<>(numbers);
    System.out.print("\nInorder (sorted): ");
    intTree.inorder();
    System.out.println("\nIterator[Removing any number between 3 and 6]: ");
    Iterator iterator = intTree.iterator();
    while (iterator.hasNext()) {
      Object t = iterator.next();
      if (t.equals(3) || t.equals(4) || t.equals(5) || t.equals(6)) {
        iterator.remove();
        System.out.print("*");
        
      }
            System.out.print(t + " ");
    }
    System.out.println("\nAfter removing 3,4,5,6: " + Arrays.toString(intTree.toArray()));

  }
}
