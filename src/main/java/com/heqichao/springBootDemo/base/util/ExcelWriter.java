package com.heqichao.springBootDemo.base.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 功能描述：导出EXCEL辅助类
 *
 * @version
 * @since 1.0
 * create on: 8/25/2013 
 *
 */
@SuppressWarnings({ "rawtypes" })
public class ExcelWriter {
	
	/**
	 * 
	 * 
	 * 功能描述：
	 * 
	 * @param title		导出的EXCEL文件中的sheet名称
	 * @param headers	导出列名数组
	 * @param data		待导出的数据集合
	 * @param keys		列名数组(非必须参数,该参数不为空时按该数组中的列名顺序取map中的值)
	 * @return 
	 */
	public static HSSFWorkbook export(String title,String [] headers,List data,String [] keys){
		  
		//logOperation(7, "导出"+title);
		
		HSSFWorkbook workbook = new HSSFWorkbook();	// 声明一个工作薄
		HSSFSheet sheet = workbook.createSheet();	// 生成一个表格
		HSSFCellStyle style = workbook.createCellStyle();	//标题样式
		HSSFCellStyle style2 = workbook.createCellStyle();	//文本单元格样式
		HSSFCellStyle style3 = workbook.createCellStyle();	//常规单元格样式
		HSSFFont font = workbook.createFont();	//字体
		HSSFFont font2 = workbook.createFont();	//生成另一个字体
		HSSFRow row;
		
		workbook.setSheetName(0, title);
		sheet.setDefaultColumnWidth(20);	// 设置表格默认列宽度为15个字节
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);//设置样式
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setFont(font);	//把字体应用到当前的样式
		
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style2.setFont(font2);// 把字体应用到当前的样式
		style2.setWrapText(true);
		HSSFDataFormat format = workbook.createDataFormat();   
		style2.setDataFormat(format.getFormat("@"));   
		
		style3.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style3.setFont(font2);// 把字体应用到当前的样式
		style3.setWrapText(true);
		style3.setDataFormat(format.getFormat("General"));
		
		//产生表格标题行
		row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
	//		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellStyle(style); 
			cell.setCellValue(headers[i]);
		}
        
		//遍历集合数据，产生数据行
		Iterator it = data.iterator();
		int index = 0;
		//列名数组不为空按列名从map中取值，否则按顺序迭代map取值
		if(keys!=null && keys.length>0){
			while (it.hasNext()) {
				index++;
				row = sheet.createRow(index);
				row.setHeightInPoints(20);
				Map map = (Map) it.next(); 
				int cellNum = 0;
				for(int j=0;j<keys.length;j++){
					HSSFCell cell = row.createCell(cellNum);
					if (isNotEmpty(map.get(keys[j]))) {
						cell.setCellValue(map.get(keys[j]).toString());
						if("S_RULE_CONTENT".equals(keys[j])||"ruleXmlDesc".equals(keys[j])){
							cell.setCellStyle(style3);
						}else{
							cell.setCellStyle(style2);
						}
					}else{
						cell.setCellStyle(style2);
					}
					cellNum++;
				}
			}
		}else{
			while (it.hasNext()) {
				index++;
				row = sheet.createRow(index);
				Map map = (Map) it.next();
				Iterator itr = map.entrySet().iterator();
				Short cellNum = 0;
				while (itr.hasNext()) {
					Entry entry = (Entry) itr.next();
					HSSFCell cell = row.createCell(cellNum);
					cell.setCellStyle(style2);
					if (isNotEmpty(entry.getValue())) {
						cell.setCellValue(entry.getValue().toString());
					}
					cellNum++;
				}
			}
		}
		return workbook;
	}
	
	public static String exportToCvs(String title,String [] headers,List data,String [] keys){
		StringBuffer sb = new StringBuffer(data.size() * 500); //初定每一行大概有500字符
		
		//产生表格标题行
		for (short i = 0; i < headers.length; i++) 
			sb.append(headers[i]).append(",");
		sb.append("\n");
        
		//遍历集合数据，产生数据行
		Iterator it = data.iterator();
		//列名数组不为空按列名从map中取值，否则按顺序迭代map取值
		Map map = null;
		if(keys!=null && keys.length>0){
			while (it.hasNext()) {
				map = (Map) it.next(); 
				for(int j=0;j<keys.length;j++){
					if (isNotEmpty(map.get(keys[j]))) 
						sb.append(filter(map.get(keys[j]).toString()));
					sb.append(",");
				}
				sb.append("\n");
			}
		}else{
			while (it.hasNext()) {
				map = (Map) it.next(); 
				Iterator itr = map.entrySet().iterator();
				while (itr.hasNext()) {
					Entry entry = (Entry) itr.next();
					if (isNotEmpty(entry.getValue())){
						sb.append(filter(entry.getValue().toString()));
					}
					sb.append(",");
				}
				sb.append("\n");
			}
		}

		return   sb.toString(); 
	}
	
	private static String filter(String value) {
		value = value.replaceAll("\"", "\"\"");
		value = "\"" + value + "\"";
		return value + "\t";
	}
	
	/**
	 * 判断对象是否不为空
	 * @param obj 检查对象
	 * @return boolean
	 */
	public static boolean isNotEmpty(Object obj){
		if(obj==null){
			return false;
		}
		if(obj instanceof String){
			return ((String)obj).trim().length()!=0;
		}
		return true;
	}
}
