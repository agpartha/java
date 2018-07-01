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


    public void reverse () {
        if (null == head)
            return;

        LinkedListNode <T> prev, cur, temp;

        prev = null;
        cur  = head;
        // A -> B -> C -> D -> E -> Null
        // 1. temp = B, cur (A) -> prev (Null), prev =  cur (A), cur = temp (B)
        // 2. temp = C, cur (B) -> prev (A), prev =  cur (0), cur = temp (C)
        // Head == E
        while (null != cur) {
            temp        = cur.next;
            cur.next    = prev;
            prev        = cur;
            cur         = temp;
        }
        head = prev;
    }

    public void printList (String header) {
        LinkedListNode <T> cur = head;
        System.out.format("\n%s:\n", header);
        while (null != cur) {
            System.out.println(cur.print());
            cur = cur.next;
        }
     }

    public void print () {

        this.printList("List items");
        this.reverse();
        this.printList("Reversed List items");
        this.reverse();
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
