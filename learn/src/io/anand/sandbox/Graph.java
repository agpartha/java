package io.anand.sandbox;

// Graph class uses a concept of vertices and stores the connected node Ids in each node as a set (no duplicates)
// The vertices themselves

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class Graph<I, D> {

    static private class Vertex <I, D> {

        private I id;
        private D data;
        private HashSet<I> edges = new HashSet<>();

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

    private Map<I, Vertex> vertices;

    public Graph() {
        vertices = new HashMap<I, Vertex>();
    }

    public Vertex addVertex(I id, Vertex node) {
        return vertices.put(id, node);
    }

    public Vertex getVertex(I id) {
        return vertices.get(id);
    }

    public void printVertices () {
        for (I id : vertices.keySet()) {
            System.out.println(vertices.get(id));
        }
    }

    public void printVerticesEdges () {
        for (I id : vertices.keySet()) {
            StringBuilder sb = new StringBuilder();
            Vertex vertex = vertices.get(id);
            sb.append("Vertex: { id: " + id + ", data: " + vertex.getData() + ", edges: [");
            HashSet<I> edges = vertex.getEdges();
            for (I edgeId : edges) {
                Vertex edgeVertex = vertices.get(edgeId);
                sb.append(" " + edgeVertex.getData());
            }
            sb.append(" ] }");
            System.out.println(sb.toString());
        }
    }

    HashSet<I> visitedSet = new HashSet<>();
    public void doDFS (I id) {
        Vertex<I, D>    vertex = vertices.get(id);
        System.out.println("Vertex: id: " + vertex.getId() + ", data: " + vertex.getData());
        visitedSet.add(id);
        for (I edgeId: vertex.getEdges()) {
            if (!visitedSet.contains(edgeId))
                doDFS(edgeId);
        }

    }

    public static void main (String[] args) {
        Graph<Integer, Vertex<Integer, String>>  cities = new Graph<>();

        System.out.println("Adding Cities");
        // Add a few cities and print them.
        Vertex <Integer, String> vertex = new Vertex(1, "San Jose");
        cities.addVertex(vertex.getId(), vertex);
        vertex = new Vertex(4, "San Francisco");
        cities.addVertex(vertex.getId(), vertex);
        vertex = new Vertex(3, "Dublin");
        cities.addVertex(vertex.getId(), vertex);
        vertex = new Vertex(2, "Atherton");
        cities.addVertex(vertex.getId(), vertex);
        vertex = new Vertex(5, "Pleasanton");
        cities.addVertex(vertex.getId(), vertex);
        cities.printVertices();
        cities.printVerticesEdges();
        cities.doDFS(1);


        // Add some connections
        System.out.println("Adding Routes");
        cities.getVertex(1).addEdge(2);
        cities.getVertex(2).addEdge(1);
        cities.getVertex(1).addEdge(3);
        cities.getVertex(3).addEdge(4);
        cities.getVertex(4).addEdge(3);
        cities.getVertex(4).addEdge(5);
        cities.getVertex(5).addEdge(4);
        cities.getVertex(5).addEdge(2);
        cities.printVertices();
        cities.printVerticesEdges();
        cities.doDFS(1);


        // Remove some connections
        System.out.println("Removing Routes");
        cities.getVertex(5).delEdge(2);
        cities.getVertex(2).delEdge(1);
        cities.printVertices();
        cities.printVerticesEdges();
        cities.doDFS(1);
    }
}
