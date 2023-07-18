/**
 * Project:szcgcWechatServer
 * File:UserController.java
 * Date:2020年4月9日
 * Author:chenxinli
 * Description:
 */
package com.szcgc.wechat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.szcgc.user.entity.User;
import com.szcgc.user.service.UserService;

/**
 * @author chenxinli
 * @date 2020年4月9日
 * 
 */

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@GetMapping(value = "user/bind")
	public String binding(int userid,Model model) {
		model.addAttribute("userid",userid);
		return "szcgc/common/bindUser";
	}

	/**
	 * 执行用户绑定操作
	 * @param userid
	 * @param cellphone
	 * @param model
	 * @return
	 */
	@PostMapping(value = "user/bind")
	public String binding(int userid,String cellphone,Model model) {
		User user = userService.find(userid);
		if(user!=null) {
			userService.binding(user,cellphone);
			
		}
		
		return "szcgc/center/index";
	}

	@GetMapping(value = "user/bindstatus")
	public String bindingstatus(int userid,Model model) {
		model.addAttribute("userid",userid);
		return "szcgc/common/bindPhoneChange";
	}

	@GetMapping(value = "user/unbind")
	public String unbindingview(int userid,Model model) {
		model.addAttribute("userid",userid);
		return "szcgc/common/bindUserChange";
	}


}
