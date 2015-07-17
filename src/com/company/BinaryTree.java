package com.company;

import java.util.Iterator;

public class BinaryTree<T extends Comparable<T>> implements Iterable<T> {
    private Node root = null;

    public void toList() {
        if (root == null) {
            return;
        }
        Node minNode = findMin();
        convertToList(root);
        root = minNode;
    }

    private void convertToList(Node node) {
        if (node.left != null) {
            Node left = findPreviousSorted(node);
            convertToList(node.left);
            left.right = node;
            node.left = left;
        }

        if (node.right != null) {
            Node right = findNextSorted(node);
            convertToList(node.right);
            right.left = node;
            node.right = right;
        }
    }

    private Node findNext(Node node, Function<Node, Node> nextNode, Function<Node, Node> previousNode) {
        Node nextHighest;

        Node next = nextNode.get(node);

        if (next == null) {
            return null;
        } else {
            nextHighest = next;
        }

        while (previousNode.get(nextHighest) != null) {
            nextHighest = previousNode.get(nextHighest);
        }
        return nextHighest;
    }

    private Node findPreviousSorted(Node node) {
        return findNext(node, LEFT, RIGHT);
    }

    private Node findNextSorted(Node node) {
        return findNext(node, RIGHT, LEFT);
    }

    private Node findMin() {
        if (root == null) {
            return null;
        }
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public void add(T value) {
        if (root == null) {
            root = new Node(value, null, null);
        } else {
            insertNode(value, root);
        }
    }

    private void insertNode(T value, Node node) {
        int compareValue = value.compareTo(node.value);
        if (compareValue > 0) {
            if (node.right == null) {
                node.right = new Node(value, null, null);
            } else {
                insertNode(value, node.right);
            }
        } else if (compareValue < 0) {
            if (node.left == null) {
                node.left = new Node(value, null, null);
            } else {
                insertNode(value, node.left);
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator(root);
    }

    private class ListIterator implements Iterator<T> {
        private Node currentNode;

        public ListIterator(Node node) {
            currentNode = node;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            T returnValue = currentNode.value;
            currentNode = currentNode.right;
            return returnValue;
        }

        @Override
        public void remove() {
            throw new RuntimeException("Operation not supported");
        }
    }

    interface Function<F, T> {
        T get(F value);
    }

    private final Function<Node, Node> LEFT = new Function<Node, Node>() {
        @Override
        public Node get(Node value) {
            return value.left;
        }
    };

    private final Function<Node, Node> RIGHT = new Function<Node, Node>() {
        @Override
        public Node get(Node value) {
            return value.right;
        }
    };

    private class Node {
        Node left;
        Node right;
        T value;

        Node(T value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

}
