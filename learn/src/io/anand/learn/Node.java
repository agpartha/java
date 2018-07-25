package io.anand.learn;

import java.util.ArrayList;
import java.util.List;

public class Node {
    // Define the Node


        public void addChild (Node child) {
            this.children.add(child);
        }

        public void delChild (Node delChild) {
            for (Node child: this.children)
                if (child.equals(delChild)) {
                    this.children.remove(delChild);
                    break;
                }
        }

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
        List<Node>          children = new ArrayList<Node>();
    }