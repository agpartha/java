package io.anand.play;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class RomanNumeral {

    static HashMap<Character, Integer> romanDict;

    public RomanNumeral() {
        this.romanDict = new HashMap<>();
        initRomanDict();
    }

    private void initRomanDict () {
        romanDict.put('I', 1);
        romanDict.put('V', 5);
        romanDict.put('X', 10);
        romanDict.put('L', 50);
        romanDict.put('C', 100);
        romanDict.put('D', 500);
        romanDict.put('M', 1000);
    }

    // Convert Roman Numeral to Decimal
    static int romanToDecimal (String r) throws IllegalArgumentException {

        // Error Checks
        if (null == r) {
            return 0;
        }

        // We could convert the string to all upper case or reject it as invalid
        // depends on what assumptions and requirements we need to handle
        // for now we will not convert and raise exception when faced with
        // lower case characters same as invalid characters
        int len         = r.length();
        int num         = 0;
        int prevValue   = 0;

        // We parse the string from right to left.
        // As we find characters follow conversion logic
        // same or higher value character --> add to total
        // else subtract the value from total
        while (0 < len--) {
            Character c = r.charAt(len);

            // find the value for this roman numeral, if not found, raise exception
            Integer value = romanDict.get(c);
            if (null == value) {
                throw new IllegalArgumentException ("Aborting, found non roman numeral character: " + c);
            }

            if (value < prevValue) {
                num -= value;
            } else {
                num += value;
            }

            prevValue = value;
        }
        return num;
    }

    // Convert Decimal to Roman Numeral
    static String decimalToRoman(int n) {
        // One approach is to go from right to left and get the number in
        // the place value and covert to equivalent roman numerals.
        return "MCVXL";
    }

    public static void main(String[] args) throws IOException {
        RomanNumeral romanNumeral = new RomanNumeral();
        String r;

        r = "MMCDXXI";
        System.out.println("Roman Number: " +  r + ", Decimal Number: " + romanNumeral.romanToDecimal(r));
        r = "MLXVI";
        System.out.println("Roman Number: " +  r + ", Decimal Number: " + romanNumeral.romanToDecimal(r));
        r = "CLX";
        System.out.println("Roman Number: " +  r + ", Decimal Number: " + romanNumeral.romanToDecimal(r));
        r = "CCVII";
        System.out.println("Roman Number: " +  r + ", Decimal Number: " + romanNumeral.romanToDecimal(r));
        r = "MDCCLXXVI";
        System.out.println("Roman Number: " +  r + ", Decimal Number: " + romanNumeral.romanToDecimal(r));
        r = "MCMLIV";
        System.out.println("Roman Number: " +  r + ", Decimal Number: " + romanNumeral.romanToDecimal(r));
        r = "MMXIV";
        System.out.println("Roman Number: " +  r + ", Decimal Number: " + romanNumeral.romanToDecimal(r));
        r = "MMXIX";
        System.out.println("Roman Number: " +  r + ", Decimal Number: " + romanNumeral.romanToDecimal(r));
        r = "XLIIII";
        System.out.println("Roman Number: " +  r + ", Decimal Number: " + romanNumeral.romanToDecimal(r));
        r = "MDCCCCX";
        System.out.println("Roman Number: " +  r + ", Decimal Number: " + romanNumeral.romanToDecimal(r));
        r = "MCMX";
        System.out.println("Roman Number: " +  r + ", Decimal Number: " + romanNumeral.romanToDecimal(r));
    }
}