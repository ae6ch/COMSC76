import java.util.Scanner;

/**
 * TestGeometricObjects
 * 
 * @author Steve Rubin
 *         COMSC-076-204
 *         2023/08/29
 * 
 *         Implements concrete classes for Triangle, Circle, and Rectangle that
 *         extend
 *         GeometricObject from the textbook, also included below.
 * 
 *         This program prompts the user to enter the sides of a triangle, the
 *         color of
 *         the triangle, and whether or not the triangle is filled. It then
 *         creates a
 *         Triangle object. It also creates a Circle object and a Rectangle
 *         object using
 *         static data.
 *         It then displays the object state for each object, and other
 *         appropriate
 *         information.
 * 
 */
public class TestGeometricObjects {
   static Scanner in = new Scanner(System.in);

   public static void main(String[] args) {
      double[] sides = null;
      while (sides == null) {
         sides = getDoubleArray("Please enter the sides of a triangle: ", 3);
         if (!Triangle.isValid(sides[0], sides[1], sides[2])) {
            System.out.println("Invalid triangle. Try again.");
            sides = null;
         }
      }

      String color = getString("What is the color of the triangle: ");
      boolean filled = getBoolean("Is the triangle filled? (true/false): ");
      Triangle triangle = new Triangle(sides[0], sides[1], sides[2], color, filled);
      Circle circle = new Circle(2, "yellow", true);
      Rectangle rectangle = new Rectangle(4, 2, "red", false);

      System.out.println("Triangle:\n" + triangle);
      System.out.println("\nCircle:\n" + circle);
      System.out.println("\nRectangle:\n" + rectangle);

   }

   /**
    * Get an array of doubles from the user
    * 
    * @param prompt prompt to display to the user
    * @param size   number of elements in the array
    * @return
    */
   private static double[] getDoubleArray(String prompt, int size) {
      double[] sides = new double[size];

      try {
         System.out.print(prompt);
         for (int i = 0; i < sides.length; i++) {
            sides[i] = in.nextDouble();
         }
         in.nextLine(); // discard any extra elements from the buffer

      } catch (Exception e) {
         System.out.println("Invalid input.");
         System.exit(1);
      }
      return sides;
   }

   /**
    * Get a string from the user
    * 
    * @param prompt prompt to display to the user
    * @return string entered by the user
    */
   private static String getString(String prompt) {
      String str = "";
      Scanner in = new Scanner(System.in);
      try {
         System.out.print(prompt);
         str = in.nextLine();
      } catch (Exception e) {
         System.out.println("Invalid input.");
         System.exit(1);
      }
      return str;
   }

   /**
    * Get a boolean from the user
    * 
    * @param prompt prompt to display to the user
    * @return boolean entered by the user
    */
   private static boolean getBoolean(String prompt) {
      boolean bool = false;
      Scanner in = new Scanner(System.in);
      try {
         System.out.print(prompt);
         bool = in.nextBoolean();
      } catch (Exception e) {
         System.out.println("Invalid input.");
         System.exit(1);
      }
      return bool;
   }
}

/**
 * The Triangle class
 * 
 */
class Triangle extends GeometricObject {
   private double a;
   private double b;
   private double c;

   /**
    * Construct a Triangle object with specified a,b,c sides
    * 
    * @param a side a
    * @param b side b
    * @param c side c
    */
   public Triangle(double a, double b, double c) {
      this.a = a;
      this.b = b;
      this.c = c;
   }

   /**
    * Construct a Triangle object with specified a,b,c sides, color and filled
    * 
    * @param a      side a
    * @param b      side b
    * @param c      side c
    * @param color  of triangle
    * @param filled true if filled, false otherwise
    */
   public Triangle(double a, double b, double c, String color, boolean filled) {
      super(color, filled);
      this.a = a;
      this.b = b;
      this.c = c;
   }

   /**
    * Return side a
    * 
    * @return side a
    */
   public double getA() {
      return a;
   }

   /**
    * Return side b
    * 
    * @return side b
    */
   public double getB() {
      return b;
   }

   /**
    * Return side c
    * 
    * @return side c
    */
   public double getC() {
      return c;
   }

   /**
    * Set a new side a
    * 
    * @param a side a
    */
   public void setA(double a) {
      this.a = a;
   }

   /**
    * Set a new side b
    * 
    * @param b side b
    */

   public void setB(double b) {
      this.b = b;
   }

   /**
    * Set a new side c
    * 
    * @param c side c
    */

   public void setC(double c) {
      this.c = c;
   }

   /**
    * Return area of this triangle
    * 
    * @return area of this triangle
    */
   public double getArea() {
      double s = (a + b + c) / 2;
      return Math.sqrt(s * (s - a) * (s - b) * (s - c));
   }

   /**
    * Return perimeter of this triangle
    * 
    * @return perimeter of this triangle
    */
   public double getPerimeter() {
      return a + b + c;
   }

