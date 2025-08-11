package ProductTestPractice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeadsTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		FileInputStream fs = new FileInputStream("./resources/commondata.properties");
		Properties pf = new Properties();
		pf.load(fs);
		
		String BROWSER = pf.getProperty("browser");
		String URL = pf.getProperty("url");
		String USERNAME = pf.getProperty("username");
		String PASSWORD = pf.getProperty("password");
		
		//Get data from excel file
		FileInputStream fs1 = new FileInputStream("./resources/testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fs1);
		
		String lStatus = wb.getSheet("Lead").getRow(1).getCell(0).getStringCellValue();
		String lName = wb.getSheet("Lead").getRow(1).getCell(1).getStringCellValue();
		String comp = wb.getSheet("Lead").getRow(1).getCell(2).getStringCellValue();
		String lSource = wb.getSheet("Lead").getRow(1).getCell(3).getStringCellValue();
		String indust = wb.getSheet("Lead").getRow(1).getCell(4).getStringCellValue();
		String phone = wb.getSheet("Lead").getRow(1).getCell(5).getStringCellValue();
		
		//Select driver
		WebDriver driver = null;
		if(BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//Login to application 
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[@type = 'submit']")).click();
		driver.findElement(By.xpath("//a[text() = 'Products']")).click();
		Thread.sleep(3000);
		//Click on leads button
		driver.findElement(By.xpath("//a[text() = 'Leads']")).click();
		driver.findElement(By.xpath("//span[text() ='Create Lead']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name = 'leadStatus']")).sendKeys(lStatus);
		driver.findElement(By.xpath("//input[@name = 'name']")).sendKeys(lName);
		driver.findElement(By.xpath("//input[@name = 'company']")).sendKeys(comp);
		driver.findElement(By.xpath("//input[@name = 'leadSource']")).sendKeys(lSource);
		driver.findElement(By.xpath("//input[@name = 'industry']")).sendKeys(indust);
		driver.findElement(By.xpath("//input[@name = 'phone']")).sendKeys(phone);
		Thread.sleep(3000);
		//clicking on campaigns
		driver.findElement(By.xpath("//label[text()='Campaign']/..//button[@type ='button'][1]")).click();
		Thread.sleep(3000);
		//Switching window
		String mainWindow = driver.getWindowHandle();
		Thread.sleep(3000);
		////switno sir
		//ch to new window
		for(String handle :driver.getWindowHandles()) {
			if(!handle.equals(mainWindow)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class ='select-btn']")).click();
		Thread.sleep(3000);
		driver.switchTo().window(mainWindow);
		
		//Click on submit to create lead
		driver.findElement(By.xpath("//button[@type = 'submit']")).click();
		//Print success message
		WebElement successMsg = driver.findElement(By.xpath("//div[@class = 'Toastify__toast Toastify__toast--success']"));
		String msg = successMsg.getText();
		System.out.println(msg);
		//Delete lead
		driver.findElement(By.xpath("//i[@title ='Delete']")).click();
		//switch to pop up window
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
		WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value ='Delete']")));
		//print delete success message
		deleteButton.click();
		//Thread .sleep(3000);
		//WebElement deleteMsg = driver.findElement(By.xpath("//div[@#class = Toastify__toast-body"));
		//Thread.sleep(3000);
		//String dMsg = deleteMsg.getText();
		//Thread.sleep(3000);
		//System.out.println(dMsg);
		
driver.quit();
	}
	 
	

}
