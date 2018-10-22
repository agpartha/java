package io.anand.learn;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RemoveCommentsState {

    // Line processing State Machine.
    private enum CommentState  {
            NOT_IN_COMMENT,
            MAYBE_COMMENT_START,
            IN_SINGLE_LINE_COMMENT,
            IN_MULTI_LINE_COMMENT,
            MAYBE_COMMENT_END
    }

    private static CommentState commentState = CommentState.NOT_IN_COMMENT;

    // Fix our max line length. Anything more we flush and re-use.
    private static int OUTPUT_BUFFER_SIZE   = 80;
    private static int lineOutIndex         = 0;
    private static char [] lineOut          = new char [OUTPUT_BUFFER_SIZE];

    // Flush any characters in the output buffer.
    static void flushOutputCharacters () {
        if (0 < lineOutIndex) {
            System.out.println(new String(lineOut, 0, lineOutIndex));
            lineOutIndex = 0;
        }
    }

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

    //
    // Treat the line to have many possible states
    // since block comments can start and complete many times.
    // if we find a single comment out of block comment segment, we skip the rest of the line.
    static void processCommentBytes (String line) {
        int length = line.length();
        int i      = 0;

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

    public static void main (String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while (null != (input = br.readLine()))
            processCommentBytes(input);
    }
}
