package me.raycai.tdd;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kkppccdd on 2016/1/10.
 */
public class Column3Dao {
    private static Column3Dao instance;

    public static Column3Dao getInstance(){
        if(instance ==null){
            synchronized (Column3Dao.class){
                if(instance==null){
                    instance = new Column3Dao();
                }
            }
        }
        return instance;
    }

    private Column3Dao(){

    }

    public Map<String,String> loadMapping(){
        HashMap<String,String> codeMapping = new HashMap<>();
/*        codeMapping.put("X2134","NAM");
        codeMapping.put("Q53221","APAC");
        codeMapping.put("PS0","EMAR");
        codeMapping.put("SA","JP");*/
        return codeMapping;
    }
}
