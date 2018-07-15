/**
 * 
 */
package com.heqichao.springBootDemo.base.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author husq
 * @version 1.0 2014-11-15
 * @类描述 Excel文件解析类
 * @修订历史：
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ExcelReader {
    // Excel读取时用到的参数
    private static POIFSFileSystem fs;
    private static HSSFWorkbook wb;
    private static HSSFSheet sheet;
    private static HSSFRow row;

    /**
     * 读取文件
     * @param file excel文件
     * @return
     */
    public static Map readFile(File file) {
        try {
            return readInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取文件
     * @param file excel文件
     * @return
     */
    public static Map readInputStream(InputStream inputStream) {
        LinkedHashMap map=new LinkedHashMap();
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            int sheetTotal = workbook.getNumberOfSheets() ;
            for (int z = 0; z < sheetTotal; z++) {
                HSSFSheet sheet = workbook.getSheetAt(z);
                List list = new ArrayList();
                int totalRow = sheet.getLastRowNum();// 得到excel的总记录条数

                for (int i = 0; i <= totalRow; i++) {
                    List rowlist = new ArrayList();
                    HSSFRow row = sheet.getRow(i);
                    if(row!=null){
                        int totalCell = row.getLastCellNum();
                        for (int j = 0; j < totalCell; j++) {
                            HSSFCell cell = row.getCell(j);
                            String value=getCellValue(cell);
                            rowlist.add(value);
                        }
                        list.add(rowlist);
                    }
                }
                map.put(sheet.getSheetName(), list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取单元格内容
     * @param cell 单元格
     * @return
     */
    private static String getCellValue(HSSFCell cell){
        if(cell==null){
            return null;
        }
        int cellType=cell.getCellType();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String value="";
        switch(cellType){
        case HSSFCell.CELL_TYPE_NUMERIC:
            if(org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)){
                value=dateFormat.format(cell.getDateCellValue());
            }else{
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String temp=cell.getStringCellValue();
                if(temp.indexOf(".")>-1){
                    value=String.valueOf(new Double(temp)).trim();
                }else{
                    value=temp.trim();
                }
            }
            break;
        case Cell.CELL_TYPE_FORMULA:    // 公式
            // 用数字方式获取公式结果，根据值判断是否为日期类型
            double numericValue = cell.getNumericCellValue() ;
            if(HSSFDateUtil.isValidExcelDate(numericValue)) {   // 如果是日期类型
                value=dateFormat.format(cell.getDateCellValue());
            } else  {
                value = String.valueOf(numericValue) ;
            }
            break ;
        case Cell.CELL_TYPE_BOOLEAN:            // Boolean
            value = String.valueOf(cell.getBooleanCellValue()).toUpperCase() ;
            break ;
        case Cell.CELL_TYPE_ERROR:              // Error，返回错误码
            value = String.valueOf(cell.getErrorCellValue()) ;
            break ;
        default:
            value=cell.getStringCellValue();
            break;
        }
        return value;
    }

    /**
     * 根据文件版本选择读取方式
     * @param filePath 文件路径
     * @return
     */
    public static String readExcel(String filePath){
        if(isExcel2003(filePath)){
            return readXls(filePath);
        }else if(isExcel2007(filePath)){
            return readXLSX(filePath);
        }else{
            return "";
        }
    }

    /**
     * 读取xls数据内容
     *
     * @param InputStream
     * @return 每个单元格的内容拼成的字符串
     */
    private static String readXls(String filePath) {
        InputStream is = null;
        Map<Integer, String> content = new HashMap<Integer, String>();
        String str = "";
        try {
            is = new FileInputStream(filePath);
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 0; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            while (j < colNum) {
                // 每个单元格的数据内容用" "分割开，以后需要时用String类的replace()方法还原数据
                str += getCellFormatValue(row.getCell((short) j)).trim() + " ";
                j++;
            }
            content.put(i, str);
        }
        return str;
    }

    /**
     * 读取xlsx文件
     * @param filePath 文件路径
     * @return
     */
    private static String readXLSX(String filePath) {
        String str = "";
        // 构造 XSSFWorkbook 对象，strPath 传入文件路径
        XSSFWorkbook xwb = null;
        try {
            xwb = new XSSFWorkbook(filePath);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < xwb.getNumberOfSheets(); numSheet++) {
            XSSFSheet xSheet = xwb.getSheetAt(numSheet);
            if (xSheet == null) {
                continue;
            }

            // 循环行Row
            for (int rowNum = 0; rowNum <= xSheet.getLastRowNum(); rowNum++) {
                XSSFRow xRow = xSheet.getRow(rowNum);
                if (xRow == null) {
                    continue;
                }

                // 循环列Cell
                for (int cellNum = 0; cellNum <= xRow.getLastCellNum(); cellNum++) {
                    XSSFCell xCell = xRow.getCell(cellNum);
                    if (xCell == null) {
                        continue;
                    }

                    if (xCell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
                        str += String.valueOf(xCell.getBooleanCellValue());
                    } else if (xCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                        str += String.valueOf(xCell.getNumericCellValue());
                    } else {
                        str += String.valueOf(xCell.getStringCellValue());
                    }
                }
            }
        }
        return str;
    }

    /**
     * 根据HSSFCell类型设置数据
     *
     * @param cell
     * @return
     */
    private static String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case HSSFCell.CELL_TYPE_NUMERIC:
            case HSSFCell.CELL_TYPE_FORMULA: {
                // 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式
                    // 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                    // cellvalue = cell.getDateCellValue().toLocaleString();
                    // 方法2：这样子的data格式是不带带时分秒的：2011-10-12
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue = sdf.format(date);
                }
                // 如果是纯数字
                else {
                    // 取得当前Cell的数值
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            // 如果当前Cell的Type为STRIN
            case HSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // 默认的Cell值
            default:
                cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }

    /**
     * 判断是否xls格式
     * @param filePath
     * @return
     */
    private static boolean isExcel2003(String filePath) {
        try {
            new HSSFWorkbook(new FileInputStream(new File(filePath)));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否xlsx格式
     * @param filePath
     * @return
     */
    private static boolean isExcel2007(String filePath) {
        try {
            new XSSFWorkbook(new FileInputStream(new File(filePath)));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
