package me.hupeng.ocrdemo.main;



public class Main {
	public static void main(String[] args) throws Exception {
		ImgFileUtil imgFileUtil = new ImgFileUtil();
		//�����ͼƬ�����ࡣ��һ����������֤������������ ���ڶ���������������ļ�����������������cookie,����ĳһ��cookie��ȡͼƬ
		imgFileUtil.downloadImageFile("http://acm.imudges.com/JudgeOnline/vcode.php","111.gif","PHPSESSID=dj589pcq1gfbe9jp2v9rgv04n5");	
		OCRUtil ocrUtil = new OCRUtil();
		//ͼƬʶ���ࡣ����ΪͼƬ��λ�á����Ϊʶ������ַ���
		//�������windows��ʹ��
		System.out.println(ocrUtil.getImageString("111.gif"));
	}
}