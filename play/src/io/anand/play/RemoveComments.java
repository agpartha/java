package io.anand.play;

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

    private enum CommentState  {
            NOT_IN_COMMENT,
            MAYBE_COMMENT_START,
            IN_SINGLE_LINE_COMMENT,
            IN_MULTI_LINE_COMMENT,
            MAYBE_COMMENT_END
    }

    private static CommentState commentState = CommentState.NOT_IN_COMMENT;
    // Fix our max line length to be 80. Anything more we flush and re-use.
    private static int lineOutIndex = 0;
    private static char [] lineOut = new char [80];
    private static boolean commentStart = false;
    private static boolean blockComment = false;
    private static String  singleChars  = "//";
    private static String  blockBeginChars = "/*";
    private static String  blockEndChars   = "*/";

    // Fliush any characters in the output buffer.
    static void flushOutputCharacters () {
        if (0 < lineOutIndex) {
            System.out.println(new String(lineOut, 0, lineOutIndex));
            lineOutIndex = 0;
        }
    }

    //
    // Wrapper to handle the output characters.
    // This will help to buffer the characters till we hit a new line or
    // buffer size to flush to conosle.
    // This helps us to use same buffer size irrespective of line input length.
    static void addOutputCharacter(char c)
    {
        // If we have room to add this character stash it.
        if (lineOutIndex < (lineOut.length - 1)) {
            lineOut[lineOutIndex++] = c;
        }
        // if we are full or the newly added character is a new line flush it
        if ((c == '\n') || (lineOutIndex == lineOut.length)) {
            flushOutputCharacters();
        }
    }

    // Returns the starting index in the string where the
    // single line comment is found.
    // -1 if not found.
    static public int findSingleComment (String line) {
        return line.indexOf(singleChars);
    }

    // Returns the starting index in the string where the
    // multi line comment start is found.
    // -1 if not found.
    static public int findBlockCommentStart (String line) {
        return line.indexOf(blockBeginChars);
    }


    // Returns the starting index in the string where the
    // multi line comment end is found.
    // -1 if not found.
    static public int findBlockCommentEnd(String line) {
        return line.indexOf(blockEndChars);
    }

    //
    // Treat the line to have many possible states
    // since block comments can start and complete many times.
    // if we find a single comment out of block comment segment, we skip the rest of the line.
    // It
    static void processCommentBytes (String line, boolean outputEnabled) {

        int length = line.length();
        int i      = 0;
        int j      = 0;

        // till we have some characters
        while (i < length) {
            switch (commentState) {
                case NOT_IN_COMMENT:
                    if (line.charAt(i) == '/')
                        commentState = CommentState.MAYBE_COMMENT_START;
                    else
                        addOutputCharacter(line.charAt(i));
                    break;

                case MAYBE_COMMENT_START:
                    if (line.charAt(i) == '/')
                        commentState = CommentState.IN_SINGLE_LINE_COMMENT;
                    else if (line.charAt(i) == '*')
                        commentState = CommentState.IN_MULTI_LINE_COMMENT;
                    else {
                        addOutputCharacter(line.charAt(i - 1));
                        addOutputCharacter(line.charAt(i));
                        commentState = CommentState.NOT_IN_COMMENT;
                    }
                    break;

                case IN_SINGLE_LINE_COMMENT:
                    if (line.charAt(i) == '\n') {
                        addOutputCharacter(line.charAt(i));
                        commentState = CommentState.NOT_IN_COMMENT;
                    }
                    break;

                case IN_MULTI_LINE_COMMENT:
                    if (line.charAt(i) == '*')
                        commentState = CommentState.MAYBE_COMMENT_END;
                    break;

                case MAYBE_COMMENT_END:
                    if (line.charAt(i) == '/')
                        commentState = CommentState.NOT_IN_COMMENT;
                    else
                        commentState = CommentState.IN_MULTI_LINE_COMMENT;
                    break;
            }
            // Always move the character to next one
            i++;
        }
        // End of the line means implicit New line.
        // Flush any pending line characters.
        // If in single comment state, reset.
        if (CommentState.IN_SINGLE_LINE_COMMENT == commentState)
            commentState = CommentState.NOT_IN_COMMENT;
        flushOutputCharacters();
    }

    //
    // Treat the line to have many possible states
    // since block comments can start and complete many times.
    // if we find a single comment out of block comment segment, we skip the rest of the line.
    // It
    static void processCommentLines (String line, boolean outputEnabled) {
        int length = line.length();
        int i = 0;
        // till we have some characters
        while (i < length) {
            String testStr3= /*TEST: yes*/"\" \' \" // /*/* \\ */*/";//TEST: very uncool
            String remLine = (String) line.substring(i);
            int slcIndex        = findSingleComment(remLine);
            int mlcStartIndex   = findBlockCommentStart(remLine);
            int mlcEndIndex     = findBlockCommentEnd(remLine);
            boolean anyCommentStart = (-1 != slcIndex) || (-1 != mlcStartIndex);

            // reset our string index for this iteration.
            i = 0;
            // Not inside a comment block only applies for multiline block comment types.
            if (!commentStart) {
                if (!anyCommentStart) {
                    System.out.println(remLine);
                    return;
                }

                // If we have a single line comment, print upto the start of the comment and
                // return.
                // Careful if block comment is not starting earlier than single comment.
                if (-1 != slcIndex) {
                    if ((slcIndex < mlcStartIndex) || (-1 == mlcStartIndex)) {
                        System.out.println(remLine.substring(i, slcIndex));
                        return;
                    }
                }

                // block comment start somewhere without a single line comment.
                // print upto start of the block comment
                System.out.println(remLine.substring(i, mlcStartIndex));
                commentStart = true;
                i = mlcStartIndex + blockBeginChars.length();
            }

            // Inside a block comment,
            // Ignore all till we see a end of the block comment.
            if (-1 != mlcEndIndex) {
                i = mlcEndIndex + blockEndChars.length();
                commentStart = false;
            } else {
                // we are done in this line
                return;
            }
        }
    }

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
            processCommentBytes(input, true);
//            processCommentLines(input, true);
            /*
            if (false == commentStart) {
                detectCommentStart(input, true);
            } else {
                detectCommentEnd(input, true);
            }
            */
        }
    }
}
