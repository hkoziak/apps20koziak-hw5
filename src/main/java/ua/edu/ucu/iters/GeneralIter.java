package ua.edu.ucu.iters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class GeneralIter implements Iterator<Integer> {
    private ArrayList values = new ArrayList();
    private int i = 0;

    public GeneralIter(int... elements) {
        for (int el: elements) {
            values.add(el);
        }
    }

    @Override
    public boolean hasNext() {
        return values.size() > i;
    }

    @Override
    public Integer next() throws NoSuchElementException {
        int result = (Integer) values.get(i++);
        if (Objects.isNull(result)) {
            throw new NoSuchElementException();
        }
        return result;
    }
}
