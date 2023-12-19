import java.util.ArrayList;

public class Shuffle {
   public static <E> void shuffle5(ArrayList<E> list) {
      for (int i = 0; i < 5; i++) {
         int r = (int) (Math.random() * list.size());
         E t = list.get(i);
         list.set(i, list.get(r));
         list.set(r, t);
      }

   }
   public static void main(String[] args) {
      ArrayList<Integer> list = new ArrayList<Integer>();
      for (int i = 0; i < 25; i++) 
         list.add(i);
         
      System.out.println(list);
      shuffle5(list);
      System.out.println(list);

   }
}
