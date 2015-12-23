package me.hupeng.ocrdemo.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class OCRUtil {
	public String chiSIM = "chi_sim";
	
	public void runOCR(String realPath,String imagePath,String outPath,boolean isChi) throws Exception{
		Runtime r = Runtime.getRuntime();
		//tesseract    图片名  输出文件名 -l 字库文件 -psm pagesegmode 配置文件
		//System.out.println("D:\\WEB\\OcrDemo\\Tesseract-OCR\\tesseract.exe \""+imagePath+"\" \""+outPath+"\" -l "+(isChi?chiSIM:""));
		String cmd = "Tesseract-OCR\\tesseract.exe \""+imagePath+"\" \""+outPath+"\" -l "+(isChi?chiSIM:"");
		r.exec(cmd);
	}
	
	public String getImageString(String imagePath){
		String realPath = "";
		//String imagePath = "6970.jpg";
		String outPath = System.currentTimeMillis()+"";
		String result = "";
		File file = new File(outPath+".txt");
		if (file.exists()) {
			file.delete();
		}
		try {
			runOCR(realPath, imagePath, outPath, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		while(!file.exists()){
			try {
				//可调参数
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			try {
				result = bufferedReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
			try {
				bufferedReader.close();
				inputStreamReader.close();
				fileInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		file.delete();
		if (result == null) {
			result = "";
		}
		return result;
	}
}
