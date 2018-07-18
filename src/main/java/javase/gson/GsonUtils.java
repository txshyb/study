package javase.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {
    public static final Gson GSON_NUll = new GsonBuilder().registerTypeHierarchyAdapter(Object.class,new TestAdapter()).create();

    public static void main(String[] args) {
        Locale locale = new Locale();
        locale.setLatitude("ssss");
        String json = GSON_NUll.toJson(locale,Locale.class);
        System.out.println(json);
    }
}
