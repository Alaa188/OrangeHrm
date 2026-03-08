package Data;

import org.apache.poi.xssf.usermodel.*;
import java.io.*;

public class ExcelReader {

    public Object[][] getExcelData() throws IOException {

        String filePath = System.getProperty("user.dir") + "/src/test/java/Data/UserData.xlsx";
        FileInputStream fis = new FileInputStream(filePath);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][cols];

        for(int i = 1; i <= rows; i++){
            XSSFRow row = sheet.getRow(i);

            for(int j = 0; j < cols; j++){
                data[i-1][j] = row.getCell(j).toString();
            }
        }

        workbook.close();
        return data;
    }
}