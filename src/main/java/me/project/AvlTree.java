package me.project;

import java.util.Objects;

public class AvlTree<T extends Comparable<T>> {

    private Node<T> root;

    public void delete(T data) {
        root = delete(data, root);
    }

    private Node<T> delete(T data, Node<T> node) {
        if(Objects.isNull(node)) {
            return new Node<>(data);
        }

        if(data.compareTo(node.getData()) < 0) {
            node.setLeft(delete(data, node.getLeft()));
        } else if(data.compareTo(node.getData()) > 0) {
            node.setRight(delete(data, node.getRight()));
        } else {
            //one child
            if(Objects.isNull(node.getLeft())) {
                return node.getRight();
            } else if(Objects.isNull(node.getRight())) {
                return node.getLeft();
            }

            //two children
            node.setData(maxRec(node.getLeft()));
            node.setLeft(delete(node.getData(), node.getLeft()));
        }

        updateHeight(node);
        return rotation(node);
    }

    public void insert(T data) {
        root = insert(data, root);
    }

    private Node<T> insert(T data, Node<T> node) {
        if(Objects.isNull(node)) {
            return new Node<>(data);
        }

        if(data.compareTo(node.getData()) < 0) {
            node.setLeft(insert(data, node.getLeft()));
        } else if(data.compareTo(node.getData()) > 0) {
            node.setRight(insert(data, node.getRight()));
        } else {
            return node;
        }

        updateHeight(node);
        return rotation(node);
    }

    private void updateHeight(Node<T> node) {
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
    }

    private int height(Node<T> node) {
        return Objects.isNull(node) ? 0 : node.getHeight();
    }

    private Node<T> rotation(Node<T> node) {
        int balance = balance(node);
        if(balance > 1) {
            if(balance(node.getLeft()) < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }

            return rotateRight(node);
        }

        if(balance < -1) {
            if(balance(node.getRight()) > 0) {
                node.setRight(rotateRight(node.getRight()));
            }

            return rotateLeft(node);
        }

        return node;
    }

    private int balance(Node<T> node) {
        return Objects.isNull(node) ? 0 : height(node.getLeft()) - height(node.getRight());
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> left = node.getLeft();
        Node<T> center = left.getRight();
        left.setRight(node);
        node.setLeft(center);
        updateHeight(node);
        updateHeight(left);
        return left;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> right = node.getRight();
        Node<T> center = right.getLeft();
        right.setLeft(node);
        node.setRight(center);
        updateHeight(node);
        updateHeight(right);
        return right;
    }

    /**
     * 전위 순회
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node<T> node) {
        if(node != null) {
            System.out.println(node.getData());
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    /**
     * 중위 순회
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node<T> node) {
        if(node != null) {
            inOrder(node.getLeft());
            System.out.println(node.getData());
            inOrder(node.getRight());
        }
    }

    /**
     * 후위 순회
     */
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node<T> node) {
        if(node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.println(node.getData());
        }
    }

    public boolean isEmpty() {
        return Objects.isNull(root);
    }

    public T min() {
        if(isEmpty()) {
            return null;
        }

        Node<T> curr = root;
        while (curr.getLeft() != null) {
            curr = curr.getLeft();
        }

        return curr.getData();
    }

    public T max() {
        if(isEmpty()) {
            return null;
        }

        Node<T> curr = root;
        while (curr.getRight() != null) {
            curr = curr.getRight();
        }

        return curr.getData();
    }

    public T minRec() {
        if(isEmpty()) {
            return null;
        }

        return minRec(root);
    }

    public T maxRec() {
        if(isEmpty()) {
            return null;
        }

        return maxRec(root);
    }

    private T minRec(Node<T> node) {
        if(node.getLeft() != null) {
            return minRec(node.getLeft());
        }

        return node.getData();
    }

    private T maxRec(Node<T> node) {
        if(node.getRight() != null) {
            return maxRec(node.getRight());
        }

        return node.getData();
    }
}
