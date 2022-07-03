package deque;

public interface Deque<T> {
    /** adds an item to the front of the list */
    void addFirst(T item);

    /** adds an item to end of the list*/
    void addLast(T item);

    /** returns the size of the list */
    int size();

    /** prints out the deque in a string */
    void printDeque();

    /** removes the first element of the deque and returns it */
    T removeFirst();

    /** removes the last element of the deque and returns the item */
    T removeLast();

    /** returns the item of the index passed in*/
    T get(int index);

    boolean equals(Object o);

    /** return true if deque is empty, else false*/
    default boolean isEmpty() {
        return size() == 0;
    }

}
