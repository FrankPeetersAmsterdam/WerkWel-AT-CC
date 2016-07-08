package utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;

	/*
	 * Set the File path, open Excel file
	 * @params - Excel Path and Sheet Name
	 */
	public static void setExcelFile(String path, String sheetName) throws Exception {
		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(path);

			// Access the excel data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
		} catch (Exception e) {
			throw (e);
		}
	}
    /*
     * Elk blok aan testdata wordt voorafgegaan door een regel met daarin een cel met de tabelnaam
     * en afgesloten door een regel met daarin een cel met de tabelnaam.
     * Onderstaand een voorbeeld van een tabel met drie testgevallen.
     * De kolomtitels "login" en "password" spelen geen rol in de utility.
     * 
     * tablename login          password
     * 			 testwaarde 1   testwaarde 2
     * 			 testwaarde 1   testwaarde 2
     * 			 testwaarde 1   testwaarde 2
     * 										 tablename
     */
	public static String[][] getTestData(String tableName) {
		String[][] testData = null;

		try {
			XSSFCell[] boundaryCells = findCells(tableName);
			XSSFCell startCell = boundaryCells[0];
			
			XSSFCell endCell = boundaryCells[1];
			
			int startRow = startCell.getRowIndex() + 1;
			int endRow = endCell.getRowIndex() - 1;
			int startCol = startCell.getColumnIndex() + 1;
			int endCol = endCell.getColumnIndex() - 1;

			testData = new String[endRow - startRow + 1][endCol - startCol + 1];

			for (int i=startRow; i<endRow+1; i++) {
				for (int j=startCol; j<endCol+1; j++) {
					if (ExcelWSheet.getRow(i).getCell(j)!= null){
					testData[i-startRow][j-startCol] = ExcelWSheet.getRow(i).getCell(j).getStringCellValue();
					} else {
						testData[i-startRow][j-startCol]="";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testData;
	}

	public static XSSFCell[] findCells(String tableName) {
		String pos = "begin";
		XSSFCell[] cells = new XSSFCell[2];

		for (Row row : ExcelWSheet) {
			for (Cell cell : row) {
				if (tableName.equals(cell.getStringCellValue())) {
					if (pos.equalsIgnoreCase("begin")) {
						cells[0] = (XSSFCell) cell;
						pos = "end";
					} else {
						cells[1] = (XSSFCell) cell;
					}
				}
			}
		}
		return cells;
	}
}