package io.anand.play;

import java.util.Arrays;

public class EasyRecursion {

    private static int factorial (int n) {
        // Base case 
        if (n == 1)
            return 1;
        // Recursive call find factorial of n -1 and multiply with current number
        // n! = n * (n - 1)!
       return n * factorial(n - 1);
    }

    private static int sum (int n) {
        
        // Base Case
        if (n == 1)
            return 1;
        // Recursive call sum up all numbers of n - 1 and add current number
        // sum(n) = n + sum(n - 1); 
        // n ( n + 1 ) / 2
        return n + sum(n - 1);   
    }
    
  
    private static int      patternCount = 0;
    private static boolean  seqUp = true;

    private static void printPatternA(String str) {
        // Base case
        // if length of the string is 10 print it.
        if (8 == str.length())
            System.out.println("Pattern (" + patternCount++ + ") is: "+  str);
        else {
            // Expanded Pattern -> for once in 3 characters switch to other
            // character first.
            if (0 == (str.length() % 3)) 
                seqUp = !seqUp;
            if (seqUp){
                // Recursive case for '\' case
                // Add a "\" to string passed to us and recurse to see if we
                // have reached a length of 10
                printPattern(str + "/");
                // Recursive case for '/' case
                // Add a "/" to string passed to us and recurse to see if we
                // have reached a length of 10
                printPattern(str + "\\");
            } else {              
                // Recursive case for '/' case
                // Add a "/" to string passed to us and recurse to see if we
                // have reached a length of 10
                printPattern(str + "\\");
                // Recursive case for '\' case
                // Add a "\" to string passed to us and recurse to see if we
                // have reached a length of 10
                printPattern(str + "/");
            }
        }
    }
    
    private static void printExoandedPattern(String str) {
//        System.out.print("Pattern (" + patternCount++ + ") is: ");
        for (int line = 0; line < 3; line++) {
            for (char c : str.toCharArray()) {
                String pat ="";
                if ( c == '/') {
                    switch (line) {
                    case 0: pat = "  /"; break;
                    case 1: pat = " / "; break;
                    case 2: pat = "/  "; break;
                    } 
                } else {
                    switch (line) {
                    case 0: pat = "\\  "; break;
                    case 1: pat = " \\ "; break;
                    case 2: pat = "  \\"; break;
                    }
                }
                System.out.print(""+pat);
            }
            System.out.println();
        }
    }

    private static void printPattern(String str) {
        // Base case
        // if length of the string is 10 print it.
        if (10 == str.length())
//            System.out.println("Pattern (" + patternCount++ + ") is: "+  str);
            printExoandedPattern(str);
        else {
            // Recursive case for '\' case
            // Add a "\" to string passed to us and recurse to see if we
            // have reached a length of 10
            printPattern(str + "/");
            // Recursive case for '/' case
            // Add a "/" to string passed to us and recurse to see if we
            // have reached a length of 10
            printPattern(str + "\\");
        }
    }
    
    private static void mountainRange (double[] range, int leftIndex, int rightIndex, double disturbanceY) {
        // Base Case
        // Adjust the middle point
        // Figure out the average height of the left and right index.
        // Generate a random number and apply it to scale the disturbance.
        // Further shift it down by half so that adjustment will yield a half of it up or down the 
        // current height. 
        int middleIndex         = (leftIndex + rightIndex) / 2;
        double averageHeight    = (range[leftIndex] + range[rightIndex]) / 2.0;
        double randDisturbance  = Math.random() * disturbanceY - (disturbanceY / 2.0);
        range[middleIndex]      = averageHeight + randDisturbance;

        System.out.println("Adjusted Mountain range (" + leftIndex + ", " + rightIndex + "): " + Arrays.toString(range));
        // Terminating condition 
        // If we are down to one index which is the case for left index is same as middle index
        // return without any more recursion.
        if (middleIndex == leftIndex) 
            return;

        // Recursive case to apply disturbance (scaled down by half) to the left half of the range
        mountainRange(range, leftIndex, middleIndex, disturbanceY / 2.0);
        // Recursive case to apply disturbance (scaled down by half) to the right half of the rang
        mountainRange(range, middleIndex, rightIndex, disturbanceY / 2.0);
    }
    
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int number = 5;
        System.out.println("Factorial of number: " + number + " is : " + factorial(number));
        System.out.println("Sum (from 1 to) number: " + number + " is : " + sum(number));
        printPattern("");
        double[]  range = new double[8];
        System.out.println("Starting Mountain range: " + Arrays.toString(range));
        mountainRange(range, 0, 7, 20.0);
        System.out.println("Adjusted Mountain range: " + Arrays.toString(range));
    }

}
