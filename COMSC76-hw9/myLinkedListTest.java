import java.util.Iterator;

/**
 * test code for MyLinkedList.java methods
 * 
 * 11/13/23
 * @author Steve Rubin 0370442
 */

public class myLinkedListTest {
   public static void main(String[] args) {
      // Create a list for strings from an array. This also tests add() indirectly.
      // 10 names with some intentional duplicates
   
      MyLinkedList<String> list = new MyLinkedList<>(new String[] { "Larry", "Curly", "Curley", "Shep", "Mo", "Mo", "Bob","Tom","Howard","John","Jack","Jill" });
      System.out.println("Should be Larry Curly Curley Shep Mo Mo Bob Tom Howard John Jack Jill");
      System.out.println(list);

      list.addFirst("Zero");
      list.addLast("Last");
      list.add(2, "Sheeeeeep");
      System.out.println("should be Zero Larry Sheeeeeep Curly Curley Shep Mo Mo Bob Tom Howard John Jack Jill Last");
      System.out.println(list);

      System.out.println("Should be Zero Last Zero Last");
      System.out.println(list.getFirst()); 
      System.out.println(list.getLast()); 
      System.out.println(list.removeFirst());
      System.out.println(list.removeLast()); 
      System.out.println("should be Larry Sheeeeeep Curly Curley Shep Mo Mo Bob Tom Howard John Jack Jill");
      System.out.println(list); 
   

      System.out.println("should be Mo");
      System.out.println(list.remove(6));
      System.out.println("Should be False and then True"); 
      String t1 = "Test";
      System.out.println(list.contains(t1)); 
      list.add(t1);
      System.out.println(list.contains(t1)); 

      System.out.println("Should be Larry Sheeeeeep Curly Curley Shep Mo Bob Tom Howard John Jack Jill Test");
      System.out.println(list); 
      System.out.println("Should be Curly, then null twice, then Mo");
      System.out.println(list.get(2)); 
      System.out.println(list.get(-1));
      System.out.println(list.get(100)); 
      System.out.println(list.get(5)); 

      System.out.println("Should be 12 then True");
      System.out.println(list.indexOf(t1)); 
      System.out.println(list.remove(t1));

      System.out.println("Should be Larry Sheeeeeep Curly Curley Shep Mo Bob Tom Howard John Jack Jill");
      System.out.println("After: " + list);

      System.out.println("Should be -1 then false");
      System.out.println(list.indexOf(t1)); 
      System.out.println(list.remove(t1));
      System.out.println("Add 3 of the Test to the end, then remove them one at a time.");
      list.add(t1);
      list.add(t1);
      list.add(t1);
      System.out.println(list);

      System.out.println(list.lastIndexOf(t1));
      list.removeLast();
      System.out.println(list.lastIndexOf(t1));
      list.removeLast();

      System.out.println(list.lastIndexOf(t1)); 
      list.removeLast();

      System.out.println(list.lastIndexOf(t1)); 
      System.out.println(list);

      System.out.println("Replace the first and forth elements with Test");
      String one = list.set(0, t1);
      String three = list.set(3, t1);
      System.out.println(list.set(-1, t1)); 
      System.out.println(list.set(10, t1)); 
      System.out.println(list); 
      System.out.println("Put them back");
      list.set(0, one);
      list.set(3, three);
      System.out.println(list); 
   
      System.out.println("use iterator.remove() to get rid of the shep's");
      Iterator<String> iterator = list.iterator();
      while (iterator.hasNext()) { // removes the Sheps
         String element = iterator.next();
         if ((element != null) && (element.equals("Shep"))) {
            iterator.remove();
         }
      }
      System.out.println(list); 

      System.out.println("Test to see if looking for null works or crashs, should be false then -1, and then we add a null (returns larry), and then check to see if its in the list");
      System.out.println(list.contains(null)); 
      System.out.println(list.indexOf(null)); 
      System.out.println(list.set(0, null)); 
      System.out.println(list);
      System.out.println("Look again for null and its index, should be true and 0, then we get it (null), and then look for the last index of null (should be 0)");
      System.out.println(list.contains(null)); 
      System.out.println(list.indexOf(null));
      System.out.println(list.get(0)); 
      System.out.println(list.lastIndexOf(null));
      System.out.println("Add 3 nulls to the end, then remove them one");
      list.add(null);
      list.add(null);
      list.add(null);
      System.out.println(list);
      System.out.println(list.removeLast());
      System.out.println("Should be size 13 if we kept track correctly"); 
      System.out.println(list);
      System.out.println(list.size());
   }

}
