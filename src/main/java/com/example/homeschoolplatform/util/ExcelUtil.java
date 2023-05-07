package com.example.homeschoolplatform.util;

import com.example.homeschoolplatform.entity.Grades;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    public static List<Grades> readGradesFromExcel(InputStream inputStream) throws Exception {
        List<Grades> gradesList = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        // Use getLastRowNum() instead of getPhysicalNumberOfRows()
        int numberOfRows = sheet.getLastRowNum();
        for (int i = 1; i <= numberOfRows; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                Grades grades = new Grades();

                // Use safer methods to get cell values
                grades.setUserId((int) getCellValueAsDouble(row.getCell(0)));
                grades.setSubject(getCellValueAsString(row.getCell(1)));
                grades.setScore(BigDecimal.valueOf(getCellValueAsDouble(row.getCell(2))));
                grades.setTerm(getCellValueAsString(row.getCell(3)));

                gradesList.add(grades);
            }
        }
        workbook.close();
        return gradesList;
    }

    private static double getCellValueAsDouble(Cell cell) {
        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        }
        return 0;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell != null && cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        }
        return "";
    }
}
