
import java.util.Comparator;
import java.util.Arrays;

public class Steve_Rubin_Test2 {
   public static void main(String[] args) {
      Integer[] list = { 2, 3, 2, 5, 6, 1, -2, 3, 14, 12 };

      mergeSort(list);

      for (int i = 0; i < list.length; i++) {
         System.out.print(list[i] + " ");

      }
      System.out.println();

      Circle[] list1 = { new Circle(2), new Circle(3), new Circle(2),
            new Circle(5), new Circle(6), new Circle(1), new Circle(2),
            new Circle(3), new Circle(14), new Circle(12) };
      mergeSort(list1, new CircleComparator());
      for (int i = 0; i < list1.length; i++) {
         System.out.print(list1[i] + " ");
      }
            System.out.println();

   }

   /**
    * Merge Sort algorithm
    *
    * @param list to be sorted
    * @return runtime in milliseconds
    */
   public static <E extends Comparable<E>> void mergeSort(E[] list) {

      if (list == null || list.length < 2) {
         return;
      }

      int mid = list.length / 2;
      E[] left = Arrays.copyOfRange(list, 0, mid);
      E[] right = Arrays.copyOfRange(list, mid, list.length);
            mergeSort(left);
      mergeSort(right);

      merge(list, left, right);

   }

   private static <E extends Comparable<E>> void merge(E[] list, E[] left, E[] right) {

      int i = 0;
      int j = 0;
      int k = 0;
      while (i < left.length && j < right.length) {
         if (left[i].compareTo(right[j]) <= 0) {
            list[k] = left[i];
            i++;
         } else {
            list[k] = right[j];
            j++;
         }
         k++;
      }
      while (i < left.length) {
         list[k] = left[i];
         i++;
         k++;
      }
      while (j < right.length) {
         list[k] = right[j];
         j++;
         k++;
      }
   }
   

   public static <E> void mergeSort(E[] list, Comparator<? super E> comparator) {

      if (list == null || list.length < 2) {
         return;
      }

      int mid = list.length / 2;
      E[] left = Arrays.copyOfRange(list, 0, mid);
      E[] right = Arrays.copyOfRange(list, mid, list.length);

      mergeSort(left, comparator);
      mergeSort(right, comparator);
      merge(list, comparator, left, right);

   }

   private static <E> void merge(E[] list, Comparator<? super E> comparator, E[] left, E[] right) {
      int i = 0;
      int j = 0;
      int k = 0;
      while (i < left.length && j < right.length) {
         if (comparator.compare(left[i], right[j]) <= 0) {
            list[k] = left[i];
            i++;
         } else {
            list[k] = right[j];
            j++;
         }
         k++;
      }
      while (i < left.length) {
         list[k] = left[i];
         i++;
         k++;
      }
      while (j < right.length) {
         list[k] = right[j];
         j++;
         k++;
      }
   }

}

class CircleComparator implements Comparator<Circle> {
   public int compare(Circle c1, Circle c2) {
      if (c1.getRadius() < c2.getRadius()) {
         return -1;
      } else if (c1.getRadius() == c2.getRadius()) {
         return 0;
      } else {
         return 1;
      }
   }
}

class Circle extends GeometricObject implements Comparable<Circle> {
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

   public int compareTo(Circle o) {
      if (this.getRadius() > o.getRadius())
         return 1;
      else if (this.getRadius() < o.getRadius())
         return -1;
      else
         return 0;
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
      return "" + radius;
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
   // public String toString() {
   // return "created on " + dateCreated + "\ncolor: " + color +
   // " and filled: " + filled;
   // }

   /** Abstract method getArea */
   public abstract double getArea();

   /** Abstract method getPerimeter */
   public abstract double getPerimeter();
}