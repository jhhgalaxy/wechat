package com.szcgc.wechat.sms;

import com.szcgc.config.WebEnvConfig;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 茧米云/宽乐通信短信接口
 *		
 * @作者hql
 * @创建时间2019-8-14 下午2:09:20
 */


public class SMSManages {




	// 帐号
	String _uc="";
	// 密码
	String _pwd="";
	// 发送地址
	String _host = "";
	// 签名
	String _sign = "";
	
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	private static final String PROPERTY_SMS_ACCOUNT = "smsUc";
	private static final String PROPERTY_SMS_PASSWORD = "smsPwd";
	private static final String PROPERTY_SMS_URL = "smsUrl";
	private static final String PROPERTY_SMS_SIGN = "smsSign";

	/**
	 * 构造函数初始化
	 */
	public SMSManages() {
		this._uc = WebEnvConfig.APPPROPERTIES.getProperty("smsUc");
		this._pwd = WebEnvConfig.APPPROPERTIES.getProperty("smsPwd");
		this._host = WebEnvConfig.APPPROPERTIES.getProperty("smsUrl");
		this._sign = WebEnvConfig.APPPROPERTIES.getProperty("smsSign");
	}

	public void getInfo(){
		System.out.println(_uc);
		System.out.println(_pwd);
		System.out.println(_host);
		System.out.println(_sign);
	}

	/**
	 * 密码MD5加密
	 * @return
	 */
	public String get_pwd() {
		return this.MD5Encode(_pwd);
	}
	
	/**
	 * 发送短信
	 * 
	 * @param mobiles 接收号码
	 * @param cont 短信内容
	 * @param msgid 短信ID
	 * @return String
	 */
	public String sendSMS(String mobiles, String cont, String msgid) {
		String re = "";
		try {
			//LogUtil.log().info("_sign="+_sign);
			cont = URLEncoder.encode(cont+"【"+_sign+"】", "GBK"); // 短信内容需要编码
			String sendUrl = _host 
					+ "cmd=send&uid="+ _uc 
					+ "&psw=" + this.get_pwd() 
					+ "&mobiles=" + mobiles 
					+ "&msgid=" + msgid 
					+ "&msg="+ cont;
			re = submit(sendUrl);
		} catch (Exception ex) {
		}
		return re;
	}

	/**
	 * 接收短信
	 * 
	 * @return String
	 */
	public String getMO() {
		String re = "";
		try {
			String moUrl = _host + "cmd=getmo&uid=" + _uc + "&psw=" + this.get_pwd() + "";
			re = submit(moUrl);
		} catch (Exception ex) {

		}
		return re;
	}

	/**
	 * 取发送状态
	 * 
	 * @return String
	 */
	public String getStatus() {
		String re = "";
		String getstatusUrl = _host + "cmd=getstatus&uid=" + _uc + "&psw=" + this.get_pwd() + "";
		re = submit(getstatusUrl);
		return re;
	}

	/**
	 * GET提交
	 *
	 * @return String
	 */
	private String submit(String strURL) {
		String re = "";
		HttpURLConnection urlConn = null;
		InputStream in = null;
		List<String> list = new ArrayList<String>();
		try {
			URL url = new URL(strURL);
			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setDoOutput(true);
			urlConn.setConnectTimeout(3000);
			urlConn.setRequestMethod("GET");
			urlConn.getOutputStream().flush();
			urlConn.getOutputStream().close();
			in = urlConn.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in, "GBK"));
			String line = rd.readLine();
			while (line != null) {
				line = line.trim();
				if (!line.equals("")){
					list.add(line);
				}
				line = rd.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null){
				try {
					in.close();
				} catch (IOException e) {}
			}
			if (urlConn != null) {
				urlConn.disconnect();
			}
		}
		if (list != null) {
			if (list.size() == 1) {
				re += list.get(0);
			} else if (list.size() > 1) {
				for (int i = 0; i < list.size(); i++) {
					re += list.get(i);
				}
			}
		}
		return re.trim();
	}

	/**
	 * MD5加密
	 * 
	 * @param origin
	 * @return
	 */
	private String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {

		}
		return resultString;
	}

	private String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

}
