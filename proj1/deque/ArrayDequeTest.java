package deque;

import deque.ArrayDeque;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test

    public void addIsEmptySizeTest() {
        ArrayDeque<String> ads = new ArrayDeque<>();

        assertTrue("A newly initialized ArrayDeque should be empty", ads.isEmpty());

        ads.addFirst("addFirst1");

        assertEquals(1, ads.size());
        assertFalse("ads should now contain 1 item", ads.isEmpty());

        ads.addLast("addLast1");
        assertEquals(2, ads.size());

        ads.addLast("addLast2");
        assertEquals(3, ads.size());

        System.out.println("Printing out ArrayDeque: ");
        ads.printDeque();
    }
    @Test
    public void randomTest(){
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        lld1.addFirst(0);
        lld1.addLast(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.addFirst(4);
        lld1.addFirst(5);
        lld1.addFirst(6);
        lld1.addFirst(7);
        lld1.addLast(8);
        lld1.addLast(9);

    }
    @Test
    public void getIndexTest(){
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 9; i++){
            lld1.addLast(i);
        }
        int test1 = lld1.get(0);
        assertEquals(0, test1);

        lld1.addLast(0);
        lld1.addFirst(1);
        lld1.addLast(2);
        lld1.get(0);
        lld1.addFirst(4);
        lld1.addLast(5);
        lld1.removeFirst();
        lld1.addFirst(7);
        lld1.addFirst(8);
        lld1.get(0);
        lld1.addLast(10);
        lld1.removeLast();
        lld1.removeFirst();
        lld1.addLast(13);
        lld1.addLast(14);
        lld1.addLast(15);
        lld1.removeFirst();
        lld1.addLast(17);

    }

    @Test
    public void addRemoveTest() {
        ArrayDeque <Integer> adi = new ArrayDeque<>();

        assertTrue("adi should be empty upon initialization", adi.isEmpty());

        adi.addFirst(10);
        assertFalse("lld1 should contain 1 item", adi.isEmpty());

        adi.removeFirst();
        assertTrue("lld1 should be empty after removal", adi.isEmpty());
    }

    @Test
    public void removeEmptyTest() {

        ArrayDeque<Integer> adi2 = new ArrayDeque<>();
        adi2.addFirst(3);

        adi2.removeLast();
        adi2.removeFirst();
        adi2.removeLast();
        adi2.removeFirst();

        int size = adi2.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    public void multipleParamTest() {

        ArrayDeque<String>  lld1 = new ArrayDeque<String>();
        ArrayDeque<Double>  lld2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> lld3 = new ArrayDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }
    @Test
    public void emptyNullReturnTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }
    @Test
    public void bigLLDequeTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }




}
