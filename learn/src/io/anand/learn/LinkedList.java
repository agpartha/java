package io.anand.learn;

public class LinkedList <T extends Comparable<T>>{
    public LinkedListNode head = null;
    
    public void insert (T data) {
        LinkedListNode <T> node = new LinkedListNode <T> (data);
        if (null == head) {
            head = node;
            return;
        }
        
        LinkedListNode <T> cur = head;
        while (null != cur) {
            if (null == cur.next) {
                cur.next = node;
                return;
            }
            cur = cur.next;                 
        }
    }
    
    public void remove (T data) {
        LinkedListNode <T> prev, cur;
 
        prev = null;
        cur  = head;
        while (null != cur) {
            if (0 == cur.compareTo(data)) {
                if (null != prev) 
                    prev.next = cur.next;
                else 
                    head = head.next;
                return;
            }
            prev = cur;
            cur  = cur.next;                 
        }
    }
    public void print () {
        LinkedListNode <T> cur = head;
        System.out.format("List items\n");
        while (null != cur) {
            System.out.println(cur.print());
            cur = cur.next;
        }
     }

    public static void main(String[] args) {
        LinkedList <String> ls = new LinkedList <> ();
        ls.insert("Hello");
        ls.insert("One");
        ls.insert("Two");

        ls.print();
        ls.remove("Two");
        ls.print();
        
        LinkedList <Integer> li = new LinkedList <> ();
        li.insert(1);
        li.insert(2);
        li.insert(3);
        li.remove(3);
        li.print();
        li.insert(5);
        li.print();
        li.remove(3);
        li.print();
        li.remove(1);
        li.print();
        li.remove(5);
        li.print();
        li.remove(2);
        li.print();
        System.out.format("LinkedList: Tests Completed");

    }

}