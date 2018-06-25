package io.anand.learn;

public class MatchingNumber {
    // you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

    class Solution {
        public int solution(int A, int B) {
            // write your code in Java SE 8

            // Divide the number A by 10 and compare till A is 0
            // 1953786 and 53
            // 1953786 / 10 = 195378 comparing with expectation that search digit is completely matched.
            // 195378/10    = 19537
            // 19537 / 10   = 1953  , It would match here, and after matching, the remaining digit count on B would 2
            boolean matchInProgress     = false;
            int     matchValue          = A;
            int     lastMatchPosition   = -1;
            while (B > 0) {
                int digit_A, digit_B;

                digit_B = B % 10;
                digit_A = matchValue % 10;
//            System.out.println("digit_A: " + digit_A + ", digit_B: "  + digit_B + ", lastMatch: "+ lastMatchPosition + ", matchValue: " + matchValue + ", match ? : " + matchInProgress);
                // see if digit_A matches digit_B [unit's place]
                if (digit_A == digit_B) {
                    matchInProgress = true;
                    matchValue = matchValue / 10;
                    // If we are done matching all digits of A in the B,
                    // Mark the position and reset the matching state machine.
                    // Start looking for next occurrence.
                    if (0 == matchValue) {
                        lastMatchPosition = B;
                        matchInProgress = false;
                    }
                } else {
                    matchInProgress = false;
                }
                // Go to next digit of B
                B /= 10;
                if (!matchInProgress)
                    matchValue = A;
            }

            // If we have a matching position value that would be latest and therefore leftmost position where A occurs in B.
            // Just count the number of digits to get the position
            // So 195 [matched time value of B] should be the last match and so 2 would position where we found the A in B.
            if (-1 != lastMatchPosition) {
                int position = 0;
                while (lastMatchPosition > 0)  {
                    position++;
                    lastMatchPosition /= 10;
                }
                lastMatchPosition = position - 1;
            }
            return lastMatchPosition;
        }
    }
}
