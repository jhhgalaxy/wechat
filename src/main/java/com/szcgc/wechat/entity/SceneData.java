package com.szcgc.wechat.entity;

/**
 * 带字符串参数的场景数据
 * 
 * @author yangyc
 *
 */
public class SceneData {

	private String scene_str;

	public String getScene_str() {
		return scene_str;
	}

	public void setScene_str(String scene_str) {
		this.scene_str = scene_str;
	}

	public SceneData(String scene_str) {
		super();
		this.scene_str = scene_str;
	}

}
