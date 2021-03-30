package io.anand.play;

public class StringNumberAddition {

    private static class CarryPlace {
        int carry               = 0;
        int place               = 1;
        int result = 0;
        StringBuilder sumString = new StringBuilder();
    }
    private static CarryPlace carryPlace = new CarryPlace();

    private static class BorrowPlace {
        int borrow              = 0;
        int place               = 1;
        int result              = 0;
        StringBuilder subString = new StringBuilder();
    }
    private static BorrowPlace borrowPlace = new BorrowPlace();

    private static void addStringDigits (char digOne, char digTwo) {
        int val             = carryPlace.carry + (digOne - '0') + digTwo - '0';
        carryPlace.carry    = val / 10;
        carryPlace.result += (val % 10) * carryPlace.place;
        carryPlace.sumString.insert(0, (char)((val % 10) + '0'));
        carryPlace.place   *= 10;
    }

    private static void addStringDigits (char digOne) {
        addStringDigits(digOne, '0');
    }

    private static void addStringDigits () {
        addStringDigits('0', '0');
    }

    private static void subStringDigits (char digOne, char digTwo) {
        int dig1 = (digOne - '0');
        int dig2 = (digTwo - '0');

        int val = dig1 - dig2 - borrowPlace.borrow;
        if (val >= 0) {
            val = dig1 - dig2;
            borrowPlace.borrow = 0;
        } else {
            borrowPlace.borrow = 1;
            val += 10;
        }
        borrowPlace.result  += (val % 10) * borrowPlace.place;
        borrowPlace.subString.insert(0, (char)((val % 10) + '0'));
        borrowPlace.place   *= 10;
    }

    private static void subStringDigits (char digOne) {
        subStringDigits(digOne, '0');
    }

    private static void subStringDigits () {
        subStringDigits('0', '0');
    }

        // Start from the end of both strings and add them one place value
    // at a time, do account for carry
    private static int addStringNumbers (String numOne, String numTwo) {
        // Sanitize the inputs
        // Null strings, empty strings
        // non numeric characters in the input
        // define the expectation and return a defined output to indicate invalid input

        int len1    = numOne.length();
        int len2    = numTwo.length();
        // Determine the long string and make it as numOne
        // This helps rest of the logic to treat indices and
        // strings to pick for adding remaining digits from the longer
        // string
        // Add remaining digits from longer string
        if (len1 < len2) {
            String temp = numTwo;
            numTwo      = numOne;
            numOne      = temp;
            int    len  = len2;
            len2        = len1;
            len1        = len;
        }

        // Now numOne is the longer string and len1 is it's length
        // Need the diff to use the offset for loop on longer string
        int diff = len1 - len2;

        // Will traverse both till we finish the shorter string length
        for (int i = len2 - 1; i >= 0; i--)
            addStringDigits(numOne.charAt(i + diff), numTwo.charAt(i));

        // Remaining digits from longer number
        // not a big one here, but can refactor this logic to reduce duplication and errors
        for (int i = diff - 1; i >= 0; i--)
            addStringDigits(numOne.charAt(i));

        // Last addition would have possible left acarry and do not
        // ignore the place value for this carry and add it
        addStringDigits();
        System.out.println("Sum String: " + carryPlace.sumString);
        return carryPlace.result;
    }

    private static int addStringNumbersTwo (String numOne, String numTwo) {
        // Sanitize the inputs
        // Null strings, empty strings
        // non numeric characters in the input
        // define the expectation and return a defined output to indicate invalid input

        int len1    = numOne.length();
        int len2    = numTwo.length();

        // Will traverse both till we finish the shorter string length
        int i = numOne.length() - 1;
        int j = numTwo.length() - 1;
        while (i >= 0 || j >= 0) {
            addStringDigits(i >= 0 ? numOne.charAt(i): '0', j >= 0 ? numTwo.charAt(j) : '0');
            i--;
            j--;
        }

        // Last addition would have possible left a carry and do not
        // ignore the place value for this carry and add it
        addStringDigits();
        System.out.println("\nSum String: " + carryPlace.sumString);
        return carryPlace.result;
    }


    private static int subStringNumbers (String numOne, String numTwo) {
        // Sanitize the inputs
        // Null strings, empty strings
        // non numeric characters in the input
        // define the expectation and return a defined output to indicate invalid input

        int len1    = numOne.length();
        int len2    = numTwo.length();

        // Will traverse both till we finish the shorter string length
        int i = numOne.length() - 1;
        int j = numTwo.length() - 1;
        while (i >= 0 || j >= 0) {
            subStringDigits((i >= 0 ? numOne.charAt(i) : '0'), (j >= 0 ? numTwo.charAt(j) : '0'));
            i--;
            j--;
        }

        // Last addition would have possible left a borrow and do not
        // ignore the place value for this borrow and subtract it
        subStringDigits();
        System.out.println("\nSub String: " + borrowPlace.subString);
        return borrowPlace.result;
    }

    public static void main(String []args){
        String numOne = "900097894";
        String numTwo = "0050087";

        System.out.println("Input: number 1: " + numOne + ", number 2: " + numTwo);
        System.out.println("Sum (1): " + addStringNumbers(numOne, numTwo));
        System.out.println("Sum (2): " + addStringNumbersTwo(numOne, numTwo));
        System.out.println("Sub (1): " + subStringNumbers(numOne, numTwo));

    }
}
