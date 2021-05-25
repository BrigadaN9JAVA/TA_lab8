import Collections.HashTable;
import Collections.OneLinkedList;
import Interfaces.Node;

public class Test {

    public static void main(String[] args) {
        new OneLinkedList<>();
        HashTable<Long, Values, OneLinkedList<Long, Values>> hs = new HashTable<Long, Values, OneLinkedList<Long, Values>>(2);
        Values val = new Values();
        val.id = 1;
        val.name = "Vlad";
        val.description = "Поставте 5";
        val.value = "Сильный";
        hs.insert(val.id, val);
        for (int i = 2; i <= 20; i++) {
            val = new Values();
            val.id = i;
            val.name = "Vlad";
            val.description = "Поставте 5";
            val.value = "Сильный";
            hs.insert(val.id, val);
        }
        System.out.println();
        hs.remove((long) 15);
        System.out.println();
        Node<Long, Values> node = hs.find((long)18);
        System.out.println();
    }
}

