package io.anand.play;

// Graph class uses a concept of vertices and stores the connected node Ids in each node as a set (no duplicates)
// The vertices themselves

import java.util.*;
import java.util.LinkedList;

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

    public void doDFS (I id, HashSet<I> visitedSet) {
        Vertex<I, D>    vertex = vertices.get(id);
        System.out.println("Vertex: id: " + vertex.getId() + ", data: " + vertex.getData());
        visitedSet.add(id);
        for (I edgeId: vertex.getEdges()) {
            if (!visitedSet.contains(edgeId))
                doDFS(edgeId, visitedSet);
        }
    }

    public void doDFSTraversal(I sourceId) {
        System.out.println("DFS starting from: " + sourceId);
        HashSet<I> visitedSet = new HashSet<>();
        doDFS(sourceId, visitedSet);
    }

    public void doDFSTraversal () {
        doDFSTraversal(vertices.keySet().iterator().next());
    }

    public void doBFS (I sourceId) {
        Queue<I> vertexList     = new LinkedList<I>();
        HashSet<I> visitedSet   = new HashSet<>();

        vertexList.add(sourceId);
        visitedSet.add(sourceId);
        while (!vertexList.isEmpty()) {
            I id                   = vertexList.poll();
            Vertex<I, D>    vertex = vertices.get(id);
            System.out.println("Vertex: id: " + vertex.getId() + ", data: " + vertex.getData());
            // Though it appears right to mark as visited only here,
            // it helps tp avoid the node appearing twice if the same child appears as a child of any other
            // node. In this case next level node is reachable by two of the current children nodes.
            //visitedSet.add(id);

            // Add all the children who are not yet visited to the queue so that they
            // are visited after our current list of vertices are handled.
            for (I edgeId: vertex.getEdges())
                if (!visitedSet.contains(edgeId)) {
                    vertexList.add(edgeId);
                    // Though it appears not right to mark as visited yet since we have not really seen it,
                    // it helps tp avoid the node appearing twice if the same child appears as a child of any other
                    // node. In this case next level node is reachable by two of the current children nodes.
                    visitedSet.add(edgeId);
                }
        }
    }

    public void doBFSTraversal(I sourceId) {
        System.out.println("BFS starting from: " + sourceId);
        doBFS(sourceId);
    }

    public void doBFSTraversal () {
        doBFSTraversal(vertices.keySet().iterator().next());
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
        cities.doDFSTraversal(1);
        cities.doBFSTraversal(1);


        // Add some connections
        System.out.println("Adding Routes");
        cities.getVertex(1).addEdge(5);
        cities.getVertex(1).addEdge(3);
        cities.getVertex(1).addEdge(2);
        cities.getVertex(2).addEdge(3);
        cities.getVertex(3).addEdge(4);
        cities.getVertex(4).addEdge(5);
        cities.getVertex(4).addEdge(2);
        cities.getVertex(5).addEdge(1);
        cities.getVertex(5).addEdge(2);
        cities.printVertices();
        cities.printVerticesEdges();
        cities.doDFSTraversal(2);
        cities.doBFSTraversal(2);


        // Remove some connections
        System.out.println("Removing Routes");
        cities.getVertex(1).delEdge(3);
        cities.getVertex(5).delEdge(2);
        cities.getVertex(4).delEdge(5);
        cities.printVertices();
        cities.printVerticesEdges();
        cities.doDFSTraversal();
        cities.doBFSTraversal();
    }
}
