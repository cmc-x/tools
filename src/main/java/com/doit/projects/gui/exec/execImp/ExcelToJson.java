package com.doit.projects.gui.exec.execImp;

import com.doit.projects.gui.exec.ExecInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 将Excel 转换成JSON
 *
 * @author: yexiaomin
 * @date: 2024年05月04日 17:00
 */
public class ExcelToJson implements ExecInterface {
    private static final String serverName = "ExcelToJson";

    @Override
    public void exec(String sourceFile, String targetFile) {

        List<Map<String, Object>> jsonDataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(sourceFile));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // 假设只读取第一个sheet
            Row headerRow = sheet.getRow(0); // 第一行是标题行

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) { // 从第二行开始读取数据
                Row dataRow = sheet.getRow(rowIndex);
                Map<String, Object> rowData = new LinkedHashMap<>();
                for (int cellIndex = 0; cellIndex < headerRow.getLastCellNum(); cellIndex++) {
                    Cell headerCell = headerRow.getCell(cellIndex);
                    Cell dataCell = dataRow.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    rowData.put(getCellValueAsString(headerCell), getCellValueAsString(dataCell));
                }
                jsonDataList.add(rowData);
            }


            ObjectMapper mapper = new ObjectMapper();

            // 如果需要将JSON写入文件，取消下面的注释

            FileWriter writer = new FileWriter(targetFile);

            for (Map<String, Object> stringObjectMap : jsonDataList) {
                writer.append(mapper.writeValueAsString(stringObjectMap));
                writer.write("\r\n");
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServerName() {
        return serverName;
    }

    private static String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue().toString();
                } else {
                    double numericCellValue = cell.getNumericCellValue();

                    String s = String.valueOf(numericCellValue);
                    if(s.endsWith(".0")){
                        return s.split("[.]")[0];
                    }
                    return s;
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
}
