package deque;
import java.util.Iterator;
/**
 *
 * @author Jackson
 * Credit to Josh Hug's lecture and office hour staff
 */

/** give credit Josh Hug's lecture and TA's office hours for most of the methods */
public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private class GenNode {
        private GenNode prev;
        private T item;
        private GenNode next;

        GenNode(T i) {
            prev = null;
            item = i;
            next = null;
        }
    }

    private GenNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new GenNode(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** adds an item to the front of the list */
    @Override
    public void addFirst(T x) {
        GenNode addedNode = new GenNode(x);
        GenNode temp = sentinel.next;

        sentinel.next = addedNode;
        addedNode.prev = sentinel;
        addedNode.next = temp;
        temp.prev = addedNode;
        size += 1;
    }

    /** adds an item to end of the list*/
    @Override
    public void addLast(T x) {
        GenNode lastNode = new GenNode(x);
        GenNode temp = sentinel.prev;

        sentinel.prev = lastNode;
        lastNode.prev = temp;
        temp.next = lastNode;
        lastNode.next = sentinel;
        size += 1;
    }


    /** returns the size of the list */
    @Override
    public int size() {
        return size;
    }

    /** prints out the deque in a string */
    public void printDeque() {
        GenNode p = sentinel.next;
        for (int i = 0; i < size; i += 1) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /** removes the first element of the deque and returns it*/
    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T removedItem = sentinel.next.item;

        GenNode nodeAfterFirst = sentinel.next.next;
        sentinel.next = nodeAfterFirst;
        nodeAfterFirst.prev = sentinel;
        this.size -= 1;

        return removedItem;
    }

    /** removes the last element of the deque and returns the item */
    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T removedItem = sentinel.prev.item;
        GenNode nodeBeforeLast = sentinel.prev.prev;

        sentinel.prev = nodeBeforeLast;
        nodeBeforeLast.next = sentinel;
        this.size -= 1;
        return removedItem;
    }

    /** returns the item of the index passed in*/
    @Override
    public T get(int index) {
        if ((index > size() - 1) || (index < 0)) {
            return null;
        }
        GenNode p = sentinel.next;
        for (int i = 0; i < index; i += 1) {
            p = p.next;
        }
        return p.item;
    }

    /** same as get method, but with a recursive approach */
    public T getRecursive(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        GenNode first = sentinel.next;
        return getRecursive(first, index);
    }

    /** helper method for the recursive call */
    private T getRecursive(GenNode ddlst, int index) {
        if (index == 0) {
            return ddlst.item;
        }
        return getRecursive(ddlst.next, index - 1);
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
        return new LLDequeIterator();
    }

    /** class ADequeIterator which implements iterator that has hasNext method
     * and next method, and returns the item */
    private class LLDequeIterator implements Iterator<T> {
        private int pos;
        LLDequeIterator() {
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
