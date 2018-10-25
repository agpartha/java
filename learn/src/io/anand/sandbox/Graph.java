package io.anand.sandbox;

// Graph class uses a concept of vertices and stores the connected node Ids in each node as a set (no duplicates)
// The vertices themselves

import java.util.HashMap;
import java.util.Map;

public class Graph<I, V> {

    private Map<I, V> vertices;

    public Graph() {
        vertices = new HashMap<I, V>();
    }

    public V addVertex(I id, V node) {
        return vertices.put(id, node);
    }

    public V getVertex(I id) {
        return vertices.get(id);
    }

    public void printVertices () {
        for (I id : vertices.keySet()) {
            System.out.println(vertices.get(id));
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


        // Add some connections
        System.out.println("Adding Routes");
        cities.printVertices();

        // Remove some connections
        System.out.println("Removing Routes");
        cities.printVertices();

    }
}
