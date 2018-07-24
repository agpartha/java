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
            System.out.println("Processing node: " + node.getId());
            // Loop over the children of this node
            for (Node child : node.children) {
                // if child is in the lookup table
                // we must have added this as another traversal.
                if (lookupTable.contains(node.getId())) {
                    System.out.println("Detected a loop between node: " + node.getId() + ", and : " + child.getId());
                    return false;
                }
                // Add the child and also keep it in the lookup table
                queue.add(child);
            }
            // Safe to mark this parent node as done. Should not be seen as a child of anyone or this node [self loop)
            lookupTable.add(node.getId());
        }
        return true;
    }



    public static void main (String args []) {
        System.out.println("Null check: loop free: " + isLoopFree(null));
        Node root = new Node (1, Collections.emptyList());
        System.out.println("Null check: loop free: " + isLoopFree(root));
        List<Node> children = new LinkedList<>();
        children.add(new Node(2));
        children.add(new Node(3));
//        children.add(root);
        root.setChildren(children);
        System.out.println("Null check: loop free: " + isLoopFree(root));

    }
}
