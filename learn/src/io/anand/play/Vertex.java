package io.anand.play;

import java.util.HashSet;

public class Vertex <I, D> {

    private I               id;
    private D               data;
    private HashSet<I>      edges = new HashSet<>();

    public I getId() {
        return id;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public HashSet<I> getEdges() {
        return edges;
    }

    public boolean addEdge(I id) {
        return edges.add(id);
    }

    public boolean delEdge(I id) {
        return edges.remove(id);
    }

    public Vertex(I id) {
        this.id = id;
    }

    public Vertex(I id, D data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                ", data=" + data +
                ", edges=" + edges +
                '}';
    }
}
