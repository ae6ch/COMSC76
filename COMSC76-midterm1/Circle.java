public class Circle extends GeometricObject {
   private double radius;

   public Circle(double radius) {
      this.radius = radius;
   }

   /** Abstract method getArea */
   public  double getArea() {
      return radius * radius * Math.PI;
   }

   /** Abstract method getPerimeter */
   public  double getPerimeter() {
      return 2 * radius * Math.PI;
   }
}