   public static boolean isValid(double a, double b, double c) {
      return a + b > c && a + c > b && b + c > a;
   }

   /** Return a string description for the triangle */
   @Override
   public String toString() {
      return "a = " + a + " b = " + b + " c = " + c + "\narea = " + getArea() + " perimeter = "
            + getPerimeter() + "\n" + super.toString();
   }
}

/**
 * The Rectangle class
 */
class Rectangle extends GeometricObject {
   private double height;
   private double width;

   /**
    * Construct a Rectangle object with specified height and width
    * 
    * @param height
    * @param width
    */
   public Rectangle(double height, double width) {
      this.height = height;
      this.width = width;
   }

   /**
    * Construct a Rectangle object with specified height, width, color and filled
    * 
    * @param height
    * @param width
    * @param color  color of rectangle
    * @param filled true if filled, false otherwise
    */
   public Rectangle(double height, double width, String color, boolean filled) {
      super(color, filled);
      this.height = height;
      this.width = width;
   }

   /**
    * Return height
    * 
    * @return height
    */
   public double getHeight() {
      return height;
   }

   /**
    * Return width
    * 
    * @return width
    */
   public double getWidth() {
      return width;
   }

   /**
    * Set a new height
    * 
    * @param height
    */

   public void setHeight(double height) {
      this.height = height;
   }

   /**
    * Set a new width
    * 
    * @param width
    */

   public void setWidth(double width) {
      this.width = width;
   }

   /**
    * Return area of this rectangle
    * 
    * @return area of this rectangle
    */
   public double getArea() {
      return (height * width);
   }

   /**
    * Return perimeter of this rectangle
    * 
    * @return perimeter of this rectangle
    */
   public double getPerimeter() {
      return (2 * (height + width));
   }

   /**
    * Return true if height and width are valid
    * 
    * @param height
    * @param width
    * @return true if height and width are valid
    */
   public static boolean isValid(double height, double width) {
      return height > 0 && width > 0;
   }

   @Override
   public String toString() {
      return "height = " +
            height + " " +
            "width = " + width + "\narea = " + getArea() + " perimeter = "
            + getPerimeter() + "\n" + super.toString();
   }
}

/**
 * The Circle class
 */

class Circle extends GeometricObject {
   private double radius;

   /**
    * Construct a Circle object with specified radius
    * 
    * @param radius of circle
    */
   public Circle(double radius) {
      this.radius = radius;
   }

   /**
    * Construct a Circle object with specified radius, color and filled
    * 
    * @param radius of circle
    * @param color  of circle
    * @param filled true if filled, false otherwise
    */
   public Circle(double radius, String color, boolean filled) {
      super(color, filled);
      this.radius = radius;
   }

   /**
    * Return radius
    * 
    * @return radius
    */
   public double getRadius() {
      return radius;
   }

   /**
    * Set a new radius
    * 
    * @param radius
    */
   public void setRadius(double radius) {
      this.radius = radius;
   }

   /**
    * Return perimeter of this circle
    * 
    * @return perimeter of this circle
    */
   public double getPerimeter() {
      return 2 * Math.PI * radius;
   }

   /**
    * Return area of this circle
    * 
    * @return area of this circle
    * 
    */

   public double getArea() {
      return Math.PI * Math.pow(radius, 2);
   }

   /**
    * Return true if radius is valid
    * 
    * @param radius
    * @return
    */
   public static boolean isValid(double radius) {
      return radius > 0;
   }

   /**
    * Return a string representation of this object
    * 
    * @return string representation of this object
    */
   @Override
   public String toString() {
      return "radius = " + radius + "\narea = " + getArea() + " perimeter = "
            + getPerimeter() + "\n" + super.toString();
   }
}

/**
 * The GeometricObject class from the textbook
 * 
 */
abstract class GeometricObject {
   private String color = "white";
   private boolean filled;
   private java.util.Date dateCreated;

   /** Construct a default geometric object */
   protected GeometricObject() {
      dateCreated = new java.util.Date();
   }

   /** Construct a geometric object with color and filled value */
   protected GeometricObject(String color, boolean filled) {
      dateCreated = new java.util.Date();
      this.color = color;
      this.filled = filled;
   }

   /** Return color */
   public String getColor() {
      return color;
   }

   /** Set a new color */
   public void setColor(String color) {
      this.color = color;
   }

   /**
    * Return filled. Since filled is boolean,
    * the get method is named isFilled
    */
   public boolean isFilled() {
      return filled;
   }

   /** Set a new filled */
   public void setFilled(boolean filled) {
      this.filled = filled;
   }

   /** Get dateCreated */
   public java.util.Date getDateCreated() {
      return dateCreated;
   }

   /** Return a string representation of this object */
   public String toString() {
      return "created on " + dateCreated + "\ncolor: " + color +
            " and filled: " + filled;
   }

   /** Abstract method getArea */
   public abstract double getArea();

   /** Abstract method getPerimeter */
   public abstract double getPerimeter();
}