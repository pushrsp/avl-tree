package me.project;

public class Node<T extends Comparable<T>> {
    private T data;
    private int height = 1;
    private Node<T> left;
    private Node<T> right;

    public Node(T data) {
        this.data = data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public int getHeight() {
        return height;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }
}
