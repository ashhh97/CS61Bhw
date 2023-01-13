package deque;
import java.util.Iterator;

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
        if(nextFirst > nextLast){
            int start = items.length - (nextFirst+1);
            System.arraycopy(items, nextFirst + 1, re, 1 ,start);
            System.arraycopy(items, 0, re, start+1, nextLast);
        } else {
            System.arraycopy(items, nextFirst, re, 1 ,size);
        }
        items = re;
        nextFirst = 0;
        nextLast = size +1;
    }


    @Override
    public void addFirst(T item) {
        int theLastOne = items.length - 1;
        int resizeInt = items.length * 2;
        if (size == items.length - 2){
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
        if (size == items.length - 2){
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

    @Override
    public T removeFirst() {
        if(isEmpty()) {
            return null;
        }
        size = size - 1;
        T removed = items[nextFirst+1];
        items[nextFirst] = null;
        nextFirst = nextFirst + 1;
        return removed;
    }

    @Override
    public T removeLast() {
        if(isEmpty()) {
            return null;
        }
        size = size - 1;
        T removed = items[nextLast];
        items[nextLast] = null;
        nextLast = nextLast - 1;
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
