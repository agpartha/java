package io.anand.play;

import java.util.Collections;
import java.util.List;

public class Node {
    // Define the Node
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<Node> getChildren() {
            return children;
        }

        public void setChildren(List<Node> children) {
            this.children = children;
        }

        public Node(int id, List<Node> children) {
            this.id = id;
            this.children = children;
        }

        public Node (int id) {
            this.id = id;
        }

        private     int     id;
        List<Node>          children = Collections.emptyList();
    }