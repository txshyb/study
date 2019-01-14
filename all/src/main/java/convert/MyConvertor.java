package convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MyConvertor implements Converter<String, User> {

	@Override
	public User convert(String source) {
		String[] values = source.split(",");
		Integer id = Integer.valueOf(values[0]);
		User user = new User(id,values[1],values[2]);
		return user;
	}
}
