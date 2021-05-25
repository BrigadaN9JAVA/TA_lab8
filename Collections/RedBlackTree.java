package Collections;


import Interfaces.IMap;
import Interfaces.Node;
import Interfaces.Row;

import java.util.Iterator;
import java.util.Stack;

public class RedBlackTree<K extends Comparable<K>, T> implements IMap<K, T>, Row<K, T> {
    @Override
    public Iterator<Node<K, T>> iterator() {
        return new Itr();
    }

    static class RedBlackNode<K extends Comparable<K>, T> implements Node<K, T> {
        private K key;
        private T data;
        private RedBlackNode<K, T> parent;
        private RedBlackNode<K, T> left;
        private RedBlackNode<K, T> right;
        private int color;

        public RedBlackNode<K, T> getParent() {
            return parent;
        }

        public void setParent(RedBlackNode<K, T> parent) {
            this.parent = parent;
        }

        public RedBlackNode<K, T> getLeft() {
            return left;
        }

        public void setLeft(RedBlackNode<K, T> left) {
            this.left = left;
        }

        public RedBlackNode<K, T> getRight() {
            return right;
        }

        public void setRight(RedBlackNode<K, T> right) {
            this.right = right;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setKey(K key) {
            this.key = key;
        }

        @Override
        public void display() {
            System.out.println("> Node has value: " + data + "; key: " + key);
        }
    }
    private RedBlackNode<K, T> root;
    private RedBlackNode<K, T> TNULL;

    public RedBlackNode<K, T> getRoot() {
        return root;
    }

    public RedBlackTree(){
        TNULL = new RedBlackNode<K, T>();
        TNULL.setColor(0);
        TNULL.setLeft(null);
        TNULL.setRight(null);
        root = TNULL;
    }

    public void insert(K key, T data){
        RedBlackNode<K, T> newNode = new RedBlackNode<K, T>();
        newNode.setParent(null);
        newNode.setKey(key);
        newNode.setData(data);
        newNode.setLeft(TNULL);
        newNode.setRight(TNULL);
        newNode.setColor(1);

        RedBlackNode<K, T> currentNode= this.root;
        RedBlackNode<K, T> parentNode = null;

        while(currentNode != TNULL){
            parentNode = currentNode;
            if(newNode.key.compareTo(currentNode.key) < 0){
                currentNode = currentNode.getLeft();
            }
            else{
                currentNode = currentNode.getRight();
            }
        }

        newNode.setParent(parentNode);
        if(parentNode == null){
            root = newNode;
        }
        else if(newNode.getKey().compareTo(parentNode.getKey())<0){
            parentNode.setLeft(newNode);
        }
        else{
            parentNode.setRight(newNode);
        }

        if(newNode.getParent() == null){
            newNode.setColor(0);;
            return;
        }

        if(newNode.getParent().getParent() == null){
            return;
        }
        fixInsert(newNode);
    }

    private void fixInsert(RedBlackNode<K, T> k){
        RedBlackNode<K, T> u;
        while (k.getParent().getColor() == 1){
            if(k.getParent() == k.getParent().getParent().getRight()){
                u = k.getParent().getParent().getLeft();
                if(u.getColor() == 1){
                    u.setColor(0);
                    k.getParent().setColor(0);
                    k.getParent().getParent().setColor(1);
                    k = k.getParent().getParent();
                }
                else {
                    if(k == k.getParent().getLeft()){
                        k = k.getParent();
                        rightRotate(k);
                    }
                    k.getParent().setColor(0);
                    k.getParent().getParent().setColor(1);
                    leftRotate(k.getParent().getParent());
                }
            } else {
                u = k.getParent().getParent().getRight();

                if(u.getColor() == 1){
                    u.setColor(0);
                    k.getParent().setColor(0);
                    k.getParent().getParent().setColor(1);
                    k = k.getParent().getParent();
                }
                else {
                    if(k == k.getParent().getRight()){
                        k = k.getParent();
                        leftRotate(k);
                    }
                    k.getParent().setColor(0);
                    k.getParent().getParent().setColor(1);
                    rightRotate(k.getParent().getParent());
                }
            }
            if(k == root){
                break;
            }
        }
        root.setColor(0);
    }

    private void rightRotate(RedBlackNode<K, T> x){
        RedBlackNode<K, T> y = x.getLeft();
        x.setLeft(y.getRight());
        if(y.getRight() != TNULL){
            y.getRight().setParent(x);
        }
        y.setParent(x.getParent());
        if(x.getParent() == null){
            this.root = y;
        }
        else if(x == x.getParent().getRight()){
            x.getParent().setRight(y);
        }
        else {
            x.getParent().setLeft(y);
        }
        y.setRight(x);
        x.setParent(y);
    }

