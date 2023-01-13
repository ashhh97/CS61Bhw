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
        System.arraycopy(items, 0, re, 2,size);
        items = re;
        nextFirst = 1;
        nextLast = Math.floorMod((nextFirst + size), items.length);
    }


    @Override
    public void addFirst(T item) {
        int theLastOne = items.length - 1;
        int resizeInt = items.length * 2;

        if(isFull() == false) {
            size = size+1;
            items[nextFirst] = item;
            nextFirst--;
            if(nextFirst < 0 && items[theLastOne]==null) {
                nextFirst = theLastOne;
            }
        }else {
            resize(resizeInt);//items.length*2
        }
    }

    @Override
    public void addLast(T item) {
        int resizeInt = items.length*2;
        while(isFull() == false) {
            size++;
            items[nextLast] = item;
            nextLast++;
            if (nextLast >= items.length && items[theFirstOne] == null) {
                nextLast = theFirstOne;
            }
        }
         while(isFull() == true) {
             resize(resizeInt);
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
