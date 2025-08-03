package campaignTestPractice;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateCampaignWithMandatoryFieldsTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		//properties file
		//get java representation object of physical file
		FileInputStream fs = new FileInputStream("./resources/commondata.properties");
		 //Create object of Properties file
		Properties pf = new Properties();
		//load all keys
		pf.load(fs);
		//Read the data from it with the help of keys
		String BROWSER = pf.getProperty("browser");
		String URL = pf.getProperty("url");
		String USERNAME = pf.getProperty("username");
		String PASSWORD = pf.getProperty("password");
		
		//excel file
				//Create java representation object of physical file
					FileInputStream fs1 =new FileInputStream("./resources/testdata.xlsx");
					
					//open excel in read mode
					Workbook wb = WorkbookFactory.create(fs1);
					
					String campaignName = wb.getSheet("Campaigns").getRow(1).getCell(1).getStringCellValue();
					System.out.println(campaignName);
					
					String targetSize =  wb.getSheet("Campaigns").getRow(1).getCell(2).getStringCellValue();
					System.out.println(targetSize);
			
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
		
		////create campaign with mandatory fields
		//driver.findElement(By.xpath("//span[text()= 'Create Campaign']")).click();
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
