package io.anand.play;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class LoopFreeGraph {

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
                    System.out.println("Detected a loop between node: " + node.getId() + " and : " + child.getId());
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
        Node root, l1, m1, r1, l2l1, l2m1, r2m1, l2r1, m2r1, r2r1;

        System.out.println("Null check: loop free: " + isLoopFree(null));

        root = new Node (1);

        System.out.println("Single Root check: loop free: " + isLoopFree(root));

        l1   = new Node (2);
        m1   = new Node (3);
        r1   = new Node (4);
        l2l1 = new Node (5);
        l2m1 = new Node (6);
        r2m1 = new Node (7);
        l2r1 = new Node (8);
        m2r1 = new Node (10);
        r2r1 = new Node (11);

        root.addChild(l1);
        root.addChild(m1);
        root.addChild(r1);

        l1.addChild(l2l1);

        m1.addChild(l2m1);
        m1.addChild(r2m1);

        r1.addChild(l2r1);
        r1.addChild(m2r1);
        r1.addChild(r2r1);

        // Loops
        l2l1.addChild(r1);

        System.out.println("Multi Level check: loop free: " + isLoopFree(root));
    }
}
