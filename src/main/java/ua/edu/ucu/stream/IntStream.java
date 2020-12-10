package ua.edu.ucu.stream;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.function.IntPredicate;
import ua.edu.ucu.function.IntConsumer;
import ua.edu.ucu.function.IntUnaryOperator;
import ua.edu.ucu.function.IntBinaryOperator;

public interface IntStream {

    long average();

    int max();

    int min();
    
    IntStream flatMap(IntToIntStreamFunction func);

    long count();

    IntStream filter(IntPredicate predicate);

    void forEach(IntConsumer action);

    IntStream map(IntUnaryOperator mapper);

    int reduce(int identity, IntBinaryOperator op);

    int sum();

    int[] toArray();
}
