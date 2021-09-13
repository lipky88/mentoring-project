package ru.lmru.mentoring.education.factorial;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultithreadFactorialCalculation {

    private List<CustomThread> createThreads(int threadCount, List<Range> ranges, List<BigInteger> precalculation) {
        List<CustomThread> executionThreads = new ArrayList<>();
        for (int i = 1; i <= threadCount; i++) {
            executionThreads.add(new CustomThread(ranges.get(i - 1), i, precalculation));
        }
        return executionThreads;
    }

    private void runThreads(List<CustomThread> threads) {
        for (int i = 0; i <= threads.size() - 1; i++) {
            threads.get(i).start();
        }
    }

    private void waitThreads(List<CustomThread> threads) throws InterruptedException {
        for (int i = 0; i <= threads.size() - 1; i++) {
            threads.get(i).join();
        }
    }

    public BigInteger calculate(int threadCount, long number) throws InterruptedException {
        long rangeDifference = getRageCount(threadCount, number);
//        System.out.println("rangeDifference:" + rangeDifference);

        List<Range> ranges = getRanges(rangeDifference, number);

        List<BigInteger> precalculation = Arrays.asList(new BigInteger[threadCount]);
        List<CustomThread> threads = createThreads(threadCount, ranges, precalculation);
        runThreads(threads);
        waitThreads(threads);

        BigInteger result = BigInteger.ONE;
        for (BigInteger value : precalculation) {
            result = result.multiply(value);
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
        int threads = 10;
        long number = 10000;

        long sumTime = 0L;
        for (int i = 0; i < 100; i++) {
            MultithreadFactorialCalculation test = new MultithreadFactorialCalculation();
            long startTime = System.nanoTime();
            BigInteger result = test.calculate(threads, number);
            long endTime = System.nanoTime();
            sumTime += endTime - startTime;
//            System.out.println(endTime - startTime);
//            System.out.println("result: " + result);
        }

        System.out.println("\navgTime = " + sumTime / 100);

        //long number = 10000;
        //1:  avgTime = 40788988
        //2:  avgTime = 22718350
        //3:  avgTime = 14685377
        //4:  avgTime = 13019964
        //5:  avgTime = 13223878
        //6:  avgTime = 12943815
        //7:  avgTime = 13864885
        //8:  avgTime = 13501970
        //9:  avgTime = 14845343
        //10: avgTime = 15539884

        //long number = 90000;
        //1:  avgTime = 966478104
        //2:  avgTime = 330120610
        //3:  avgTime = 196390364
        //4:  avgTime = 148242053
        //5:  avgTime = 133780609
        //6:  avgTime = 134151135
        //7:  avgTime = 110620422
        //8:  avgTime = 109504402
        //9:  avgTime = 117933499
        //10: avgTime = 122350301
    }


    private static class CustomThread extends Thread {
        private Range range;
        private int threadNumber;
        private List<BigInteger> precalculation;

        CustomThread(Range range, int threadNumber, List<BigInteger> precalculation) {
            this.range = range;
            this.threadNumber = threadNumber;
            this.precalculation = precalculation;
        }

        @Override
        public void run()    //Этот метод будет выполнен в побочном потоке
        {
            BigInteger result = BigInteger.ONE;
            for (long i = range.getStartValue(); i <= range.getEndValue(); i++) {
                result = result.multiply(BigInteger.valueOf(i));
            }
            precalculation.set(threadNumber - 1, result);
        }
    }
}
