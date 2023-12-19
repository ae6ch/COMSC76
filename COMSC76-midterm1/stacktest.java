import java.util.Stack;

public class stacktest {
   public static void main(String [] args) {
      Stack<String> lang = new Stack<String>();
      lang.push("Python");
      lang.add("Java");
      lang.push("C++");
      System.out.println(lang.pop());
       System.out.println(lang.pop());
      System.out.println(lang.pop());
      System.out.println(lang.pop());
   }
