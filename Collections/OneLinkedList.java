package Collections;

import Interfaces.Node;
import Interfaces.Row;

import java.util.Iterator;

public class OneLinkedList<K extends Comparable<K>, T> implements Row<K, T> {

    @Override
    public Iterator<Node<K, T>> iterator() {
        return new Itr();
    }

    public static class NodeOneLinked<K extends Comparable<K>, T> implements Node<K, T> {
        private NodeOneLinked<K, T> next;
        private K key;
        private T data;

        public NodeOneLinked() {
            this.next = null;
        }

        public NodeOneLinked<K, T> getNext() {
            return next;
        }

        public void setNext(NodeOneLinked<K, T> next) {
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        @Override
        public void display() {

        }
    }
    private NodeOneLinked<K, T> head;
    private NodeOneLinked<K, T> tail;
    private NodeOneLinked<K, T> iterator;
    private int size;

    public OneLinkedList(){
        size = 0;
        head = null;
        tail = null;
        iterator = null;
    }

    public NodeOneLinked<K, T> getIterator() {
        return iterator;
    }

    public void setIterator(NodeOneLinked<K, T> iterator) {
        this.iterator = iterator;
    }

    public NodeOneLinked<K, T> getHead() {
        return head;
    }

    public void setHead(NodeOneLinked<K, T> head) {
        this.head = head;
    }

    public NodeOneLinked<K, T> getTail() {
        return tail;
    }

    public void setTail(NodeOneLinked<K, T> tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isEmpty(){
        return getSize() == 0;
    }

    public void insert(K key, T data){
        if(isEmpty()){
            NodeOneLinked<K, T> Node = new NodeOneLinked<K, T>();
            Node.setData(data);
            Node.setKey(key);
            head = Node;
            tail = Node;
        }
        else{
            NodeOneLinked<K, T> Node = new NodeOneLinked<K, T>();
            Node.setData(data);
            Node.setKey(key);
            Node.setNext(head);
            head = Node;
        }
        size++;
    }

    public void addBack(T data, K key){
        if(isEmpty()){
            NodeOneLinked<K, T> Node = new NodeOneLinked<K, T>();
            Node.setData(data);
            Node.setKey(key);
            head = Node;
            tail = Node;
        }
        else{
            NodeOneLinked<K, T> Node = new NodeOneLinked<K, T>();
            Node.setData(data);
            Node.setKey(key);
            tail.setNext(Node);
            tail = Node;
        }
        size++;
    }

    /*public void insert1(K key, T data){
        if(isEmpty())
            //addFront(data, key);
        int position = getIndex(key)+1;
        if(position == 1){
            //addFront(data, key);
        }
        else if(position == getSize()+1){
            addBack(data, key);
        }
        else if(position > 1 && position <= getSize()){
            int count = 1;
            iterator = head;
            while(count != position-1){
                iterator = iterator.getNext();
                count++;
            }
            NodeOneLinked<K, T> Node = new NodeOneLinked<K, T>();
            Node.setData(data);
            Node.setKey(key);
            Node.setNext(iterator.getNext());
            iterator.setNext(Node);
            size++;
        }
        else{
            System.out.println("Position is incorrect. Input other number");
        }
    }*/

    public void removeFront(){
        if(isEmpty()){
            System.out.println("List is Empty. Cant remove the element");
        }
        else{
            head = head.getNext();
            size--;
        }
    }

    public void removeBack(){
        if(isEmpty()){
            System.out.println("List is Empty. Cant remove the element");
        }
        else{
            iterator = head;
            while (iterator.getNext() != tail){
                iterator = iterator.getNext();
            }
            iterator.setNext(null);
            tail = iterator;
            size--;
        }
    }

    public void remove(K key){
        int position = getIndex(key)+1;
        if(isEmpty()){
            System.out.println("List is Empty. Cant remove the element");
        }
        else if (position==1){
            removeFront();
        }
        else if(position == getSize()){
            removeBack();
        }
        else if(position > 1 && position < getSize()){
            int count = 1;
            iterator = head;
            while (count != position-1){
                iterator = iterator.getNext();
                count++;
            }
            iterator.setNext(iterator.getNext().getNext());
            size--;
        }
        else {
            System.out.println("Position is incorrect. Input other number");
        }
    }

    public int getIndex(K key){
        iterator = head;
        int count = 0;
        while(iterator != tail.getNext()){
            if(iterator.getKey().equals(key)){
                return count;
            }
            count++;
            iterator = iterator.getNext();
        }
        return -1; // Value is not found
    }

    public Node<K, T> find(K key){
        iterator = head;
        while(iterator != tail.getNext()){
            if(iterator.getKey().equals(key)){
                return iterator;
            }
            iterator = iterator.getNext();
        }
        return null; // Value is not found
    }

    public void display(){
        if(isEmpty()){
            System.out.println("List is empty");
        }
        else{
            iterator = head;
            while(iterator != tail.getNext()){
                System.out.print(iterator.getData() + " ");
                iterator = iterator.getNext();
            }
            System.out.println();
        }
    }

    public void clear(){
        size = 0;
        iterator = null;
        head = null;
        tail = null;
    }

    private class Itr implements Iterator<Node<K, T>>{
        NodeOneLinked<K, T> f = head;

        public Itr(){

        }


        @Override
        public boolean hasNext() {
            return f != null;
        }

        @Override
        public Node<K, T> next() {
            NodeOneLinked<K, T> buff = f;
            f = f.next;
            return buff;
        }
    }
}
