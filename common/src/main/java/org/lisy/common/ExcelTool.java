package org.lisy.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Excel操作工具
 * 
 * @author Lisy
 * @date 2017年4月17日
 * @version 1.0
 */
public class ExcelTool {

	public static void main(String[] args) throws Exception {
		FileInputStream fis = null;
		File f = null;
		FileOutputStream fos = null; 
		try{
//			f = new File("‪C:\\Users\\Sasori\\Desktop\\text.xls");
			f = new File(args[0]);
			fis = new FileInputStream(f);
			Workbook wb = new HSSFWorkbook(fis);
			for(int sheetIndex=0;sheetIndex<wb.getNumberOfSheets();sheetIndex++){
				Sheet sheet = wb.getSheetAt(sheetIndex);
				System.out.println("[SHEET_NAME]"+sheet.getSheetName());
				for(int rowIndex=7;rowIndex<160;rowIndex++){
					Row row = sheet.getRow(rowIndex);
					if(row==null) continue;
					System.out.println("[ROW_NO]"+(rowIndex+1));
					for(int col=0;col<4;col++){
						System.out.println("[COL_NO]"+(col+1));
						Cell cell = row.getCell(col);
						String cellStr = cell.getStringCellValue();
						if(cellStr.equals("REQUEST")||cellStr.equals("RESPONSE")||cellStr.contains("HEADER")||cellStr.contains("BODY")){
							continue;
						}
						cell.setCellValue(ExcelTool.toLowerCase(cellStr));
					}
				}
			}
			fis.close();
			f = new File(args[1]);
			
			if(!f.exists()){
				f.createNewFile();
			}
			
			fos = new FileOutputStream(f);
			wb.write(fos);
		}catch(IOException e){
			System.out.println("文件读取异常");
			e.printStackTrace();
		}finally{
			fis.close();
		}
	}
	
	public static String toLowerCase(String str){
		System.out.println("转换前:"+str);
		char[] charArr = str.toCharArray();
		for(int i=0;i<charArr.length;i++){
			Character c = charArr[i];
			if(Character.isLowerCase(c)){
				continue;
			}else{
				if(i==0||str.charAt(i-1)!='_'){
					charArr[i] = Character.toLowerCase(c);
				}
				else{
					continue;
				}
			}
		}
		str = String.copyValueOf(charArr);
		str = str.replaceAll("_","");
		System.out.println("转换后:"+str.toString());
		return str;
	}
}
