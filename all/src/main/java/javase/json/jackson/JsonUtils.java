package javase.json.jackson;


import javase.json.Locale;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    static{
        mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {

            @Override
            public void serialize(Object arg0, JsonGenerator arg1, SerializerProvider arg2)
                    throws IOException {
                arg1.writeString("");
            }
        });
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
    /**
     * obj中的null转为""
     * @param obj
     * @return
     */
    public static String getJsonForNull2String(Object obj) {
        // null替换为""
        String str = null;
        try {
            str = mapper.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void main(String[] args) {
        Locale locale = new Locale();
        locale.setDate(new Date());
        System.out.println(getJsonForNull2String(locale));
    }
}
