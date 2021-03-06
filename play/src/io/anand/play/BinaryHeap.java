// Simple Min-Heap implementation using Array Lists for Integers.
package io.anand.play;

import java.util.ArrayList;

// Implement a Min-Heap
class BinaryHeap {
    
    private int                 count  = 0;
    private ArrayList<Integer>  harray = new ArrayList<Integer> ();
    
    private boolean insert (Integer val) {
        harray.add(count++, val);
        heapifyUp(count - 1);
        return true;
    }
    
    private boolean remove (Integer val) {
        System.out.println("Not supported for heap, use delMin to remove the min value");
        return true;
    }
    
    private Integer getMin () {
       return harray.get(0); 
    }
    
    private Integer delMin () {
        // Copy the last value to the root and remove the last item.
        int min = harray.get(0);
        harray.set(0, harray.get(count - 1));
        harray.remove(count - 1);
        // Reduce our count and heapify from the root
        count--;
        heapifyDn(0);
        return min;
    }
    
    private int parent (int index) {
        // Loose defense, if we do not want callers to check return value, callers must pass 
        // an index in range for the heap.
        int parent = (index - 1) / 2;
        if ((index < 0) || (parent  >= count))
            return -1;
        return parent;
    }
    
    private int leftChild (int index) {
        // Loose defense, if we do not want callers to check return value, callers must pass 
        // an index in range for the heap.
        int left = (2 * index + 1);
        if ((index < 0) || (left >= count)) 
            return -1;
        return left;   
    }
    
    private int rightChild (int index) {
        // Loose defense, if we do not want callers to check return value, callers must pass 
        // an index in range for the heap.
        int right = (2 * index + 2);
        if ((index < 0) || (right >= count)) 
            return -1;
        return right;
    }

    private void heapifyUp (int index) {
        int cur, parent, min;
        
        cur     = index;
        parent  = parent(cur);

        System.out.format("heapifyUp: cur: %d, parent: %d, heap: %s\n", cur, parent, harray.toString());
        // Assume minimum is our current node, validate if we are higher than our parent, if so set to swap to it
        min = cur;
        if ((-1 != parent) && harray.get(parent) > harray.get(cur))
            min = parent;
        // Okay current value should get swapped with minimum we found
        if (min != cur) {
            int temp = harray.get(cur);
            harray.set(cur,  harray.get(min));
            harray.set(min,  temp);
        } else {
            // Exit if we had no swap
            System.out.println("heapifyUp: Done: " + harray.toString());
            return;
        }
        // Ensure rest of the tree is heapifyed starting at the now fixed up min node
        heapifyUp(min);
    }
    
    private void heapifyDn (int index) {
        int cur, left, right, min;
        
        cur     = index;
        left    = leftChild(cur);
        right   = rightChild(cur);
        
        System.out.format("heapifyDn: cur: %d, left: %d, right: %d, heap: %s\n", 
                cur, left, right, harray.toString());
        
        // Assume minimum is our current node, validate if left one is min, then set to swap to it
        min = cur;
        if ((-1 != left) && harray.get(left) < harray.get(cur))
            min = left;
        // We must still check right child for it being real minimum, so compare the minimum we have 
        // with the right. 
        if ((-1 != right) && harray.get(right) < harray.get(min))
            min = right;
        
        // Okay current value should get swapped with minimum we found
        if (min != cur) {
            int temp = harray.get(cur);
            harray.set(cur, harray.get(min));
            harray.set(min,  temp);
        } else {
            // Exit if we had no swap
            System.out.println("heapifyDn: Done: " + harray.toString());
            return;
        }
        
        // Ensure rest of the tree is heapifyed starting at the now fixed up min node
        heapifyDn(min);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BinaryHeap  h = new BinaryHeap ();
        
        h.insert(1);
        h.insert(-3);
        h.insert(4);
        h.insert(6);
        h.insert(-5);
        h.insert(200);
        System.out.println("Heap: " + h.harray.toString());
        System.out.println("Popping minimum value: " + h.delMin());
        System.out.println("Heap: " + h.harray.toString());
        System.out.println("Popping minimum value: " + h.delMin());
        System.out.println("Heap: " + h.harray.toString());
        System.out.println("Popping minimum value: " + h.delMin());
        System.out.println("Heap: " + h.harray.toString());
        System.out.println("Getting minimum value: " + h.getMin());
        System.out.println("Getting minimum value: " + h.getMin());
        h.insert(3);
        System.out.println("Getting minimum value: " + h.getMin());
        System.out.println("Popping minimum value: " + h.delMin());
        System.out.println("Popping minimum value: " + h.delMin());
        System.out.println("Popping minimum value: " + h.delMin());
        System.out.println("Popping minimum value: " + h.delMin());
    }
}
