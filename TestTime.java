import Collections.HashTable;
import Collections.OneLinkedList;
import Collections.RedBlackTree;
import Collections.TimeTest;

public class TestTime {
    public static void main(String[] args) {
        TimeTest<Long, Values, HashTable<Long, Values, OneLinkedList<Long, Values>>> tmOneLinkedList = new TimeTest<>(new HashTable<Long, Values, OneLinkedList<Long, Values>>(1));
        TimeTest<Long, Values, HashTable<Long, Values, RedBlackTree<Long, Values>>> tmRBT = new TimeTest<>(new HashTable<Long, Values, RedBlackTree<Long, Values>>(2));

        Values val = new Values();
        val.name = "Vlad";
        val.description = "Сильный";
        val.value = "Не смотрите этот код";
        for(int i = 0; i < 1000; i++){
            val.id = (long)(Math.random()*1000000) + 2000;
            tmOneLinkedList.getMap().insert(val.id, val);
            tmRBT.getMap().insert(val.id, val);
        }
        long resultLinked = 0;
        long resultRBT = 0;
        for (long i = 0; i < 500; i++) {
            val.id = i;
            resultLinked += tmOneLinkedList.insertTime(val.id, val);
            resultRBT += tmRBT.insertTime(val.id, val);
        }
        System.out.println("ListRows(insert) 1000 elements => " + resultLinked/500 + " ns");
        System.out.println("RBTRows(insert) 1000 elements=> " + resultRBT/500 + " ns");


        resultLinked = 0;
        resultRBT = 0;
        for (long i = 0; i < 500; i++) {
            val.id = i;
            resultLinked += tmOneLinkedList.findTime(val.id);
            resultRBT += tmRBT.findTime(val.id);
        }
        System.out.println("ListRows(find) 1000 elements => " + resultLinked/500 + " ns");
        System.out.println("RBTRows(find) 1000 elements => " + resultRBT/500 + " ns");
        System.out.println("=================================================");

        for(int i = 0; i < 9000; i++){
            val.id = (long)(Math.random()*1000000) + 2000;
            tmOneLinkedList.getMap().insert(val.id, val);
            tmRBT.getMap().insert(val.id, val);
        }

        resultLinked = 0;
        resultRBT = 0;
        for (long i = 500; i < 1000; i++) {
            val.id = i;
            resultLinked += tmOneLinkedList.insertTime(val.id, val);
            resultRBT += tmRBT.insertTime(val.id, val);
        }
        System.out.println("ListRows(insert) 10000 elements => " + resultLinked/500 + " ns");
        System.out.println("RBTRows(insert) 10000 elements=> " + resultRBT/500 + " ns");

        resultLinked = 0;
        resultRBT = 0;
        for (long i = 500; i < 1000; i++) {
            val.id = i;
            resultLinked += tmOneLinkedList.findTime(val.id);
            resultRBT += tmRBT.findTime(val.id);
        }
        System.out.println("ListRows(find) 10000 elements => " + resultLinked/500 + " ns");
        System.out.println("RBTRows(find) 10000 elements => " + resultRBT/500 + " ns");
        System.out.println("=================================================");

        for(int i = 0; i < 90000; i++){
            val.id = (long)(Math.random()*1000000) + 2000;
            tmOneLinkedList.getMap().insert(val.id, val);
            tmRBT.getMap().insert(val.id, val);
        }

        resultLinked = 0;
        resultRBT = 0;
        for (long i = 1000; i < 1500; i++) {
            val.id = i;
            resultLinked += tmOneLinkedList.insertTime(val.id, val);
            resultRBT += tmRBT.insertTime(val.id, val);
        }
        System.out.println("ListRows(insert) 100000 elements => " + resultLinked/500 + " ns");
        System.out.println("RBTRows(insert) 100000 elements=> " + resultRBT/500 + " ns");

        resultLinked = 0;
        resultRBT = 0;
        for (long i = 1000; i < 1500; i++) {
            val.id = i;
            resultLinked += tmOneLinkedList.findTime(val.id);
            resultRBT += tmRBT.findTime(val.id);
        }
        System.out.println("ListRows(find) 100000 elements => " + resultLinked/500 + " ns");
        System.out.println("RBTRows(find) 100000 elements => " + resultRBT/500 + " ns");
        System.out.println("=================================================");

        for(int i = 0; i < 520000; i++){
            val.id = (long)(Math.random()*1000000) + 2000;
            tmOneLinkedList.getMap().insert(val.id, val);
            tmRBT.getMap().insert(val.id, val);
        }

        resultLinked = 0;
        resultRBT = 0;
        for (long i = 1500; i < 2000; i++) {
            val.id = i;
            resultLinked += tmOneLinkedList.insertTime(val.id, val);
            resultRBT += tmRBT.insertTime(val.id, val);
        }
        System.out.println("ListRows(insert) 300000 elements => " + resultLinked/500 + " ns");
        System.out.println("RBTRows(insert) 300000 elements=> " + resultRBT/500 + " ns");

        resultLinked = 0;
        resultRBT = 0;
        for (long i = 1500; i < 2000; i++) {
            val.id = i;
            resultLinked += tmOneLinkedList.findTime(val.id);
            resultRBT += tmRBT.findTime(val.id);
        }
        System.out.println("ListRows(find) 300000 elements => " + resultLinked/500 + " ns");
        System.out.println("RBTRows(find) 300000 elements => " + resultRBT/500 + " ns");
        System.out.println("=================================================");
        System.out.println();
        System.out.println();
        System.out.println();


        TimeTest<Long, Values, RedBlackTree<Long, Values>> tmTree = new TimeTest<>(new RedBlackTree<Long, Values>());

        for(int i = 0; i < 1000; i++){
            val.id = (long)(Math.random()*1000000) + 2000;
            tmTree.getMap().insert(val.id, val);
        }

        long resultTree = 0;
        for (long i = 0; i < 500; i++) {
            val.id = i;
            resultTree += tmTree.insertTime(val.id, val);
        }
        System.out.println("Tree(insert) 1000 elements => " + resultTree/500 + " ns");

        resultTree = 0;
        for (long i = 0; i < 500; i++) {
            val.id = i;
            resultTree += tmTree.findTime(val.id);
        }

        System.out.println("Tree(find) 1000 elements => " + resultTree/500 + " ns");
        System.out.println("=================================================");

        for(int i = 0; i < 9000; i++){
            val.id = (long)(Math.random()*1000000) + 2000;
            tmTree.getMap().insert(val.id, val);
        }

        resultTree = 0;
        for (long i = 500; i < 1000; i++) {
            val.id = i;
            resultTree += tmTree.insertTime(val.id, val);
        }
        System.out.println("Tree(insert) 10000 elements => " + resultTree/500 + " ns");

        resultTree = 0;
        for (long i = 500; i < 1000; i++) {
            val.id = i;
            resultTree += tmTree.findTime(val.id);
        }

        System.out.println("Tree(find) 10000 elements => " + resultTree/500 + " ns");
        System.out.println("=================================================");

        for(int i = 0; i < 90000; i++){
            val.id = (long)(Math.random()*1000000) + 2000;
            tmTree.getMap().insert(val.id, val);
        }

        resultTree = 0;
        for (long i = 1000; i < 1500; i++) {
            val.id = i;
            resultTree += tmTree.insertTime(val.id, val);
        }
        System.out.println("Tree(insert) 100000 elements => " + resultTree/500 + " ns");

        resultTree = 0;
        for (long i = 1000; i < 1500; i++) {
            val.id = i;
            resultTree += tmTree.findTime(val.id);
        }

        System.out.println("Tree(find) 100000 elements => " + resultTree/500 + " ns");
        System.out.println("=================================================");

        for(int i = 0; i < 520000; i++){
            val.id = (long)(Math.random()*1000000) + 2000;
            tmTree.getMap().insert(val.id, val);
        }

        resultTree = 0;
        for (long i = 1500; i < 2000; i++) {
            val.id = i;
            resultTree += tmTree.insertTime(val.id, val);
        }
        System.out.println("Tree(insert) 300000 elements => " + resultTree/500 + " ns");

        resultTree = 0;
        for (long i = 1500; i < 2000; i++) {
            val.id = i;
            resultTree += tmTree.findTime(val.id);
        }

        System.out.println("Tree(find) 300000 elements => " + resultTree/500 + " ns");
        System.out.println("=================================================");
    }
}
