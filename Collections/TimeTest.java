package Collections;

import Interfaces.IMap;
import Interfaces.TestTime;

public class TimeTest<K extends Comparable<K>, T, D extends IMap<K, T>> implements TestTime<K, T, D> {
    IMap<K, T> map;
    public TimeTest(IMap<K, T> map){
        this.map = map;
    }
    @Override
    public long insertTime(K key, T data) {
        long end;
        long start = System.nanoTime();
        map.insert(key, data);
        end = System.nanoTime();
        return end - start;
    }

    @Override
    public long removeTime(K key) {
        long end;
        long start = System.nanoTime();
        map.remove(key);
        end = System.nanoTime();
        return end - start;
    }

    @Override
    public long findTime(K key) {
        long end;
        long start = System.nanoTime();
        map.find(key);
        end = System.nanoTime();
        return end - start;
    }

    public IMap<K, T> getMap() {
        return map;
    }
}
