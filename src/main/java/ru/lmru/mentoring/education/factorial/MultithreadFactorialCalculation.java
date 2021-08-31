package ru.lmru.mentoring.education.factorial;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultithreadFactorialCalculation {

    public List<CustomThread> createThreads(int threadCount, List<Range> ranges, List<Long> precalculation) {
        List<CustomThread> executionThreads = new ArrayList<>();
        for (int i = 1; i <= threadCount; i++) {
            executionThreads.add(new CustomThread(ranges.get(i - 1), i, precalculation));
        }
        return executionThreads;
    }

    public void runThreads(List<CustomThread> threads) {
        for (int i = 0; i <= threads.size() - 1; i++) {
            threads.get(i).start();
        }
    }

    public void waitThreads(List<CustomThread> threads) throws InterruptedException {
        for (int i = 0; i <= threads.size() - 1; i++) {
            threads.get(i).join();
        }
    }

    public Long calculate(int threadCount, long number) throws InterruptedException {
        long rangeDifference = getRageCount(threadCount, number);
//        System.out.println("rangeDifference:" + rangeDifference);

        List<Range> ranges = getRanges(rangeDifference, number);

        List<Long> precalculation = Arrays.asList(new Long[threadCount]);
        List<CustomThread> threads = createThreads(threadCount, ranges, precalculation);
        runThreads(threads);
        waitThreads(threads);

        Long result = 1L;
        for (Long value : precalculation) {
            result *= value;
        }

        return result;
    }

    private long getRageCount(long threadCount, long number) {
        return number / threadCount;
    }

    private List<Range> getRanges(long rageCount, long number) {
        List<Range> resultRanges = new ArrayList<>();
        long lastBorderValue = 0L;
        for (long i = 1L; i <= number; i++) {
            if (i % rageCount == 0 && i - 1 + rageCount < number || i == number) {
//                System.out.println("left:" + (lastBorderValue + 1) + " right:" + i);
                resultRanges.add(new Range(lastBorderValue + 1, i));
                lastBorderValue = i;
            }
        }
        return resultRanges;
    }

    public static void main(String[] args) throws InterruptedException {
        int threads = 1;
        long number = 20;

        long sumTime = 0L;
        for (int i = 0; i < 100; i++) {
            MultithreadFactorialCalculation test = new MultithreadFactorialCalculation();
            long startTime = System.nanoTime();
            Long result = test.calculate(threads, number);
            long endTime = System.nanoTime();
            sumTime += endTime - startTime;
            System.out.println(endTime - startTime);
        }

        System.out.println("\navgTime = " + sumTime / 100);

        //1: 479969
        //2: 620589
        //3: 821463
        //4: 1012965
        //5: 1117506
        //6: 1416791
        //7: 1435521
        //8: 1756952
        //9: 1901002
        //10: 2030838
    }


    private static class CustomThread extends Thread {
        private Range range;
        private int threadNumber;
        private List<Long> precalculation;

        CustomThread(Range range, int threadNumber, List<Long> precalculation) {
            this.range = range;
            this.threadNumber = threadNumber;
            this.precalculation = precalculation;
        }

        @Override
        public void run()    //Этот метод будет выполнен в побочном потоке
        {
            long result = 1;
            for (long i = range.getStartValue(); i <= range.getEndValue(); i++) {
                result *= i;
            }
            precalculation.set(threadNumber - 1, result);
        }
    }
}
