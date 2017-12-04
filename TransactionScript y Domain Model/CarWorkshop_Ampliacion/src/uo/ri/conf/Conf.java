package uo.ri.conf;

import java.io.IOException;
import java.util.Properties;

public class Conf {

	private static final String CONFIG_FILE = "configuration.properties";

	private static Conf conf;
	private Properties properties;

	private Conf() {
		properties = new Properties();

		try {
			properties.load(Conf.class.getClassLoader().getResourceAsStream(CONFIG_FILE));
		} catch (IOException e) {
			System.err.println("Error in properties file load");
			throw new RuntimeException();
		}
	}

	public static String get(String key) {
		return getInstance().getProperty(key);
	}

	private String getProperty(String key) {
		String str = properties.getProperty(key);

		if (str == null) {
			throw new RuntimeException("Property not found ");
		}

		return str;
	}

	private static Conf getInstance() {
		if (conf == null) {
			conf = new Conf();
		}

		return conf;
	}

}
