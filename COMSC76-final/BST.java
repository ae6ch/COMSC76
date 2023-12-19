import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * print smaller and larger nodes in a BST
 * 
 * @author Steve Rubin 0370442
 */

public class BST<E extends Comparable<E>> implements Tree<E> {
  protected TreeNode<E> root;
  protected int size = 0;

  public static void main(String[] args) {
    BST<Integer> tree = new BST<>();
    tree.addAll(Arrays.asList(100, 90, 80, 95, 92, 125, 110, 130, 132));
    System.out.println("99: " + tree.findNextSmallerAndBigger(new TreeNode<>(99)));
    System.out.println("132: " + tree.findNextSmallerAndBigger(new TreeNode<>(132)));
    System.out.println("10: " + tree.findNextSmallerAndBigger(new TreeNode<>(10)));
    System.out.println("200: " + tree.findNextSmallerAndBigger(new TreeNode<>(200)));
    
    System.out.println("isPerfectBST: " + tree.isPerfectBST());


  }

/** 
 * Returns true if the tree is a perfect BST
 */
  public boolean isPerfectBST() {
    return getSize() == (Math.pow(2, height() + 1) - 1);
}

public int height() {
  return height(root);
}

private int height(TreeNode<E> root) {
  if (root == null) {
      return -1;
  }

  int leftHeight = height(root.left);
  int rightHeight = height(root.right);

  return 1 + Math.max(leftHeight, rightHeight);
}

  /**
   * Returns the next smaller and bigger nodes in the tree
   * 
   * @param x node to find next smaller and bigger nodes for
   * @return NodePair containing the next smaller and bigger nodes
   */
  public NodePair<E> findNextSmallerAndBigger(TreeNode<E> x) {
    TreeNode<E> smaller = null;
    TreeNode<E> bigger = null;
    TreeNode<E> current = root;
    while (current != null) {
      if (x.element.compareTo(current.element) < 0) {
        bigger = current;
        current = current.left;
      } else if (x.element.compareTo(current.element) > 0) {
        smaller = current;
        current = current.right;
      } else {
        if (current.left != null) {
          TreeNode<E> temp = current.left;
          while (temp.right != null) {
            temp = temp.right;
          }
          smaller = temp;
        }
        if (current.right != null) {
          TreeNode<E> temp = current.right;
          while (temp.left != null) {
            temp = temp.left;
          }
          bigger = temp;
        }
        break;
      }
    }
    return new NodePair<>(smaller, bigger);

  }

  /** Create a default binary tree */
  public BST() {
  }

  /** Create a binary tree from an array of objects */
  public BST(E[] objects) {
    for (int i = 0; i < objects.length; i++)
      add(objects[i]);
  }

