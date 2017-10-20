package io.anand.learn;

public class LinkedListNode <DataType extends Comparable<DataType>> {

    public LinkedListNode <DataType> next;
    private DataType data;

    public LinkedListNode (DataType data) {
        this.data = data;
        this.next = null;
    }
    
    public int compareTo (DataType data2){
        return data.compareTo(data2);
    }
    public String print () {
        return data.toString();
    }

}
