package com.laughing2b.util.general;
import java.lang.reflect.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
/**
 * Describe: 
 */
public class GsonUtil {
    private static Logger logger = LoggerFactory.getLogger(GsonUtil.class);
    
    /**
     * List<Object1> object1s = convertGsonToObject(gson, new TypeToken<List<Object1>>(){}.getType());
     * Object2 object2 = convertGsonToObject(gson, new TypeToken<Object2>(){}.getType());
     * Object3 object3 = convertGsonToObject(gson, Object3.class);
     */
    @SuppressWarnings("unchecked")
    public static <T> T convertGsonToObject(String json, Type type) {
        T t = null;
        if (json == null) {
            return null;
        }
        try {
            t = (T) new Gson().fromJson(json, type);
        } catch (Exception e) {
            logger.error("convertGsonToObject exception :" + json, e);
        }
        return t;
    }
    
    public static String convertObjectToGson(Object object) {
        if (object == null) {
            return null;
        }
        
        try {
            return new Gson().toJson(object);
        } catch (Exception e) {
            logger.error("convertObjectToGson exception :", e);
        }
        
        return null;
    }
    
}