package ua.edu.ucu.iters;

import java.util.ArrayList;
import java.util.Iterator;

public class GeneralIter implements Iterator<Integer> {
    private ArrayList values = new ArrayList();
    private int i = 0;

    public GeneralIter(int... elements) {
        for (Integer el: elements) {
            values.add(el);
        }
    }

    @Override
    public boolean hasNext() {
        return values.size() > i;
    }

    @Override
    public Integer next() {
        return (Integer) values.get(i++);
    }

}
