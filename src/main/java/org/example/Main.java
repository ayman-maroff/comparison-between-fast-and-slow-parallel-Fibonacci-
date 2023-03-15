package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


/*
However, besides being a dumb way to compute Fibonacci functions (there is a simple fast linear algorithm that you'd use in practice),
 this is likely to perform poorly because the smallest subtasks are too small to be worthwhile splitting up.
 Instead, as is the case for nearly all fork/join applications,
 you'd pick some minimum granularity size (for example 10 here) for which you always sequentially solve rather than subdividing.
 */


public class Main {



    public static void main(String[] args) {

        Fibonacci task1 = new Fibonacci(40);
        FasterFibonacci task2 = new FasterFibonacci(40);
        long start1 = System.currentTimeMillis();
        System.out.println("Fibonacci is :"+new ForkJoinPool().invoke(task1));
        long end1 = System.currentTimeMillis();
        System.out.println("implementation Time in milli seconds for Fibonacci is : "+ (end1-start1));
        long start2 = System.currentTimeMillis();
        System.out.println("faster Fibonacci is :"+new ForkJoinPool().invoke(task2));
        long end2 = System.currentTimeMillis();
        System.out.println("implementation Time in milli seconds for faster Fibonacci is : "+ (end2-start2));
    }
}