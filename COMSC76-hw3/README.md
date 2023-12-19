This assignment consists of two separate programs.

Program I:  Write a recursive method that displays a string in reverse order on the console using the following header:

      public static void reverseDisplay(String value)

To make sure this program makes efficient use of memory, have the method call a helper method that includes the string's high index:

      public static void reverseDisplay(String value, int high)

Write a program to test your recursive method by prompting the user to enter a string and then displays it in reverse order. This program is the same as Exercise 18.12 of the textbook, page 744.

Sample Output:

Enter your string: Able was I, I saw Elba (user input appears in bold adjacent the prompt)

ablE was I ,I saw elbA (this is the program output)

 

Program II: Write a recursive method to print all permutations of a string. Then a write a program to test the method, by once again, prompting the user to enter a string of characters. For example, the permutations for the string "abc" are the following:

abc

acb

bac

bca

cab

cba

Hint: Define the following two methods. The second is a helper method.

     public static void displayPermutation(String s)

     public static void displayPermutation(String s1, String s2)

The first method simply invokes displayPermutation(" ", s). The second method uses a loop to move a character from s2 to s1 and recursively invokes it with new s1 and s2. The base case is that s2 is empty and prints s1 to the console.

Write a test program that prompts the user to enter a string and displays all of its permutations.

This program is the same as Exercise 18.25 of the textbook, page 746.

