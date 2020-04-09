package com.tao.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidParameterException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author DongTao
 * @since 2019-05-20
 */
@Slf4j
public class ExcelUtils {

    /**
     * @param filePath The path of the Excel file
     * @return {@code true} excel version is 2003 {@code false} excel version isn't 2003
     */
    public static boolean isExcel2003(String filePath) {

        return filePath.matches("^.+\\.(?i)(xls)$");

    }

    /**
     * @param filePath The path of the Excel file
     * @return {@code true} excel version is 2007 {@code false} excel version isn't 2007
     */
    public static boolean isExcel2007(String filePath) {

        return filePath.matches("^.+\\.(?i)(xlsx)$");

    }

    /**
     * 验证excel
     */
    public static void validateExcel(String filePath) throws InvalidParameterException {

        /* 检查文件名是否为空或者是否是Excel格式的文件 */
        if (!isExcel2003(filePath) && !isExcel2007(filePath)) {
            throw new InvalidParameterException(
                    "the param of sourcePath : " + filePath + " is wrong ");
        }

        /* 检查文件是否存在 */
        File file = new File(filePath);
        if (!file.exists()) {
            throw new InvalidParameterException(
                    "the param of sourcePath : " + filePath + " is wrong ");
        }

    }

    public static Workbook readWorkbook(String filePath) throws IOException {
        validateExcel(filePath);

        Workbook wb;
        File file = new File(filePath);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        if (isExcel2003(filePath)) {
            wb = new HSSFWorkbook(in);
        } else {
            wb = new XSSFWorkbook(in);
        }

        return wb;
    }
}