    private void leftRotate(RedBlackNode<K, T> x){
        RedBlackNode<K, T> y = x.getRight();
        x.setRight(y.getLeft());
        if(y.getLeft() != TNULL){
            y.getLeft().setParent(x);
        }
        y.setParent(x.getParent());
        if(x.getParent() == null){
            this.root = y;
        }
        else if(x == x.getParent().getLeft()){
            x.getParent().setLeft(y);
        }
        else {
            x.getParent().setRight(y);
        }
        y.setLeft(x);
        x.setParent(y);
    }

    public Node<K, T> find(K key){
        return  findHelper(this.root, key);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    private RedBlackNode<K, T> findHelper(RedBlackNode<K, T> node, K key){
        if(node == TNULL || key == node.getKey()){
            return node;
        }
        if(key.compareTo(node.getKey()) < 0){
            return findHelper(node.getLeft(), key);
        }
        return findHelper(node.getRight(), key);
    }

    public void remove(K key){
        deleteNodeHelper(this.root, key);
    }

    private void deleteNodeHelper(RedBlackNode<K, T> node, K key){
        RedBlackNode<K, T> z = TNULL;
        RedBlackNode<K, T> x, y;
        while(node != TNULL){
            if(node.getKey() == key){
                z = node;
            }
            if(node.getKey().compareTo(key) <= 0){
                node = node.getRight();
            }
            else{
                node = node.getLeft();
            }
        }

        if(z == TNULL){
            System.out.println("Couldn't find key in the tree");
            return;
        }

        y = z;
        int yOriginalColor = y.getColor();
        if(z.getLeft() == TNULL){
            x = z.getRight();
            rbTransplant(z, z.getRight());
        }
        else if(z.getRight() == TNULL){
            x = z.getLeft();
            rbTransplant(z, z.getLeft());
        }
        else {
            y = minimum(z.getRight());
            yOriginalColor = y.getColor();
            x = y.getRight();
            if(y.getParent() == z){
                x.setParent(y);
            }
            else {
                rbTransplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }

            rbTransplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            y.setColor(z.getColor());
        }
        if(yOriginalColor == 0){
            fixDelete(x);
        }
    }

    private void rbTransplant(RedBlackNode<K, T> u, RedBlackNode<K, T> v){
        if(u.getParent() == null){
            root = v;
        }
        else if(u == u.getParent().getLeft()){
            u.getParent().setLeft(v);
        }
        else{
            u.getParent().setRight(v);
        }
        v.setParent(u.getParent());
    }

    public RedBlackNode<K, T> minimum(RedBlackNode<K, T> node){
        while (node.getLeft() != TNULL){
            node = node.getLeft();
        }
        return node;
    }

    private void fixDelete(RedBlackNode<K, T> x){
        RedBlackNode<K, T> s;
        while(x != root && x.getColor() == 0){
            if(x == x.getParent().getLeft()){
                s = x.getParent().getRight();
                if(s.getColor() == 1){
                    s.setColor(0);
                    x.getParent().setColor(1);
                    leftRotate(x.getParent());
                    s = x.getParent().getRight();
                }

                if(s.getLeft().getColor() == 0 && s.getRight().getColor() == 0){
                    s.setColor(1);
                    x = x.getParent();
                }
                else {
                    if(s.getRight().getColor() == 0){
                        s.getLeft().setColor(0);
                        s.setColor(1);
                        rightRotate(s);
                        s = x.getParent().getRight();
                    }

                    s.setColor(x.getParent().getColor());
                    x.getParent().setColor(0);
                    s.getRight().setColor(0);
                    leftRotate(x.getParent());
                    x = root;
                }
            }
            else{
                s = x.getParent().getLeft();
                if(s.getColor() == 1){
                    s.setColor(0);
                    x.getParent().setColor(1);
                    rightRotate(x.getParent());
                    s = x.getParent().getLeft();
                }
                if(s.getRight().getColor() == 0 && s.getLeft().getColor() == 0){
                    s.setColor(1);
                    x = x.getParent();
                }
                else {
                    if(s.getLeft().getColor() == 0){
                        s.getRight().setColor(0);
                        s.setColor(1);
                        leftRotate(s);
                        s = x.getParent().getLeft();
                    }

                    s.setColor(x.getParent().getColor());
                    x.getParent().setColor(0);
                    s.getLeft().setColor(0);
                    rightRotate(x.getParent());
                    x = root;
                }
            }
        }
        x.setColor(0);
    }

    private class Itr implements Iterator<Node<K, T>>{
        private RedBlackNode<K, T> next;

        private Itr() {
            next = root;
            if(next == TNULL)
                return;
            while(next.left != TNULL)
                next = next.left;
        }

        @Override
        public boolean hasNext() {
            return next != TNULL;
        }

        @Override
        public Node<K, T> next() {
            if(!hasNext()) return null;
            RedBlackNode<K, T> r = next;

            if(next.right != null) {
                next = next.right;
                while (next.left != null)
                    next = next.left;
                return r;
            }
            while(true) {
                if(next.parent == null) {
                    next = null;
                    return r;
                }
                if(next.parent.left == next) {
                    next = next.parent;
                    return r;
                }
                next = next.parent;
            }
        }
    }

}
