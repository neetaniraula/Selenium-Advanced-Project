package campaignTestPractice;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCampaignWithExpectedDate {

	public static void main(String[] args) throws IOException, ParseException, InterruptedException {		
				
		//Read the data from json File
		//Step 1.Create a java representation of physical file
				
		FileReader fr = new FileReader("./resources/commondata.json");
						
		//Step 2. create the object of JSONparser and use parse method to pass the object of physical file
		  JSONParser jp = new JSONParser();
		 Object javaObject = jp.parse(fr);
					     
		 //Step 3. Convert java object to Json by Downcasting
		JSONObject obj = (JSONObject)javaObject;
					    
		 //Step http://4.Read data using get()
		String BROWSER = obj.get("browser").toString();
		String URL = obj.get("url").toString();
		String USERNAME = obj.get("username").toString();
		String PASSWORD = obj.get("password").toString();		
						
		       WebDriver driver = null;
		       if (BROWSER.equals("chrome")) {
				driver = new ChromeDriver();
			} else if (BROWSER.equals("edge")) {
				driver = new EdgeDriver();
			}
			else if (BROWSER.equals("firefox")) {
				driver = new FirefoxDriver();
			}					   
			   
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
				
				driver.get(URL);
				driver.findElement(By.id("username")).sendKeys(USERNAME);
				driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				
				//Click on campaign link
				driver.findElement(By.linkText("Campaigns")).click();	
					
			   //Create campaign with Mandatory fields
			   driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
			   driver.findElement(By.name("campaignName")).sendKeys("jati1111");
				WebElement targSize = driver.findElement(By.name("targetSize"));
				targSize.clear();
				targSize.sendKeys("13323");
				
				//Get the date after 30 days
				//Create the object of date to get the todays date
				Date date = new Date();
						
				//Format Date 
				SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
				sim.format(date);
				Calendar cal = sim.getCalendar();
						
				cal.add(Calendar.DAY_OF_MONTH,30);
				String expectedDate = sim.format(cal.getTime());
				Thread.sleep(3000);			
				driver.findElement(By.name("expectedCloseDate")).sendKeys("expectedDate");
				Thread.sleep(3000);		
				driver.findElement(By.xpath("//button[@type='submit']")).click();
						
				//Verify the succesfull message
				WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
						
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.visibilityOf(toastMsg));
												
				String msg = toastMsg.getText();
				System.out.println(msg);
			}

	
}
		 



