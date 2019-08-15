package com.zcj.mybatisplus.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelUtils {

    public static List<Map<String, Object>> parseExcel(File file) throws InvalidFormatException, FileNotFoundException, IOException {
        return parseExcel(file, 0);
    }

    public static List<Map<String, Object>> parseExcel(File file, int headRowNum) throws InvalidFormatException, FileNotFoundException, IOException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Workbook book = WorkbookFactory.create(new FileInputStream(file));
        if (book != null) {
            //解析公式，取值
            //FormulaEvaluator evaluator = book.getCreationHelper().createFormulaEvaluator();
            Map<String, Object> map;
            Sheet sheet = book.getSheetAt(0);
            Row header = sheet.getRow(headRowNum);
            Row row;
            Cell cell;
            String key;
            double num;
            boolean isEmptyRow;
            int firstCellNum = header.getFirstCellNum();
            int lastCellNum = header.getLastCellNum();
            int i = sheet.getFirstRowNum() + 1 + headRowNum;
            int j = 0;
            try {
                for (int len = sheet.getLastRowNum(); i <= len; i++) {
                    row = sheet.getRow(i);
                    if (row == null) {//空行
                        continue;
                    }
                    isEmptyRow = true;
                    map = new HashMap<String, Object>();
                    map.put("rowNum", String.valueOf(i + 1));//行号
                    for (j = firstCellNum; j <= lastCellNum; j++) {
                        if (row.getLastCellNum() >= j) {
                            if (header.getCell(j) == null) {//某列标题为空，则其前一列当做最后一列
                                lastCellNum = j - 1;
                                break;
                            }
                            key = header.getCell(j).getStringCellValue();
                            cell = row.getCell(j);
                            if (cell != null) {
                                switch (cell.getCellType()) {
                                    case Cell.CELL_TYPE_BLANK:
                                        map.put(key, "");
                                        break;
                                    case Cell.CELL_TYPE_NUMERIC:
                                        num = cell.getNumericCellValue();
                                        if (HSSFDateUtil.isCellDateFormatted(cell)) {//日期
                                            map.put(key, HSSFDateUtil.getJavaDate(num));
                                        } else {//数值
                                            map.put(key, BigDecimal.valueOf(num));
                                        }
                                        ;
                                        isEmptyRow = false;//只要有一个字段不为空，就不算空行
                                        break;
                                    case Cell.CELL_TYPE_STRING:
                                        map.put(key, cell.getStringCellValue());
                                        isEmptyRow = false;
                                        break;
                                    case Cell.CELL_TYPE_FORMULA://公式，取结果
										/*jar包版本问题，不能用FormulaEvaluator，先用try-catch，这样比较影响性能，最好不要用公式
										evaluator.evaluateFormulaCell(cell);
										cellValue = evaluator.evaluate(cell);
									    switch(cellValue.getCellType()) {
										    case Cell.CELL_TYPE_BLANK : 
												map.put(key, "");
												break;
											case Cell.CELL_TYPE_STRING :
									    		map.put(key, cellValue.getStringValue());
									    		isEmptyRow = false;
									    		break;
								    		case Cell.CELL_TYPE_NUMERIC : 
								    			if(HSSFDateUtil.isCellDateFormatted(cell)) {//日期
													map.put(key, HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
												} else {
													map.put(key, BigDecimal.valueOf(cellValue.getNumberValue()));
												}
								    			isEmptyRow = false;
												break;
									    }*/
                                        try {//尝试解析成字符串
                                            map.put(key, cell.getStringCellValue());
                                        } catch (IllegalStateException e) {//类型错误
                                            if (HSSFDateUtil.isCellDateFormatted(cell)) {//日期
                                                map.put(key, HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                                            } else {//数字
                                                map.put(key, BigDecimal.valueOf(cell.getNumericCellValue()));
                                            }
                                        }
                                        isEmptyRow = false;
                                        break;
                                }
                            } else {
                                map.put(key, "");
                            }
                        }
                    }
                    if (!isEmptyRow) {//空行，忽略
                        list.add(map);
                    }
                }
            } catch (RuntimeException e) {
                throw new RuntimeException((i + 1) + "行" + (j + 1) + "列", e);
            }
        }
        return list;
    }
}