  @Override /** Returns true if the element is in the tree */
  public boolean search(E e) {
    TreeNode<E> current = root; // Start from the root

    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      } else if (e.compareTo(current.element) > 0) {
        current = current.right;
      } else // element matches current.element
        return true; // Element is found
    }

    return false;
  }

  @Override /**
             * Insert element e into the binary tree
             * Return true if the element is inserted successfully
             */
  public boolean insert(E e) {
    if (root == null)
      root = createNewNode(e); // Create a new root
    else {
      // Locate the parent node
      TreeNode<E> parent = null;
      TreeNode<E> current = root;
      while (current != null)
        if (e.compareTo(current.element) < 0) {
          parent = current;
          current = current.left;
        } else if (e.compareTo(current.element) > 0) {
          parent = current;
          current = current.right;
        } else
          return false; // Duplicate node not inserted

      // Create the new node and attach it to the parent node
      if (e.compareTo(parent.element) < 0)
        parent.left = createNewNode(e);
      else
        parent.right = createNewNode(e);
    }

    size++;
    return true; // Element inserted successfully
  }

  protected TreeNode<E> createNewNode(E e) {
    return new TreeNode<>(e);
  }

  @Override /** Inorder traversal from the root */
  public void inorder() {
    inorder(root);
  }

  /** Inorder traversal from a subtree */
  protected void inorder(TreeNode<E> root) {
    if (root == null)
      return;
    inorder(root.left);
    System.out.print(root.element + " ");
    inorder(root.right);
  }

  @Override /** Postorder traversal from the root */
  public void postorder() {
    postorder(root);
  }

  /** Postorder traversal from a subtree */
  protected void postorder(TreeNode<E> root) {
    if (root == null)
      return;
    postorder(root.left);
    postorder(root.right);
    System.out.print(root.element + " ");
  }

  @Override /** Preorder traversal from the root */
  public void preorder() {
    preorder(root);
  }

  /** Preorder traversal from a subtree */
  protected void preorder(TreeNode<E> root) {
    if (root == null)
      return;
    System.out.print(root.element + " ");
    preorder(root.left);
    preorder(root.right);
  }

  public class NodePair<E> {
    TreeNode<E> smaller;
    TreeNode<E> bigger;

    public NodePair(TreeNode<E> smaller, TreeNode<E> bigger) {
      this.smaller = smaller;
      this.bigger = bigger;
    }

    public String toString() {
      String smallerString = (smaller != null && smaller.element != null) ? smaller.element.toString() : "null";
      String biggerString = (bigger != null && bigger.element != null) ? bigger.element.toString() : "null";

      return "Smaller: " + smallerString + " Bigger: " + biggerString;
    }
  }

  public static class TreeNode<E> {
    protected E element;
    protected TreeNode<E> left;
    protected TreeNode<E> right;

    public TreeNode(E e) {
      element = e;
    }
  }

  @Override /** Get the number of nodes in the tree */
  public int getSize() {
    return size;
  }

  /** Returns the root of the tree */
  public TreeNode<E> getRoot() {
    return root;
  }

  /** Returns a path from the root leading to the specified element */
  public java.util.ArrayList<TreeNode<E>> path(E e) {
    java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<>();
    TreeNode<E> current = root; // Start from the root

    while (current != null) {
      list.add(current); // Add the node to the list
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      } else if (e.compareTo(current.element) > 0) {
        current = current.right;
      } else
        break;
    }

    return list; // Return an array list of nodes
  }

  @Override /**
             * Delete an element from the binary tree.
             * Return true if the element is deleted successfully
             * Return false if the element is not in the tree
             */
  public boolean delete(E e) {
    // Locate the node to be deleted and also locate its parent node
    TreeNode<E> parent = null;
    TreeNode<E> current = root;
    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        parent = current;
        current = current.left;
      } else if (e.compareTo(current.element) > 0) {
        parent = current;
        current = current.right;
      } else
        break; // Element is in the tree pointed at by current
    }

    if (current == null)
      return false; // Element is not in the tree

    // Case 1: current has no left child
    if (current.left == null) {
      // Connect the parent with the right child of the current node
      if (parent == null) {
        root = current.right;
      } else {
        if (e.compareTo(parent.element) < 0)
          parent.left = current.right;
        else
          parent.right = current.right;
      }
    } else {
      // Case 2: The current node has a left child
      // Locate the rightmost node in the left subtree of
      // the current node and also its parent
      TreeNode<E> parentOfRightMost = current;
      TreeNode<E> rightMost = current.left;

      while (rightMost.right != null) {
        parentOfRightMost = rightMost;
        rightMost = rightMost.right; // Keep going to the right
      }

      // Replace the element in current by the element in rightMost
      current.element = rightMost.element;

      // Eliminate rightmost node
      if (parentOfRightMost.right == rightMost)
        parentOfRightMost.right = rightMost.left;
      else
        // Special case: parentOfRightMost == current
        parentOfRightMost.left = rightMost.left;
    }

    size--;
    return true; // Element deleted successfully
  }

  @Override /** Obtain an iterator. Use inorder. */
  public java.util.Iterator<E> iterator() {
    return new InorderIterator();
  }

  // Inner class InorderIterator
  private class InorderIterator implements java.util.Iterator<E> {
    // Store the elements in a list
    private java.util.ArrayList<E> list = new java.util.ArrayList<>();
    private int current = 0; // Point to the current element in list

    public InorderIterator() {
      inorder(); // Traverse binary tree and store elements in list
    }

    /** Inorder traversal from the root */
    private void inorder() {
      inorder(root);
    }

    /** Inorder traversal from a subtree */
    private void inorder(TreeNode<E> root) {
      if (root == null)
        return;
      inorder(root.left);
      list.add(root.element);
      inorder(root.right);
    }

    @Override /** More elements for traversing? */
    public boolean hasNext() {
      if (current < list.size())
        return true;

      return false;
    }

    @Override /** Get the current element and move to the next */
    public E next() {
      return list.get(current++);
    }

    @Override // Remove the element returned by the last next()
    public void remove() {
      if (current == 0) // next() has not been called yet
        throw new IllegalStateException();

      delete(list.get(--current));
      list.clear(); // Clear the list
      inorder(); // Rebuild the list
    }
  }

  @Override /** Remove all elements from the tree */
  public void clear() {
    root = null;
    size = 0;
  }

}

