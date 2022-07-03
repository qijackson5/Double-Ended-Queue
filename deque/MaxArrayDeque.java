package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comp;


    public MaxArrayDeque(Comparator<T> c) {
        super();
        comp = c;
    }

    public T max() {
        return max(comp);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T currMax = get(0);
        for (int i = 1; i < size(); i += 1) {
            int output = c.compare(get(i), currMax);
            if (output >= 0) {
                currMax = get(i);
            }
        }
        return currMax;
    }
}
