package javase.gson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import entity.Person;
import org.junit.Test;

/**
 * 对象中null转""
 */
public class JsonTest {

	public static String getJson(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		// null替换为""
		mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {

			@Override
			public void serialize(Object arg0, JsonGenerator arg1, SerializerProvider arg2)
					throws IOException, JsonProcessingException {
				arg1.writeString("");
			}
		});
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

	@Test
	public void test() {
		System.out.println(getJson(new TestObject()));// TestObject必须是POJO对象
	}

	@Test
    public void test2() {
            Locale locale = new Locale();
            locale.setLatitude("ssss");
            String json = getJson(locale);
            System.out.println(json);
    }
}

class TestObject {

	String name = "张三";

	String sex = null;

	Integer age = null;

	Person person;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
