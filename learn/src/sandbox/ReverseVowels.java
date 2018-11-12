package sandbox;

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
            // Go till the first vowel from left
            while ((left < right) && !isVowel(charArray[left]))
                left++;
            // Go till the first vowel from right
            while ((right > left) && !isVowel(charArray[right]))
                right--;

            // If we found the vowels swap them.
            if ((left < right) && (isVowel(charArray[left]) &&
                    isVowel(charArray[right]))) {
                char temp;
                temp             = charArray[left];
                charArray[left]  = charArray[right];
                charArray[right] = temp;
                left++;
                right--;
            }
        }
        return new String (charArray);
    }


    public static void main (String [] args) {
        String input   = "hello";
        String ooutput = reverseVowels(input);
        System.out.println("Input: " + input + ", Output: " + ooutput);
    }
}
