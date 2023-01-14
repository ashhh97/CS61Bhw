
package deque;
import deque.Deque;

import java.util.Arrays;
import java.util.Iterator;

import static java.util.Arrays.*;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] items;
    private int startingSize = 8;
    private int size;
    private int nextFirst = 1;
    private int nextLast = 2;

    private int theFirstOne = 0;

    private int swap;



    public ArrayDeque(){
        items = (T[]) new Object[startingSize];
        size = 0;

    }

    public boolean isFull() {
        return size == items.length;
    }
    public void resize(int capacity){
        T[] re = (T[]) new Object[capacity];
//        if(nextFirst > nextLast){
            System.arraycopy(items, nextLast, re, 0 ,size - nextLast);
            System.arraycopy(items,0, re, size - nextLast, size-(size - nextLast));
//        } else {
//            System.arraycopy(items, nextFirst, re, 0 ,size - nextLast);
//            System.arraycopy(items,0, re, size - nextLast, size-(size - nextLast));
//        }
        items = re;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T item) {
        int theLastOne = items.length - 1;
        int resizeInt = items.length * 2;

        if (isFull()){
            resize(resizeInt);
        }
        size = size + 1;
        items[nextFirst] = item;
        nextFirst = nextFirst - 1;
        if(nextFirst < 0 && items[theLastOne] == null) {
            nextFirst = theLastOne;
        }
    }

    @Override
    public void addLast(T item) {
        int resizeInt = items.length*2;
        if (isFull()){
            resize(resizeInt);
        }
        size = size + 1;
        items[nextLast] = item;
        nextLast = nextLast + 1;
        if (nextLast >= items.length && items[theFirstOne] == null) {
            nextLast = theFirstOne;
        }
    }

//    @Override
//    public boolean isEmpty() {
//        return size == 0;
//    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < items.length; i++) {
            System.out.print(items[i]+ " ");
        }
        System.out.println(" ");
    }
    private int getCurrentFirst(){
        int currentFirst = nextFirst + 1;
        if(currentFirst > items.length - 1){
            currentFirst = 0;
        }
        return currentFirst;
    }
    private int getCurrentLast(){
        int currentLast = nextLast - 1;
        if(currentLast < 0){
            currentLast = items.length - 1;
        }
        return currentLast;
    }



    @Override
    public T removeFirst() {
        if(isEmpty()) {
            return null;
        }
        size = size - 1;

        int currentFirst = getCurrentFirst();

        T removed = items[currentFirst];
        items[currentFirst] = null;

        nextFirst = currentFirst;

        return removed;
    }

    @Override
    public T removeLast() {
        if(isEmpty()) {
            return null;
        }
        size = size - 1;

        int currentLast = getCurrentLast();

        T removed = items[currentLast];
        items[currentLast] = null;

        nextLast = currentLast;

        return removed;
    }

    @Override
    public T get(int index) {
        if(size == 1){
            return items[index+1];
        }
        if(isEmpty()) {
            return null;
        }
        return items[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T>{
        private int wizPos;
        public ArrayDequeIterator() {
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
}

