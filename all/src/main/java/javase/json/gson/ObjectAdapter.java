package javase.json.gson;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * @author tangxiaoshuang
 * @date 2018/7/17 9:55
 * @desc gson中null转""
 */
public class ObjectAdapter implements JsonSerializer<Object>, JsonDeserializer<Object> {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if(null == jsonElement.getAsJsonObject()) {
            return "";
        }

        System.out.println("deserialize");
        return jsonElement.getAsJsonObject();
    }

    @Override
    public JsonElement serialize(Object o, Type type, JsonSerializationContext jsonSerializationContext) {
        System.out.println("serialize");
        if(null == o) {
            return jsonSerializationContext.serialize("");
        }

        return jsonSerializationContext.serialize(o);
    }
}
