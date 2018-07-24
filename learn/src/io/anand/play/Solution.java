package io.anand.play;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class Solution {

    // Returns if the Tree from the root node has any loops
    public static boolean isLoopFree (Node root) {

        // Parameter checks
        if (null == root) {
            return true;
        }

        HashSet<Integer> lookupTable = new HashSet<>();
        Queue<Node> queue            = new LinkedBlockingDeque<>();

        // Add the root to the queue
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            // Loop over the children of this node
            for (Node child : root.children) {
                // if child is in the lookup table
                // we must have added this as another traversal.
                if (lookupTable.contains(child.getId())) {
                    System.out.println("Detected a loop between node: " + node.getId() + ", and node: " + child.getId());
                    return false;
                }
                // Add the child and also keep it in the lookup table
                queue.add(child);
                lookupTable.add(child.getId());
            }
        }
        return true;
    }



    public static void main (String args []) {
        System.out.println("Null check: loop free: " + isLoopFree(null));
        Node root = new Node (1, Collections.emptyList());
        System.out.println("Null check: loop free: " + isLoopFree(root));
        List<Node> children = new LinkedList<>();
        children.add(root);
        root.setChildren(children);
        System.out.println("Null check: loop free: " + isLoopFree(root));

    }
}
