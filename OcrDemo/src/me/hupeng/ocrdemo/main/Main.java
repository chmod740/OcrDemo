package me.hupeng.ocrdemo.main;



public class Main {
	public static void main(String[] args) throws Exception {
		ImgFileUtil imgFileUtil = new ImgFileUtil();
		//这个是图片下载类。第一个参数：验证码的请求的链接 。第二个参数，保存的文件名，第三个参数。cookie,即以某一个cookie读取图片
		imgFileUtil.downloadImageFile("http://acm.imudges.com/JudgeOnline/vcode.php","111.gif","PHPSESSID=dj589pcq1gfbe9jp2v9rgv04n5");	
		OCRUtil ocrUtil = new OCRUtil();
		//图片识别类。参数为图片的位置。结果为识别出的字符串
		//此类仅在windows下使用
		System.out.println(ocrUtil.getImageString("111.gif"));
	}
}