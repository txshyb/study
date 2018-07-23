package javase.json.gson;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * @author tangxiaoshuang
 * @date 2018/4/13 15:23
 * json转对象时空字符串转Integer适配
 */
public  class IntegerDefaultAdapter implements JsonDeserializer<Integer> {

    @Override
    public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        System.out.println("deserialize");
        try {
            if (json.getAsString().equals("") || json.getAsString().equals("null")) {//定义为int类型,如果后台返回""或者null,则返回null
                return null;
            }
        } catch (Exception ignore) {
        }
        try {
            return json.getAsInt();
        } catch (NumberFormatException e) {
            throw new JsonSyntaxException(e);
        }
    }
        //如果序列化的对象里的相应属性（此处为Integer）是null  是不会走这个方法里的，所有可以不要它
        //因此gson还未找到方法把null序列化成“”的方法，要有这操作，见jackson
//    @Override
//    public JsonElement serialize(Integer src, Type typeOfSrc, JsonSerializationContext context) {
//        System.out.println("serialize");
//        return new JsonPrimitive(src);
//    }
}
