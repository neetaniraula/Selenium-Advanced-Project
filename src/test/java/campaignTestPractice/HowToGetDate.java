package campaignTestPractice;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HowToGetDate {

	public static void main(String[] args) {
		//Create the object of date import freom java.util
		Date date = new Date();
		//Format date
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		sim.format(date);
		Calendar cal = sim.getCalendar();
		
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String expectedDate = sim.format(cal.getTime());
		System.out.println(expectedDate);
	}

}
