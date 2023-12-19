import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Tree modified from 25.5 to add toArray (both toArray() and
 * toArray(E[] a) methods), retainAll,
 * removeAll, addAll, and containsAll
 * 
 * 11/19/23
 * 
 * @author Steve Rubin 0370442
 */
public interface Tree<E> extends Collection<E> {
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