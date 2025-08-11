package ProductTestPractice;

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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateProductWithMandatoryFieldTest {
	
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
		//Get data from excel file
		FileInputStream fs1 = new FileInputStream("./resources/Product.xlsx");
		//Open excel in read mode
		Workbook wb = WorkbookFactory.create(fs1);
		String qty = wb.getSheet("Products").getRow(1).getCell(1).getStringCellValue();
		String pName = wb.getSheet("Products").getRow(1).getCell(2).getStringCellValue();
		Thread.sleep(3000);
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
		driver.findElement(By.xpath("//a[text() = 'Products']")).click();
		Thread.sleep(3000);
		//Add Product
		driver.findElement(By.xpath("//span[text() = 'Add Product']")).click();
		WebElement quantityField = driver.findElement(By.xpath("//input[@name = 'quantity']"));
		quantityField.clear();
		quantityField.sendKeys(qty);
		driver.findElement(By.xpath("//input[@name = 'productName']")).sendKeys(pName);
		WebElement unitPrice = driver.findElement(By.xpath("//input[@name = 'price']"));
		unitPrice.clear();
		unitPrice.sendKeys(pUnit);
		//Select dropdown
		WebElement categoryDD = driver.findElement(By.name("productCategory"));
		Select dropDown = new Select(categoryDD);
		dropDown.selectByIndex(1);
		WebElement vendorID = driver.findElement(By.name("vendorId"));
		Select vendorDD = new Select(vendorID);
		vendorDD.selectByIndex(1);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@type ='submit']")).click();
		Thread.sleep(3000);
		//Verify success message
		WebElement successMessage = driver.findElement(By.xpath("//div[text()= 'Product "+pName+" Successfully Added']"));
		String smessage = successMessage.getText();
		System.out.println(smessage);
				
							}

		}