/**
 * Tree modified from 25.5 to add toArray (both toArray() and
 * toArray(E[] a) methods), retainAll,
 * removeAll, addAll, and containsAll
 * 
 * 11/19/23
 * 
 * @author Steve Rubin 0370442
 */
interface Tree<E> extends Collection<E> {
  public default void shuffle() {
    List<E> elements = new ArrayList<>(this); // Create a list from tree elements
    Collections.shuffle(elements); // Shuffle the list

    // Reconstruct the tree from the shuffled elements
    clear(); // Clear the existing tree
    elements.forEach(this::insert); // Insert shuffled elements into the tree
  }

  /** Return true if the element is in the tree */
  public boolean search(E e);

  /**
   * Insert element e into the binary tree
   * Return true if the element is inserted successfully
   */
  public boolean insert(E e);

  /**
   * Delete the specified element from the tree
   * Return true if the element is deleted successfully
   */
  public boolean delete(E e);

  /** Get the number of elements in the tree */
  public int getSize();

  /** Inorder traversal from the root */
  public default void inorder() {
  }

  /** Postorder traversal from the root */
  public default void postorder() {
  }

  /** Preorder traversal from the root */
  public default void preorder() {
  }

  @Override /** Return true if the tree is empty */
  public default boolean isEmpty() {
    return this.size() == 0;
  }

  @Override
  public default boolean contains(Object e) {
    return search((E) e);
  }

  @Override
  public default boolean add(E e) {
    return insert(e);
  }

  @Override
  public default boolean remove(Object e) {
    return delete((E) e);
  }

  @Override
  public default int size() {
    return getSize();
  }

  @Override
  public default boolean containsAll(Collection<?> c) {
    return c.stream().allMatch(this::contains);
  }

  @Override
  public default boolean addAll(Collection<? extends E> c) {
    return c.stream().allMatch(this::add);
  }

  @Override
  public default boolean removeAll(Collection<?> c) {
    return c.stream().allMatch(this::remove);
  }

  @Override
  public default boolean retainAll(Collection<?> c) {
    return !removeIf(e -> !c.contains(e));

  }

  @Override
  public default Object[] toArray() {
    return toArray(new Object[size()]);
  }

  @Override
  public default <T> T[] toArray(T[] array) {
    int size = size();
    // If the array is too small, allocate a new array of the same type
    if (array.length < size) {
      return Arrays.copyOf(Arrays.copyOf(array, size), size, (Class<? extends T[]>) array.getClass());
    }
    int i = 0;
    for (Object element : this) {
      array[i++] = (T) element;
    }
    if (array.length > size) { // null terminate the array in case it's too big
      array[size] = null;
    }
    return array;
  }

}