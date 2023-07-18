package com.szcgc.wechat.entity;

/**
 * AccessToken对象类，存的是token以及过期的时间
 */
public class AccessToken {

	private String accessToken;
	private long expireTime; // 过期时间；

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

	public AccessToken(String accessToken, String expireIn) {
		super();
		this.accessToken = accessToken;
		this.expireTime = System.currentTimeMillis() + Integer.parseInt(expireIn) * 1000;
	}

	/**
	 * @description:是否过期，当前时间大于该token的过期时间时，即已过期，返回true
	 * @return
	 * @author yangyc
	 * @date Mar 25, 2020
	 * @version V1.0
	 */
	public boolean isExpired() {
		return System.currentTimeMillis() > expireTime;
	}
}
