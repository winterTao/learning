package com.tao.read;

import com.tao.util.ExcelUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author DongTao
 * @since 2020-03-10
 */
public class ReadExcel {

    public static void main(String[] args) throws Exception {

        String path = "/Users/tao/Downloads/2.xlsx";

        Workbook workbook = ExcelUtils.readWorkbook(path);

        Sheet sheet = workbook.getSheetAt(0);

        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            List<String> rowResult = new ArrayList<>();
            Row row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                rowResult.add(cell.getStringCellValue());
            }
            result.add(rowResult);
        }

        System.out.println(result);
    }

}
