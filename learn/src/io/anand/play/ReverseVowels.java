package io.anand.play;

public class ReverseVowels {

    private static boolean isVowel(char c) {

        switch (c) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
                return true;

            default:
                return false;
        }
    }

    private static String reverseVowels (String input) {
        // Idea would be to have a pointer from the each end of the
        // string characters. When we find a vowel, swap it with the other
        // using the index.
        // Stop when the indices cross over.
        char [] charArray = input.toCharArray();
        int left          = 0;
        int right         = charArray.length - 1;

        while ( left < right ) {
            // Go till the first vowel from left and resume the loop by continuing.
            if (!isVowel(charArray[left])) {
                left++;
                continue;
            }
            // Go till the first vowel from right
            if (!isVowel(charArray[right])) {
                right--;
                continue;
            }

            // Fact we did not continue means we have two vowels and not crossed over.
            char temp;
            temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;

            // These are needed, else we will be in loop swapping same two vowels.
            left++;
            right--;
        }
        return new String (charArray);
    }


    public static void main (String [] args) {
        String input   = "aeiou";
        String ooutput = reverseVowels(input);
        System.out.println("Input: " + input + ", Output: " + ooutput);
    }
}
