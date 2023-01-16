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
        if (isEmpty()) {
            return null;
        }
        T maxItem;
        maxItem = this.get(0);
        for (int i = 0; i < size(); i++) {
            T current = get(i);
            if (c.compare(maxItem, current) < 0) {
                maxItem = current;
            }
        }
        return maxItem;
    }

}
