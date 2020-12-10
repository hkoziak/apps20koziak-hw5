package ua.edu.ucu.iters;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.stream.AsIntStream;

import java.util.Iterator;

public class FlatMapIter implements Iterator<Integer> {
    private Iterator<Integer> iter;
    private IntToIntStreamFunction stamp;
    private GeneralIter addIter;

    public FlatMapIter(Iterator<Integer> genIterator, IntToIntStreamFunction func) {
        this.iter = genIterator;
        this.stamp = func;
        this.addIter = new GeneralIter(); //additional iterator for internal iteration
    }

    @Override
    public boolean hasNext() {
        if (addIter.hasNext()) {
            return true;
        }
        if (iter.hasNext()) {
            AsIntStream tempStream = (AsIntStream) stamp.applyAsIntStream(iter.next());
            addIter = new GeneralIter(tempStream.toArray());
            return true;
        }
        return false;
    }

    @Override
    public Integer next() {
        return addIter.next();
    }
}
