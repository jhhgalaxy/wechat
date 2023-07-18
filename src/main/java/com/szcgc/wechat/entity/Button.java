package com.szcgc.wechat.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单的封装,这里的button我理解相当于menu，只是鉴于微信公众号的习惯，才用的button
 * 
 * @author yangyc
 * @date 2020-3-25
 */
public class Button {
	// List<AbstractBtn> button中每add一个，add的这个就是一个一级菜单
	private List<AbstractBtn> button = new ArrayList<>();

	public List<AbstractBtn> getButton() {
		return button;
	}

	public void setButton(List<AbstractBtn> button) {
		this.button = button;
	}

}
