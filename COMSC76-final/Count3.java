public class Count3 {
   public static void main(String[] args) {
      System.out.println(count3(8));
      System.out.println(count3(331));
      System.out.println(count3(3333));
   }
   //The recursive method count3 will return the number of 3s in an integer
public static int count3(int n) {
      return count3(n, 0);
  }
  
  private static int count3(int n, int number) {
      if (n == 0) 
          return number;
        if (n % 10 == 3) 
          return count3(n / 10, number + 1);

          return count3(n / 10, number);
      
  }
}