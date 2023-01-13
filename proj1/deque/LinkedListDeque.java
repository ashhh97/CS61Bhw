package deque;

import org.eclipse.jetty.websocket.common.util.ReflectUtils;

import java.util.Iterator;
import java.util.ListIterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {


    private class Node{
        public Node prev;
        public T item;
        public Node next;

        public Node(Node p, T i, Node n){
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
//  private Node sentinelLast;
    private int size;

    /*Create an empty Deque*/
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public void addFirst(T item) {
        Node first = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next = first;
        size = size + 1;
    }

    @Override
    public void addLast(T item) {
        size = size + 1;
        Node last = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;
    }

//    @Override
//    public boolean isEmpty() {
//        if(sentinel.next == sentinel) {
//            return true;
//        } else return false;
//    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        Node helper = new Node(sentinel, sentinel.item, sentinel);
        if (helper.item == null) {
            helper = sentinel.next;
            System.out.print(helper.item+" ");
        }
        while (helper.item != null) {
            helper = helper.next;
            System.out.print(helper.item+" ");

            if(helper.next.item == null) {
                return;
            }
        }
        System.out.println(" ");
    }

    public T removeFirstHelper(){
        Node first = sentinel.next;
        Node newFirst = first.next;
        sentinel.next = newFirst;
        newFirst.prev = sentinel;
        return first.item;
    }

    @Override
    public T removeFirst() {
        if (size > 0) {
            size = size - 1;
        }
        if(sentinel.next == sentinel) {
            return null;
//            sentinel.next = sentinel.next.next;
//            sentinel.next.prev = sentinel;
        }
        return removeFirstHelper();
    }

    private T removeLastHelper(){
        Node lastNode = sentinel.prev;
        Node beforeLast = lastNode.prev;
        beforeLast.next = sentinel; //point it back to sentinel
        sentinel.prev = beforeLast;
        return lastNode.item;
    }

    @Override
    public T removeLast() {
        if (size > 0) {
            size = size - 1;
        }
        if (sentinel.next == sentinel) {
            return null;
        }
        return removeLastHelper();

    }

    @Override
    public T get(int index) {
        Node helper = sentinel;
        if(index < 0 && index > this.size()) {
            return null;
        }else {
            while(index >= 0){
                helper=helper.next;
                index = index-1;
            }
            return helper.item;
        }
    }

    //Same as get, but uses recursion.
    public T getRecursive(int index, Node T) {
//        Node helper = sentinel;
        if(index < 0 && index > this.size()) {
            return null;
        } else if(index == 0){
            return T.item;
        } else{
            T = T.next;
            index = index-1;
            getRecursive(index,T);
        }
        return T.item;
    }
    //test.

    //two special methods
    @Override
    public Iterator<T> iterator() {
        return new LListIterator();
    }
    private class LListIterator implements Iterator<T>{
        private int wizPos;
        public LListIterator() {
            wizPos = 0;
        }
        public boolean hasNext() {
            return wizPos < size;
        }

        public T next(){
            T returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }

    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        if (!(o instanceof Deque)){
            return false;
        }
        Deque<T> helper = (Deque<T>) o;
        if(helper.size() != this.size()){
            return false;
        }

        Iterator<T> objHelper = (Iterator<T>) o;
        Iterator<T> thisHelper = this.iterator();

        while (objHelper.hasNext()){
            if (objHelper.next() != thisHelper.next()){
                return false;
            }
        }

        return true;
    }
//
}
