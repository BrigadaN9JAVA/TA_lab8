package Interfaces;

public interface Node<K extends Comparable<K>, T> {
    T getData();
    K getKey();
    void display();
}
