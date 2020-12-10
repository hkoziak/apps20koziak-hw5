package ua.edu.ucu.stream;

import ua.edu.ucu.function.IntPredicate;
import ua.edu.ucu.function.IntBinaryOperator;
import ua.edu.ucu.function.IntConsumer;
import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.function.IntUnaryOperator;

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

    private void checkIfEmpty() {
        if (!intIterator.hasNext()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public long average() {
        checkIfEmpty();
        int sum = 0;
        int size = 0;
        while (intIterator.hasNext()) {
            size++;
            sum += intIterator.next();
        }
        double result = sum / (double) size;
        return (long) result;
    }

    @Override
    public int max() {
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
    public int min() {
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
    public int sum() {
        checkIfEmpty();
        return reduce(0, (sum, x) -> sum += x);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIter(intIterator, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        for (Iterator<Integer> it = intIterator; it.hasNext();) {
            int el = it.next();
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
        int id_dupl = identity;
        for (Iterator<Integer> it = intIterator; it.hasNext();) {
            int el = it.next();
            id_dupl = op.apply(id_dupl, el);
        }
        return id_dupl;
    }

    @Override
    public int[] toArray() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (Iterator<Integer> it = intIterator; it.hasNext();) {
            int value = it.next();
            arrayList.add(value);
        }
        int[] result = new int[arrayList.size()];
        int i = 0;
        for (int el: arrayList) {
            result[i++] = el;
        }
        return result;
    }
}
