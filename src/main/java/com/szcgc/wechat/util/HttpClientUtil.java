package com.szcgc.wechat.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * get/post请求
 */
public class HttpClientUtil {

//	/**
//	 * 向传入的url发get请求，
//	 *
//	 * @param url
//	 * @return
//	 */
//	public static String get(String url) {
//		try {
//			URL urlObj = new URL(url);
//			URLConnection connection = urlObj.openConnection();
//			InputStream is = connection.getInputStream();
//
//			byte[] b = new byte[1024];
//			int len;
//			StringBuilder sb = new StringBuilder();
//			while ((len = is.read(b)) != -1) {
//				sb.append(new String(b, 0, len));
//			}
//			return sb.toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	/**
	 * 向传入的url发get请求，
	 *
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		try {
			URL urlObj = new URL(url);
			URLConnection connection = urlObj.openConnection();
			InputStream is = connection.getInputStream();
			BufferedReader  reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
			byte[] b = new byte[1024];
			StringBuilder sb = new StringBuilder();
			String line;
			while((line=reader.readLine())!=null){
				sb.append(line+"\n");
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	/**
	 * @description:向指定的url发post请求
	 * @param url
	 * @param data
	 * @return
	 * @author yangyc
	 * @date Mar 26, 2020
	 * @version V1.0
	 */
	public static String post(String url, String data) throws IOException {

		URL urlObj = new URL(url);
		URLConnection urlConnection = urlObj.openConnection();
		// 设置为可发送数据状态，否则不可发送数据
		urlConnection.setDoOutput(true);

		OutputStream os = urlConnection.getOutputStream();
		os.write(data.getBytes(Charset.forName("utf-8")));
		os.close();

		InputStream is = urlConnection.getInputStream();
		byte[] b = new byte[1024];
		int len;
		StringBuilder sb = new StringBuilder();
		while ((len = is.read(b)) != -1) {
			sb.append(new String(b, 0, len));
		}
		return sb.toString();

	}

	/**
	 * @description:urlEncode编码
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 * @author yangyc
	 * @date Mar 30, 2020
	 * @version V1.0
	 */
	public static String urlEncode(String str) {
		if (str == null)
			return "";
		try {
			return java.net.URLEncoder.encode(str, "UTF-8");
		} catch (Exception e) {
		}
		return "";
	}

}
