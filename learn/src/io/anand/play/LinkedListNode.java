package io.anand.play;

public class LinkedListNode <T extends Comparable<T>> {

    public LinkedListNode <T> next;
    private T data;

    public LinkedListNode (T data) {
        this.data = data;
        this.next = null;
    }
    
    public int compareTo (T data2){
        return data.compareTo(data2);
    }
    public String print () {
        return data.toString();
    }

}
