package campaignTestPractice;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import fileUtility.ExcelFileUtility;
import fileUtility.PropertyFileUtility;

public class CreateCampaignUsingUtility {

	public static void main(String[] args) throws IOException, InterruptedException {
		
	//create the object of Preoperty of file utility
	PropertyFileUtility pf = new PropertyFileUtility();
		
	//get the values
	
	String BROWSER = pf.toGetDataFromPropertiesFile("browser");
	String URL = pf.toGetDataFromPropertiesFile("url");
	String USERNAME = pf.toGetDataFromPropertiesFile("username");
	String PASSWORD = pf.toGetDataFromPropertiesFile("password");
		
	//Excel file
	//Create Excel file Utility
	
	ExcelFileUtility ex = new ExcelFileUtility();
	String campaignName = ex.toReadTheDataFromExcel("Campaigns", 1, 1);
	String targetSize = ex.toReadTheDataFromExcel("Campaigns", 1, 2);
		
	WebDriver driver = null;	
	
	if(BROWSER.equalsIgnoreCase("chrome")) {
		driver = new ChromeDriver();
	}else if (BROWSER.equalsIgnoreCase("edge")) {
		driver= new EdgeDriver();
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
	
	driver.get(URL);
	driver.findElement(By.id("username")).sendKeys(USERNAME);
	driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
	driver.findElement(By.xpath("//button[@type = 'submit']")).click();
	driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
	Thread.sleep(3000);
	
	//create campaign with mandatory fields

	driver.findElement(By.name("campaignName")).sendKeys(campaignName);
	WebElement targSize = driver.findElement(By.name("targetSize"));
	targSize.clear();
	targSize.sendKeys(targetSize);
	driver.findElement(By.xpath("//button[@type = 'submit']")).click();
	

	//verify the success message
	WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
			
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(12));
	wait.until(ExpectedConditions.visibilityOf(toastMsg));
			
		String msg = toastMsg.getText();
			
		if(msg.contains("Pawar")) {
				System.out.println("campaign created successfully");
				
		}else {
				System.out.println("campaign is not created");
				
			}
	
		}

	}


