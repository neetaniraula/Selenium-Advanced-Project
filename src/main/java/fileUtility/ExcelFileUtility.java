package fileUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.support.PageFactory;

public class ExcelFileUtility {
	public String toReadTheDataFromExcel(String sheet,int rowNum,int cellNum) throws EncryptedDocumentException, IOException {
		
		FileInputStream fs = new FileInputStream("./resources/testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fs);
		String data = wb.getSheet(sheet).getRow(rowNum).getCell(cellNum).getStringCellValue();
		wb.close();
		return data;
	}
	public int toGetTheRowCount(String sheet) throws EncryptedDocumentException, IOException {
		FileInputStream fs = new FileInputStream("./resources/testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fs);
		int lastRowNum = wb.getSheet(sheet).getLastRowNum();
		return lastRowNum;		
	}
	public List<String> toReadMultiSetOfData(String sheet,int lastRowNum,int cellNum) throws EncryptedDocumentException, IOException {
		List<String> ls = new ArrayList<String>();
		FileInputStream fs = new FileInputStream("./resources/testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fs);
		
		for(int i = 1; i <=lastRowNum ; i++) {
		String data = wb.getSheet(sheet).getRow(i).getCell(cellNum).getStringCellValue();
		ls.add(data);
		}
		return ls;
	}

}
