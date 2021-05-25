package Interfaces;

public interface Row<K extends Comparable<K>, T> extends Iterable<Node<K, T>> {
    void insert(K key, T data);
    void remove(K key);
    Node<K, T> find(K key);
    boolean isEmpty();
}
