import java.util.HashMap;
public class TestAnagram {
   public static void main(String[] args) {
      String s1 = "cbc";
      String s2 = "cba";
      System.out.println(isAnagram(s1, s2));
   }
   private static boolean isAnagram(String s1, String s2) {
      HashMap<Character, Integer> map = new HashMap<>();
      for (char c: s1.toCharArray()) {
         if (map.containsKey(c)) {
            map.put(c, map.get(c) + 1);
         } else {
            map.put(c, 1);
         }
      }
      for (char c : s2.toCharArray()) {
         if (map.containsKey(c)) {
            map.put(c, map.get(c) - 1);
         } else {
            return false;
         }
      }
      for (char c : map.keySet()) {
         if (map.get(c) != 0) {
            return false;
         }
      }
      return true;
   }
}