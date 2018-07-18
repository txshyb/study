package javase.gson;

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
        return jsonElement.getAsJsonObject();
    }

    @Override
    public JsonElement serialize(Object o, Type type, JsonSerializationContext jsonSerializationContext) {
        if(null == o) {
            return jsonSerializationContext.serialize("");
        }
        return jsonSerializationContext.serialize(o);
    }
}
