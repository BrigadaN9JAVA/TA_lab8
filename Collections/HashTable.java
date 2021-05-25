package Collections;

import Interfaces.IMap;
import Interfaces.Node;
import Interfaces.Row;

import java.lang.reflect.Array;

public class HashTable<K extends Comparable<K>, T, R extends Row<K, T>> implements IMap<K, T> {


    private R[] rows;
    private int size;
    private int numBuckets;
    private final double loadFactor;
    private final Class<R> typeContainer;

    public HashTable(int choose){
        size = 0;
        numBuckets = 16;
        loadFactor = 0.75;
        if(choose == 1){
            this.typeContainer = (Class) OneLinkedList.class;
        }
        else{
            this.typeContainer = (Class) RedBlackTree.class;
        }
        rows = (R[]) Array.newInstance(typeContainer, numBuckets);
    }

    @Override
    public void insert(K key, T data){
        int hash = hashCode(key);
        if(rows[hash] == null){
            try {
                rows[hash] = typeContainer.getConstructor().newInstance();
                size++;
                rows[hash].insert(key, data);
            } catch (ReflectiveOperationException err){
                return;
            }
        }
        else{
            if(rows[hash].find(key) == null){
                if(rows[hash].isEmpty())
                    size++;
                rows[hash].insert(key, data);
            }
        }
        if(Double.compare(size/loadFactor, numBuckets) >= 0){
            numBuckets *= 2;
            R[] buff = rows;
            size = 0;
            rows = (R[]) Array.newInstance(typeContainer, numBuckets);
            for (var row : buff) {
                if(row != null) {
                    for (var item : row) {
                        insert(item.getKey(), item.getData());
                    }
                }
            }
        }
    }

    @Override
    public void remove(K key) {
        int hash = hashCode(key);
        if(rows[hash] == null){
            return;
        }
        rows[hash].remove(key);
        if(rows[hash].isEmpty())
            size--;
    }

    @Override
    public Node<K, T> find(K key) {
        int hash = hashCode(key);
        if(rows[hash] == null){
            return null;
        }
        return rows[hash].find(key);
    }

    private int hashCode(K key){
        return (int)Long.parseLong(key.toString()) % numBuckets;
    }
}
