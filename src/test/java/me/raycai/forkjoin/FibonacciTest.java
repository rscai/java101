package me.raycai.forkjoin;

import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * Created by kkppccdd on 2016/12/29.
 */
public class FibonacciTest extends TestCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(FibonacciTest.class);


    @Test
    public void testCompute() throws Exception {
        final Fibonacci testObject = new Fibonacci(10);

        final ForkJoinPool pool = new ForkJoinPool();
        final Future<Integer> result = pool.submit(testObject);

        LOGGER.info(result.get() + "");

    }

}
