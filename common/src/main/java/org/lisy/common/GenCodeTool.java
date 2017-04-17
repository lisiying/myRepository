/**
 * 
 */
package org.lisy.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
/** 
 * 生成代码的工具
 * 
 * @author Lisy
 * @date 2017年3月15日
 * @version 1.0 
 */
public class GenCodeTool {
	public static void main(String[] args) throws IOException {
		GenCodeTool t = new GenCodeTool();
		InputStreamReader isr = new InputStreamReader(new FileInputStream(args[0]),"UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		//下面这行代码会导致br的指针向后移动一位，因此是错误的...
//		while(br.read()!=-1){
		String line = null;
		while((line=br.readLine())!=null){
			t.transfer(line);
		}
		
		br.close();
	}
	
	public void transfer(String str){
		
		str = str.toLowerCase();
		String[] arr = str.split("	");
		
		//生成中文描述
		String str2 = arr[1];
		pln("/*");
		pln(" *"+str2);
		pln("*/");
		
		//生成代码--考虑根据数据类型生成属性类型
		String str1 = arr[0];
		for(int i=0;i<str1.length();i++){
			char c = str1.charAt(i);
			if(c=='_'){
				str1 = str1.substring(0, i) + (char)(str1.charAt(i+1)-32) + str1.substring(i+2);
				
			}
		}
		
		pln("protected String "+ str1 + ";");
	}
	
	public void pln(Object o){
		System.out.println(o);
	}
}
