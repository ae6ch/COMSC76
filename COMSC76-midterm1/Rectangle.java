public class Rectangle extends GeometricObject {
   private double width;
   private double height;
 
   public Rectangle(double width, double height) {
      this.width = width;
      this.height = height;
   }
 
   /** Abstract method getArea */
   public  double getArea() {
      return width * height;
   }
 
   /** Abstract method getPerimeter */
   public  double getPerimeter() {
      return 2 * (width + height);
   }
}