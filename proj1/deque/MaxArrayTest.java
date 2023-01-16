package deque;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayTest {
    @Test
    public void removeAddTest() {
        Comparator<Integer> test1 = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                } else if (o1 < o2) {
                    return -1;
                }
                return 0;
            }
        };
        MaxArrayDeque<Integer> test = new MaxArrayDeque<>(test1);

        assertTrue(test.isEmpty());

        test.addFirst(1);
        assertFalse(test.isEmpty());

        test.addLast(10);
        int max = test.max();
        assertEquals(10, max);
    }

}
