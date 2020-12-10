package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iters.FilterIter;
import ua.edu.ucu.iters.FlatMapIter;
import ua.edu.ucu.iters.GeneralIter;
import ua.edu.ucu.iters.MapIter;
import java.util.ArrayList;
import java.util.Iterator;

public class AsIntStream implements IntStream {
    private Iterator<Integer> intIterator;
    private AsIntStream(Iterator<Integer> iterator) {
        intIterator = iterator;
    }
    public static IntStream of(int... values) {
        return new AsIntStream(new GeneralIter(values));
    }

    private void checkIfEmpty () {
        if (!intIterator.hasNext()) throw new IllegalArgumentException();
    }

    @Override
    public Double average() {
        checkIfEmpty();
        int sum = 0;
        int size = 0;
        while (intIterator.hasNext()) {
            size++;
            sum += intIterator.next();
        }
        double result = sum / (double) size;
        return result;
    }

    @Override
    public Integer max() {
        checkIfEmpty();
        int max = 0;
        while (intIterator.hasNext()) {
            int nextElement = intIterator.next();
            if (nextElement > max) {
                max = nextElement;
            }
        }
        return max;
    }

    @Override
    public Integer min() {
        checkIfEmpty();
        int min = Integer.MAX_VALUE;
        while (intIterator.hasNext()) {
            int next = intIterator.next();
            if (next < min) {
                min = next;
            }
        }
        return min;
    }

    @Override
    public long count() {
        long size = 0;
        while (intIterator.hasNext()) {
            size++;
            intIterator.next();
        }
        return size;
    }

    @Override
    public Integer sum() {
        checkIfEmpty();
        return reduce(0, (sum, x) -> sum += x);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIter(intIterator, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        for (Iterator<Integer> it = intIterator; it.hasNext(); ) {
            Integer el = it.next();
            action.accept(el);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIter(intIterator, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIter(intIterator, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        for (Iterator<Integer> it = intIterator; it.hasNext(); ) {
            Integer el = it.next();
            identity = op.apply(identity, el);
        }
        return identity;
    }

    @Override
    public int[] toArray() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (Iterator<Integer> it = intIterator; it.hasNext(); ) {
            Integer value = it.next();
            arrayList.add(value);
        }
        int[] result = new int[arrayList.size()];
        int i = 0;
        for (Integer el: arrayList) {
            result[i++] = el;
        }
        return result;
    }
}
