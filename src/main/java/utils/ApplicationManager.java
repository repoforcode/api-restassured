package utils;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationManager {

	public static String getValueOfProperty(String key) {
		Properties prop = new Properties();
		try {
			InputStream inputStream = ApplicationManager.class.getClassLoader()
					.getResourceAsStream("config.properties");
			prop.load(inputStream);

			return prop.getProperty(key);

		} catch (Exception e) {
			e.getMessage();
			return "Issue while getting value for key ::: " + key;
		}

	}

}
