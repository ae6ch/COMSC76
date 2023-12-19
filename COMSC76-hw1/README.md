This is Programming Exercise 13.1 on page 535 of the course text (11th Edition).

Design a new Triangle class that extends the abstract GeometricObject Download GeometricObject class. (The source code for this abstract class can be found in chapters 9, 11, & 13 among the source files for the text. Write a test program that prompts the user to enter three sides of the triangle (make sure they define an actual triangle), a color, and a Boolean value to indicate whether the triangle is filled. The program should then create a Triangle object with these sides and set the color and filled properties using the input. In addition, have the program make use of constructors to add circle and rectangle objects. The program should display, for each of these geometric objects, the area, perimeter, color, and true or false to indicate whether it is filled or not. Place all of the classes in a single Java soruce file named TestGeometricObjects.java. Make sure that the class that contains the main method is also named public class TestGeometricObjects, and that the Triangle, Circle, and Rectangle classes are not public.

The output for this program might be fornatted to look something like the following:

Please enter the sides of a triangle: 2 4 78
Unable to create a triangle with those sides.

Please enter the sides of a triangle: 3 4 5
What is the color of the triangle: red
Is the triangle filled(true/false)?: true

Triangle:
Area: 6.0
Perimeter: 12.0
Color: red
Filled: true

Circle:
Area: 12.56
Perimeter: 12.56
Color: yellow
Filled: true

Rectangle:
Area 8.0
Perimeter 12.0
Color: red
Filled: false
