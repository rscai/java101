package me.raycai.tdd;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kkppccdd on 2016/1/10.
 */
public class Column2Dao {
    private static Column2Dao instance;

    public static Column2Dao getInstance(){
        if(instance==null){
            synchronized (Column2Dao.class){
                if(instance==null){
                    instance = new Column2Dao();
                }
            }
        }
        return instance;
    }

    private Column2Dao(){

    }

    public Map<String,String> loadMapping(){
        HashMap<String,String> codeMapping = new HashMap<>();
/*        codeMapping.put("1002","212234");
        codeMapping.put("10934","2134");
        codeMapping.put("876","8799");
        codeMapping.put("09877","09873");*/

        return codeMapping;
    }
}
