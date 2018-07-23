package javase.json.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Person;
import javase.json.Locale;

public class GsonUtils {

    public static void main(String[] args) {

        Gson GSON_NUll = new GsonBuilder().registerTypeAdapter(Integer.class,new IntegerDefaultAdapter()).addSerializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
             //   System.out.println("shouldSkipField");
                if(Person.class.equals(f.getDeclaredClass())) {
                    return true;  //不序列化Person这个属性
                }

                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
            //    System.out.println("shouldSkipClass");
                return false;
            }
        }).serializeNulls().create();
        Locale locale = new Locale();
        locale.setLatitude("ssss");
        locale.setLongitude(null);
        String json = GSON_NUll.toJson(locale,Locale.class);
        System.out.println(json);



        String str = "{\"latitude\":\"ssss\" ,\"longitude\":\"\"}";
        Locale locale1 = GSON_NUll.fromJson(str,Locale.class);
    }
}
