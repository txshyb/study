package javase.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class TestAdapter extends TypeAdapter<Locale> {
    @Override
    public void write(JsonWriter jsonWriter, Locale o) throws IOException {
        System.out.println(o);
    }

    @Override
    public Locale read(JsonReader jsonReader) throws IOException {
        System.out.println("read");
        return null;
    }
}
