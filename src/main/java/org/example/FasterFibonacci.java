package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RecursiveTask;



/*
However, besides being a dumb way to compute Fibonacci functions (there is a simple fast linear algorithm that you'd use in practice),
 this is likely to perform poorly because the smallest subtasks are too small to be worthwhile splitting up.
 Instead, as is the case for nearly all fork/join applications,
 you'd pick some minimum granularity size (for example 10 here) for which you always sequentially solve rather than subdividing.
 */

public class FasterFibonacci extends RecursiveTask<Integer> {
    final int n;
    public FasterFibonacci(int n) { this.n = n; }
    private static Map<Integer, Integer> results = new HashMap<>();
    public Integer compute() {

        if(n > 20) {
            if (n <= 1)
                return n;
            FasterFibonacci f1 = new FasterFibonacci(n - 1);
            f1.fork();
            FasterFibonacci f2 = new FasterFibonacci(n - 2);
            return f2.compute() + f1.join();
        }else{
            return computeSeq();
        }
    }

    public Integer computeSeq() {

        if (n == 0) {
            return 0;
        } else if (n <= 2) { // if
            return 1;
        }
        if (results.get(n) != null) {
            return results.get(n);
        } else {
            FasterFibonacci f1 = new FasterFibonacci(n - 1);
            FasterFibonacci f2 = new FasterFibonacci(n - 2);
            int v = f2.computeSeq() + f1.computeSeq();
            results.put(n, v);
            return v;
        }
    }

}
