package me.raycai.tdd;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ray Cai on 2016/1/10.
 */
public class Column4Dao {
    private static Column4Dao instance;
    public static Column4Dao getInstance(){
        if(instance ==null){
            synchronized (Column4Dao.class){
                if(instance==null){
                    instance = new Column4Dao();
                }
            }
        }
        return instance;
    }

    private Column4Dao(){
    }
    public Map<String,String> loadMapping(){
        Map<String,String> codeMapping = new HashMap<>();
/*        codeMapping.put("O","OPEN");
        codeMapping.put("OC","OPEN_CLOSE");
        codeMapping.put("BMCY","YIELD");
        codeMapping.put("OP","OPEN_YIELD");*/
        return codeMapping;
    }
}
