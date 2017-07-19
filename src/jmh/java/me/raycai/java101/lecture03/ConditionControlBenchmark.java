package me.raycai.java101.lecture03;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;

@State(Scope.Thread)
public class ConditionControlBenchmark {
    List<Integer> arrayList;
    int[] array;
    Random random;
    
    List<Float> unorderedList;
    List<Float> orderedList;
    
    OrderUtil orderUtil;

    @Setup(Level.Trial)
    public void init() {
        orderUtil = new OrderUtil();
        
        final int size = 1000;
        random = new Random();
        unorderedList=new ArrayList<>(size);
        
        
        for (int i = 1; i <= size; i++) {
            int randomNumber = random.nextInt() % 100;
            unorderedList.add((float)randomNumber);
        }
        
        orderedList = new ArrayList<>(unorderedList);
        Collections.sort(orderedList);
    }

    @Benchmark
    public void inputOrdered() {
        for(Float input:orderedList){
            orderUtil.calculateTotalPrice(input,2);
        }
    }

    @Benchmark
    public void inputUnordered() {
        for(Float input:unorderedList){
            orderUtil.calculateTotalPrice(input,2);
        }
    }

}
