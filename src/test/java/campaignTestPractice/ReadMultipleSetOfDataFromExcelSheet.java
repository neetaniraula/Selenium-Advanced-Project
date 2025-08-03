package campaignTestPractice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleSetOfDataFromExcelSheet {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {


		FileInputStream fs = new FileInputStream("./resources/testdataLoop.xlsx");
		
		Workbook wb = WorkbookFactory.create(fs);
		//Sheet sheet = wb.getSheet("Campaigns");
		
		int lastRowNumber = wb.getSheet("Campaigns").getLastRowNum();
		
		for(int i = 1; i <= lastRowNumber; i++) {
		String campName = wb.getSheet("Campaigns").getRow(1).getCell(1).getStringCellValue();
		System.out.println(campName);
		}

		wb.close();
		fs.close();
	}

}
