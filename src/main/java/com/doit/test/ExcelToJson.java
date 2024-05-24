package com.doit.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelToJson {

    public static void main(String[] args) {
        String excelFilePath = "D:\\文档\\t_business_jour.xlsx";
        String outPath = "D:\\test.json";
//        System.out.println("===============请输入Excel文件路径, 如: D:\\ExcelToJSON\\input.excel==================");
//        Scanner scanner = new Scanner(System.in);
//        String excelFilePath = scanner.nextLine();
//        System.out.println("===============请输入输出结果文件路径==================");
//        String outPath = scanner.nextLine();

        List<Map<String, Object>> jsonDataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
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
//            String jsonOutput = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonDataList);
//            System.out.println(jsonOutput);

            // 如果需要将JSON写入文件，取消下面的注释

            FileWriter writer = new FileWriter(outPath);

            for (Map<String, Object> stringObjectMap : jsonDataList) {
                writer.append(mapper.writeValueAsString(stringObjectMap));
                writer.write("\r\n");
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
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