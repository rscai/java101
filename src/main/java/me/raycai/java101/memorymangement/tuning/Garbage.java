package me.raycai.java101.memorymangement.tuning;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by kkppccdd on 2017/2/4.
 */
public class Garbage {
    private List<String> ints;
    
    public Garbage(final int size){
        ints = new ArrayList<String>(size);
        for(int i=0;i<size;i++){
            ints.add(UUID.randomUUID().toString());
        }
    }
}
