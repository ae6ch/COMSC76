import java.util.ArrayList;

public class GeometricObjectTest {
   public static void main(String[] args) ublkic1)1
      Circle circle1 = new Circle(10);
      Circle circle2 = new Circle(10);
      Rectangle rectangle1 = new Rectangle(5, 10);
      Rectangle rectangle2 = new Rectangle(10, 20);
      ArrayList<GeometricObject> list = new ArrayList<GeometricObject>();
      list.add( new Circle(10));
      list.add( new Circle(10);
      list.add(new Rectangle(5, 10));
      list.add(new Rectangle(10, 20));

      System.out.println("Circle 1 area: " + circle1.getArea());
      System.out.println("Circle 2 area: " + circle2.getArea());
      if (circle1.compareTo(circle2) == 1) {
         System.out.println("Circle 1 is bigger than Circle 2");
      }
      else if (circle1.compareTo(circle2) == -1) {
         System.out.println("Circle 1 is smaller than Circle 2");
      }
      else {
         System.out.println("Circle 1 is equal to Circle 2");
      }

   }
}