package ProductTestPractice;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadTheDataFromJasonFileTestPractice
{

	public static void main(String[] args) throws IOException, ParseException {
		//Create java representation file
		FileReader fr = new FileReader("./resources/commondata.json");
		//Create object on JsonParser and use parse method to pass the object of physical file
		JSONParser jp = new JSONParser();
		Object javaObject = jp.parse(fr);
		//convert java object to jSon object by downcasting
		JSONObject obj = (JSONObject)javaObject;
		//Read the data using get()
		String BROWSER = obj.get("browser").toString();
		obj.get("url").toString();
		System.out.println(BROWSER);
	}

}
