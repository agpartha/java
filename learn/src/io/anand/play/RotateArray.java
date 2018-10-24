package io.anand.play;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class RotateArray {

    // Complete the rotLeft function below.
    // uses one loop, not necessarily faster but less code.
    static int[] rotLeft_A(int[] a, int d) {

        // if we rotate more thsn length we just need a modulo
        // if modulo is zero dont rotate
        int size      = a.length;
        int rotateMod = d % a.length;
        if (0 == rotateMod)
            return a;

        int result [] = new int[size];
        int j = 0;
        // after rotation the new index for the value will be a function of current index
        // ex: after 2 rotations index 2 will be 0 and index 0 would be 4 [if size is 5]
        // outindex = (index + size) % rotation
        for (int i = 0; i < size; i++)
            result[j++] = a[(i + d) % size];

        return result;
    }

    // Complete the rotLeft function below.
    static int[] rotLeft(int[] a, int d) {

        // if we rotate more thsn length we just need a modulo
        // if modulo is zero dont rotate
        int size      = a.length;
        int rotateMod = d % a.length;
        if (0 == rotateMod)
            return a;

        int result [] = new int[size];
        int j = 0;
        // Get the part from the portion that we can copy over for first part
        for (int i = rotateMod; i < size;)
            result[j++] = a[i++];

        // Get the remaining part to be copied over from the begining of the array
        for (int i = 0; i < rotateMod;)
            result[j++] = a[i++];

        return result;
    }

    public static void main(String[] args) throws IOException {
        int num [] = {1,2,3,4,5};
        int[] result = rotLeft_A(num, 1);

        System.out.println("Numbers: " + Arrays.asList(num) + ", Rotated Numbers: " + Arrays.asList(result));
    }
}
