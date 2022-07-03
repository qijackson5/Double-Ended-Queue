import deque.ArrayDeque;

import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayDeque class.
 *  @author Jackson Qi
 *  Credit: Josh Hug's lecture
 */

public class ArrayDequeTest {

    @Test
    public void testEmptyDeque() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addFirst(1);
        L.addLast(2);
        L.removeFirst();
        L.removeFirst();
        assertEquals(0, L.size());
    }

    @Test
    public void testAddLastAndSize() {
        ArrayDeque L = new ArrayDeque();
        for (int i = 0; i < 100; i += 1) {
            L.addLast(i);
        }
        assertEquals(100, L.size());
    }

    @Test
    public void testAddFirstAndSize() {
        ArrayDeque L = new ArrayDeque();
        for (int i = 0; i < 10000; i += 1) {
            L.addFirst(i);
        }
        assertEquals(10000, L.size());
    }

    @Test
    public void testGet1() {
        ArrayDeque L = new ArrayDeque();
        L.addLast(10);
        assertEquals(10, L.get(0));
        L.addFirst(0);
        assertEquals(0, L.get(0));
        L.addLast(11);
        assertEquals(10, L.get(1));
        assertEquals(11, L.get(2));
    }

    @Test
    public void testGet2() {
        ArrayDeque L = new ArrayDeque();
        for (int i = 0; i < 1000; i += 1) {
            L.addFirst(i);
            assertEquals(i, L.get(0));
        }
    }

    @Test
    public void testGet3() {
        ArrayDeque L = new ArrayDeque();
        for (int i = 0; i < 1000; i += 1) {
            L.addLast(i);
            assertEquals(i, L.get(i));
        }
    }

    @Test
    public void testRemovelastAndSize() {
        ArrayDeque L = new ArrayDeque();
        L.addLast(100);
        L.addFirst(0);
        assertEquals(100, L.get(1));
        L.addLast(200);
        assertEquals(200, L.get(2));
        L.removeLast();
        assertEquals(100, L.get(L.size() - 1));
        L.addLast(300);
        assertEquals(3, L.size());
    }

    @Test
    public void testRemoveFirstAndsize() {
        ArrayDeque L = new ArrayDeque();
        for (int i = 0; i < 100; i += 1) {
            L.addLast(i);
        }
        L.removeFirst();
        L.removeFirst();
        L.removeFirst();
        assertEquals(97, L.size());
        assertEquals(3, L.get(0));
    }

    /**
     * Tests addLast with a lot of strings
     */
    @Test
    public void testhugeaddLast() {
        ArrayDeque L = new ArrayDeque();
        int total = 100000;
        for (int i = 0; i < total; i += 1) {
            L.addLast("hello");
        }

        for (int i = 0; i < total; i += 1) {
            L.addLast(", World");
        }

        assertEquals(total * 2, L.size());

    }

    @Test
    public void testHugeAddFirstThenAddLast() {
        ArrayDeque L = new ArrayDeque();
        int total = 100000;
        for (int i = 0; i < total; i += 1) {
            L.addFirst("hello");
        }

        for (int i = 0; i < total; i += 1) {
            L.addLast(", World");
        }

        assertEquals(total * 2, L.size());
    }

    @Test
    public void equalsTest() {
        ArrayDeque<Integer> L1 = new ArrayDeque<>();
        ArrayDeque<Integer> L2 = new ArrayDeque<>();

        for (int i = 0; i < 100; i += 10) {
            L1.addLast(i);
            L2.addLast(i);
        }
        assertTrue(L1.equals(L2));
        L2.addLast(3);
        assertFalse(L1.equals(L2));
    }

    @Test
    public void resizeDownTest() {
        ArrayDeque<Integer> L = new ArrayDeque();
        int total = 35;
        for (int i = 0; i < total; i += 1) {
            L.addFirst(i);
        }
        for (int j = 0; j < 30; j += 1) {
            L.removeLast();
        }
        Integer expected = 34;
        assertEquals(expected, L.get(0));
        assertEquals(5, L.size());
    }
}