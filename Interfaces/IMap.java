package Interfaces;

public interface IMap<K extends Comparable<K>, T> {
    void insert(K key, T data);
    void remove(K key);
    Node<K, T> find(K key);

}
