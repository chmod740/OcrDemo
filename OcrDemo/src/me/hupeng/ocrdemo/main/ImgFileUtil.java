package me.hupeng.ocrdemo.main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImgFileUtil {
	private String filename;
	private String cookie;
	private String urlStr;
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	

	public String getUrlStr() {
		return urlStr;
	}

	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}

	public void downloadImageFile(String urlStr) throws Exception {
		if (filename == null) {
			filename = "temp.jpg";
		}
		this.urlStr = urlStr;
		URL url = new URL(urlStr);
		// ������
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// ��������ʽΪ"GET"
		conn.setRequestMethod("GET");
		// ����һЩͨ������
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("user-agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		if (cookie != null) {
			conn.setRequestProperty("Cookie", cookie);
		}
		// ��ʱ��Ӧʱ��Ϊ5��
		conn.setConnectTimeout(5 * 1000);
		// ͨ����������ȡͼƬ����
		InputStream inStream = conn.getInputStream();
		// �õ�ͼƬ�Ķ��������ݣ��Զ����Ʒ�װ�õ����ݣ�����ͨ����
		byte[] data = readInputStream(inStream);
		// newһ���ļ�������������ͼƬ��Ĭ�ϱ��浱ǰ���̸�Ŀ¼
		File imageFile = new File(filename);
		// ���ļ��Ѵ��ڣ�����ɾ��
		if (imageFile.exists()) {
			imageFile.delete();
		}
		// ���������
		FileOutputStream outStream = new FileOutputStream(imageFile);
		// д������
		outStream.write(data);
		// �ر������
		outStream.close();
		while (!imageFile.exists()) {
			Thread.sleep(1);
		}
	}
	
	public void downloadImageFile(String urlStr,String filename,String cookie) throws Exception{
		this.filename = filename;
		this.cookie = cookie;
		downloadImageFile(urlStr);
	}
	
	private byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// ����һ��Buffer�ַ���
		byte[] buffer = new byte[1024];
		// ÿ�ζ�ȡ���ַ������ȣ����Ϊ-1������ȫ����ȡ���
		int len = 0;
		// ʹ��һ����������buffer������ݶ�ȡ����
		while ((len = inStream.read(buffer)) != -1) {
			// ���������buffer��д�����ݣ��м����������ĸ�λ�ÿ�ʼ����len�����ȡ�ĳ���
			outStream.write(buffer, 0, len);
		}
		// �ر�������
		inStream.close();
		// ��outStream�������д���ڴ�
		return outStream.toByteArray();
	}
}
