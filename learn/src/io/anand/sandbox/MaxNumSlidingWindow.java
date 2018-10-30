package io.anand.sandbox;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class MaxNumSlidingWindow {

    private static int windowSize = 3;
    public static void main (String [] args) {
        int maxNum = Integer.MIN_VALUE;
        Scanner scanner = new Scanner(System.in);;

        while (true) {
            int i = 0;
            int [] numbers = new int [windowSize];

            System.out.println("Please provide " + windowSize + " numbers");

            while (i < windowSize)
                numbers[i++] = scanner.nextInt();

            System.out.println("Input: " + Arrays.toString(numbers));
            System.out.println("Max number so far: " + maxNum);
        }
    }
}
