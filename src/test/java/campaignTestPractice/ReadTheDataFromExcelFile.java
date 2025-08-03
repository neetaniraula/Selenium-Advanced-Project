package campaignTestPractice;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadTheDataFromExcelFile {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		//Excel file
		//Step 1- Create java representation object of physical file
		FileInputStream fs1 = new FileInputStream("./resources/testdata.xlsx");
		
		//Open excel in read mode
		Workbook wb = WorkbookFactory.create(fs1);
		
		//get control of sheet
		Sheet sheet = wb.getSheet("Campaigns");
		
		// get control of row
		Row rw = sheet.getRow(1);
		
		//get control of cell		
		Cell cl = rw.getCell(1);
		
		//Read the data from cell 
		
		String campaignName = cl.getStringCellValue();
		System.out.println(campaignName);
		
		String targSize = sheet.getRow(1).getCell(2).getStringCellValue();
		
		System.out.println(targSize);
		
		
	}

}
