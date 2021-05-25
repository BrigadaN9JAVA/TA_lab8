package Interfaces;

public interface TestTime<K extends Comparable<K>, T, D extends IMap<K, T>> {
    long insertTime(K key, T data);
    long removeTime(K key);
    long findTime(K key);
}
