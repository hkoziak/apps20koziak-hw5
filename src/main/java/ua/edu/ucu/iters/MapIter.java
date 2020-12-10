package ua.edu.ucu.iters;

import ua.edu.ucu.function.IntUnaryOperator;
import java.util.Iterator;

public class MapIter implements Iterator<Integer> {
    private Iterator<Integer> iter;
    private IntUnaryOperator mapper;

    public MapIter(Iterator<Integer> genIterator, IntUnaryOperator mapper) {
        this.iter = genIterator;
        this.mapper = mapper;
    }
    @Override
    public boolean hasNext() {
        return iter.hasNext();
    }

    @Override
    public Integer next() {
        int next = iter.next();
        return mapper.apply(next);
    }
}
