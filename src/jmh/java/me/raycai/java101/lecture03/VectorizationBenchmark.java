package me.raycai.java101.lecture03;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@State(Scope.Thread)
public class VectorizationBenchmark {
    int[] input;
    List<Integer> linkedListInput;
    List<Integer> arrayListInput;
    Random random;
    @Setup(Level.Trial)
    public void init() {
        final int size = 4*10000;
        random = new Random();
        input = new int[size];
        linkedListInput = new LinkedList<>();
        arrayListInput = new ArrayList<>(size);
        for(int i=0;i<size;i++){
            int value = random.nextInt()%100;
            input[i] = value;
            linkedListInput.add(value);
            arrayListInput.add(value);
        }
    }
    @Benchmark
    public void sequentialSum(){
        int sum =0;
        for(int i=0;i<input.length;i++){
            sum = sum + input[i];
        }
    }
    @Benchmark
    public void unrollingSum(){
        final int unrollingBatch = 4;
        int sum = 0;
        for(int i=0;i<input.length;i+=unrollingBatch){
            sum = sum + input[i];
            sum = sum + input[i+1];
            sum = sum + input[i+2];
            sum = sum + input[i+3];
        }
    }
    /*
    @Benchmark
    public void linkedListInput(){
        int[] out = new int[linkedListInput.size()];
        for(int i = 0; i< linkedListInput.size(); i++){
            out[i] = linkedListInput.get(i)*3;
        }
    }
    @Benchmark
    public void arrayListInput(){
        int[] out = new int[arrayListInput.size()];
        for(int i = 0; i< arrayListInput.size(); i++){
            out[i] = arrayListInput.get(i)*3;
        }
    }
    @Benchmark
    public void arrayInput(){
        int[] out = new int[input.length];
        for(int i=0;i<input.length;i++){
            out[i] = input[i]*3;
        }
    }
    */
}
