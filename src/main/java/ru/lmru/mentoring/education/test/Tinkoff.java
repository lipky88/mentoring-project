package ru.lmru.mentoring.education.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Tinkoff {
    //Дан неотсортированный массив из N чисел от 1 до N, при этом несколько чисел из диапазона [1, N] пропущено, а
    // некоторые присутствуют дважды. Найти все пропущенные числа без использования дополнительной памяти.
    //findDisappearedNumbers([5, 2, 3, 7, 2, 1, 5]) == [4, 6]
    Set<Integer> absentValues(List<Integer> values) {
        Set<Integer> result = new HashSet<>();
        values.stream()
              .sorted()
              .forEach(value -> {
                  if (!values.contains(value + 1) && !values.stream().max(Comparator.naturalOrder()).get()
                                                            .equals(value)) {
                      result.add(value + 1);
                  }
              });
        Integer temp = 1;

        return result;
    }

    //Дан неотсортированный массив чисел от 0 до N,
    //при этом ровно одно из чисел диапазона [0, N] пропущено, а остальные присутствуют.
    //Найти пропущенное число.

    Integer getLostNumber(List<Integer> values) {
        AtomicInteger result = new AtomicInteger();
        List<Integer> sortedValues = values.stream()
                                           .sorted()
                                           .collect(Collectors.toList());
        sortedValues
                .forEach(value -> {
                    if (sortedValues.indexOf(value) != value) {
                        return;
                    }
                    result.set(result.get() + 1);
                });
        return result.get();
    }

    public static void main(String[] args) {
        Tinkoff tmp = new Tinkoff();
        List<Integer> list = new ArrayList<>(asList(0, 1, 4, 3, 2, 6));
        System.out.println(tmp.getLostNumber(list));
    }
}
