package utils.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UtilExcel {
//	public static void main(String[] args) {
//		String filePath = "/home/sudheert/Sudheer/JavaFileHandling/Test1.xlsx";
//		String sheetName = "Sheet1";
//		XSSFSheet mySheet = openExcelFile(filePath, sheetName);
//		int rowCount = getRowCount(mySheet);
//		int colCount = getColCount(mySheet);
//		for (int i = 0; i < rowCount; i++) {
//			for(int j =0; j<colCount; j++) {
//				System.out.print(getDataFromCell(mySheet,i,j));
//			}
//			System.out.println();
//		}
//	}
//	
	XSSFSheet sheet = null;
	public UtilExcel(String filePath, String sheetName) {
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(filePath);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = workbook.getSheet(sheetName);
	}
	
	public int getRowCount() {
		int rowCount = sheet.getLastRowNum()+1;
//		System.out.println("no.of Row: "+rowCount);
		return rowCount;
	}
	
	public int getColCount() {
		int colCount = sheet.getRow(0).getLastCellNum();
//		System.out.println("no.of Col: " + colCount);
		return colCount;
	}
	public String getDataFromCell(int rowNum, int colNum) {
		String data = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
//		System.out.print(data+" | ");
		return data;
	}

}
