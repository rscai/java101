package me.raycai.forkjoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RecursiveTask;

/**
 * Created by kkppccdd on 2016/12/29.
 */
class Fibonacci extends RecursiveTask<Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Fibonacci.class);
    final int n;

    Fibonacci(int n) {
        this.n = n;
    }

    public Integer compute() {
        if (n <= 1)
            return n;
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        return f2.compute() + f1.join();
    }
}
