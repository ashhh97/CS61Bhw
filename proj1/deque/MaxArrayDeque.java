package deque;


import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> com;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        com = c;
    }

    public T max() {
        return max(com);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()){
            return null;
        }
        T maxItem;
        maxItem = this.get(0);
        for(T i : this) {
            if (com.compare(i, maxItem) > 0) {
                maxItem = i;
            }
        }
        return maxItem;
    }

}
