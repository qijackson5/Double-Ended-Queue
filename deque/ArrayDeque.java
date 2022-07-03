package deque;
import java.util.Iterator;
/**
 *
 * @author Jackson
 * Credit to Josh Hug's lecture and office hour staff
 */
public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] aDeque;
    private int size = 0;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        aDeque = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }


    /** adds an item to the front of the list */
    @Override
    public void addFirst(T x) {
        if (size == aDeque.length) {
            resize(size * 2);
        }
        aDeque[nextFirst] = x;
        nextFirst -= 1;
        if (nextFirst < 0) {
            nextFirst = aDeque.length - 1;
        }
        size += 1;
    }

    /** adds an item to end of the list*/
    @Override
    public void addLast(T x) {
        if (size == aDeque.length) {
            resize(size * 2);
        }
        aDeque[nextLast] = x;
        nextLast = nextLast + 1;
        if (nextLast == aDeque.length) {
            nextLast = 0;
        }
        size += 1;
    }

    /** resizes an array, if size is too big, it will shrink the array
     * and if needs space, then will enlarge */
    private void resize(int newSize) {
        T[] updatedArray = (T[]) new Object[newSize];

        if (nextFirst + 1 == aDeque.length) {
            System.arraycopy(aDeque, 0, updatedArray, 0, size);
        } else if (nextFirst + 1 < nextLast) {
            System.arraycopy(aDeque, nextFirst + 1, updatedArray, 0, size);
        } else {
            System.arraycopy(aDeque, nextFirst + 1, updatedArray, 0,
                    aDeque.length - (nextFirst + 1));
            System.arraycopy(aDeque, 0, updatedArray, aDeque.length - (nextFirst + 1),
                    size - (aDeque.length - (nextFirst + 1)));
        }
        aDeque = updatedArray;
        nextFirst = newSize - 1;
        nextLast = size;
    }


    /** returns the size of the list */
    @Override
    public int size() {
        return size;
    }

    /** prints out the deque in a string */
    @Override
    public void printDeque() {
        for (int i = 0; i < size; i += 1) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    /** removes the first element of the deque and returns it */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if ((aDeque.length >= 16) && (aDeque.length > 4 * size)) {
            resize(aDeque.length / 4);
        }
        size -= 1;
        if (nextFirst + 1 > aDeque.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst += 1;
        }
        T temp = aDeque[nextFirst];
        aDeque[nextFirst] = null;
        return temp;
    }

    /** removes the last element of the deque and returns the item */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (aDeque.length >= 16 && aDeque.length > 4 * size) {
            resize(aDeque.length / 4);
        }
        size -= 1;
        if (nextLast - 1 < 0) {
            nextLast = aDeque.length - 1;
        } else {
            nextLast -= 1;
        }
        T temp = aDeque[nextLast];
        aDeque[nextLast] = null;
        return temp;
    }

    /** returns the item of the index passed in*/
    @Override
    public T get(int index) {
        if ((index > size - 1) || (index < 0))  {
            return null;
        }
        T item = aDeque[(nextFirst + 1 + index) % aDeque.length];
        return item;
    }

    /** Returns whether the parameter o is equal to the Deque(this). */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (other.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i += 1) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }

    /** returns an iterator */
    public Iterator<T> iterator() {
        return new ADequeIterator();
    }

    /** class ADequeIterator which implements iterator that has hasNext method
     * and next method, and returns the item */
    private class ADequeIterator implements Iterator<T> {
        private int pos;
        ADequeIterator() {
            pos = 0;
        }
        public boolean hasNext() {
            return pos < size;
        }
        public T next() {
            T theItem = get(pos);
            pos += 1;
            return theItem;
        }
    }
}
