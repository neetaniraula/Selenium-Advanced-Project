package ProductTestPractice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateProductWithExcelSheetTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		//Get Java representation object of physical file
		FileInputStream fs = new FileInputStream("./resources/commondata.properties");
		Properties pf = new Properties();
		pf.load(fs);
		//read the data form it with the help of keys
		String BROWSER = pf.getProperty("browser");
		String URL = pf.getProperty("url");
		String USERNAME = pf.getProperty("username");
		String PASSWORD = pf.getProperty("password");
		
		//Generate random number
		Random rn = new Random();
		int ranNumber = rn.nextInt(1000);
		
		
		//Read data from excel file
		
		FileInputStream fs1 = new FileInputStream("./resources/testdata.xlsx");
		
		//Open excel in read mode
		Workbook wb = WorkbookFactory.create(fs1);
		String qty = wb.getSheet("Products").getRow(1).getCell(1).getStringCellValue();
		String pName = wb.getSheet("Products").getRow(1).getCell(2).getStringCellValue()+ranNumber;
		String pUnit = wb.getSheet("Products").getRow(1).getCell(3).getStringCellValue();
					
		//Select Driver
		WebDriver driver = null;
		if(BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		
		//Application login
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[@type = 'submit']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text() = 'Products']")).click();
		Thread.sleep(3000);
		//Add Product
		driver.findElement(By.xpath("//span[text() = 'Add Product']")).click();
		WebElement quantityField = driver.findElement(By.xpath("//input[@name = 'quantity']"));
		quantityField.clear();
		quantityField.sendKeys(qty);
		driver.findElement(By.xpath("//input[@name = 'productName']")).sendKeys(pName +ranNumber);
		WebElement unitPrice = driver.findElement(By.xpath("//input[@name = 'price']"));
		unitPrice.clear();
		unitPrice.sendKeys(pUnit);
		
		//Select dropdown
		WebElement categoryDD = driver.findElement(By.name("productCategory"));
		Select dropDown = new Select(categoryDD);
		dropDown.selectByIndex(2);
		WebElement vendorID = driver.findElement(By.name("vendorId"));
		Thread.sleep(5000);
		Select vendorDD = new Select(vendorID);
		vendorDD.selectByIndex(2);
		driver.findElement(By.xpath("//button[@type ='submit']")).click();
		Thread.sleep(5000);
		//Verify success message
		WebElement successMessage = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
		wait.until(ExpectedConditions.visibilityOf(successMessage));
		
		String smessage = successMessage.getText();
		System.out.println(smessage);
		
		if(smessage.contains(pName)) {
			System.out.println("product created successfuly");
		}else {
			System.out.println("product wasn't created");
		}
		//Writing back in excel 
					
				Cell cell = wb.getSheet("Products").getRow(1).createCell(4);
				cell.setCellType(CellType.STRING);
				cell.setCellValue(smessage);
				
				FileOutputStream writingExcel = new FileOutputStream("./resources/testdata.xlsx");
				wb.write(writingExcel);
				wb.close();
				
				
				driver.quit();
				
		
		
	}

}
