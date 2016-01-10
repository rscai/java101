package me.raycai.tdd;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kkppccdd on 2016/1/10.
 */
public class Column1Dao {
    private static Column1Dao instance;

    public static Column1Dao getInstance(){
        if(instance == null){
            synchronized (Column1Dao.class){
                if(instance ==null){
                    instance = new Column1Dao();
                }
            }
        }

        return instance;
    }

    private Column1Dao(){

    }

    public Map<String,String> loadMapping(){
        Map<String,String> codeMapping = new HashMap<>();
/*        codeMapping.put("0001","ISN");
        codeMapping.put("0002","CSP");
        codeMapping.put("098","IISN");
        codeMapping.put("1233","BBN");*/

        return codeMapping;
    }
}
