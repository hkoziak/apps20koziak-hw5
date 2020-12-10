package ua.edu.ucu.iters;

import ua.edu.ucu.function.IntPredicate;
import java.util.Iterator;

public class FilterIter implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private IntPredicate pred;
    private int value;

    public FilterIter(Iterator<Integer> mainIterator, IntPredicate predicate) {
        this.iterator = mainIterator;
        this.pred = predicate;
    }

    @Override
    public Integer next() {
        return value;
    }

    @Override
    public boolean hasNext() {
        while (iterator.hasNext()) {
            value = iterator.next();
            if (pred.test(value)) {
                return true;
            }
        }
        return false;
    }
}
