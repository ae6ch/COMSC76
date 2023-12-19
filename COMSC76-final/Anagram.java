import java.util.HashMap;
public class Anagram {
   public 
   public static boolean testAnagram(String s1, String s2) {
      // if s1 and s2 are anagrams, return true
      // else return false
      HashMap<Character, Integer> map = new HashMap<Character, Integer>();
      for (int i = 0; i < s1.length(); i++) {
         char c = s1.charAt(i);
         if (map.containsKey(c)) {
            map.put(c, map.get(c) + 1);
         } else {
            map.put(c, 1);
         }
      }
      for (int i = 0; i < s2.length(); i++) {
         char c = s2.charAt(i);
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