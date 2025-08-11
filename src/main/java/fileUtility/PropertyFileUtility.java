package fileUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtility {
	public String toGetDataFromPropertiesFile(String key) throws IOException {
		
		FileInputStream fs = new FileInputStream("./resources/commondata.properties");
		
		Properties pp = new Properties();
		pp.load(fs);
		
		String value = pp.getProperty(key);
		return value;
		
		
	}

	

	}

