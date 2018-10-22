package io.anand.learn;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RemoveComments {
    // 1. Detect the comments start,
    // 2. Remember if we are in a comment.
    // 3. Look for comments end.
    // 4. During this state of comment section, do not output the input characters.
    // 5. Since comments can be in middle of the input string, output untill the start
    // 6. of the comment string.
    //
    // Single line comments are handled without any state if we are not already in
    // a multi-line comment.

    static boolean commentStart = false;
    static boolean blockComment = false;   // /* */ style
    static String  singleChars  = "//";
    static String  blockBeginChars = "/*";
    static String  blockEndChars   = "*/";

    // Detect if we starting a new comment
    // Also determine if it is a block comment.
    // This method also outputs the characters till the start of the comment
    // to avoid looping again on the same string. Optionally can skip this if
    // only using to detect the string.
    static boolean detectCommentStart (String line, boolean outputEnabled) {
        // See if we have a single line comment
        int singleCommentIndex = line.indexOf(singleChars);

        // See if we have a block comment start
        int blockCommentIndex = line.indexOf(blockBeginChars);

        // Neither of them are present, just output the line and move on
        if ((-1 == singleCommentIndex) && (-1 == blockCommentIndex)) {
            System.out.println(line);
            return false;
        }

        // Single line comment
        if (-1 != singleCommentIndex) {
            // if it precedes block comment start, ignore all after start of the single comment.
            if ((singleCommentIndex < blockCommentIndex) || (-1 == blockCommentIndex))  {
                System.out.println(line.substring(0, singleCommentIndex));
                return false;
            }
        }

        // Block comment present
        // We ignore single comment inside block comment.
        // If we started line with the comment, no substring,
        if (-1 != blockCommentIndex)
            System.out.println(line.substring(0, blockCommentIndex));
        commentStart = blockComment = true;
        // is there an end of comment in same line ?
        return detectCommentEnd(line.substring(blockCommentIndex + blockBeginChars.length()), outputEnabled);
    }

    // Detect if we are ending a comment
    // End is only valid for a block comment
    // This method also outputs the characters till the start of the comment
    // to avoid loopoing again on the same string. Optionally can skip this if
    // only using to detect the string.
    static boolean detectCommentEnd (String line, boolean outputEnabled) {
        if (blockComment) {
            int commentIndex = line.indexOf(blockEndChars);
            // No end of the comment, So just skip...
            if (-1 == commentIndex) {
                return true;
            }
            // Found the end of the comment.
            commentStart = blockComment = false;

            // Handle any single comment
            System.out.println(line.substring(commentIndex+blockEndChars.length()));
        }
        return false;
    }

    public static void main (String[] args) throws java.lang.Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while (null != (input = br.readLine())) {
            if (false == commentStart) {
                detectCommentStart(input, true);
            } else {
                detectCommentEnd(input, true);
            }
        }
    }
}
