/**
 * Project:szcgcWechatServer
 * File:UserService.java
 * Date:2020年4月8日
 * Author:chenxinli
 * Description:
 */
package com.szcgc.user.service;

import com.szcgc.user.entity.User;
import com.szcgc.user.entity.UserRepository;
import com.szcgc.wechat.smart.entity.Keycontactor;
import com.szcgc.wechat.smart.entity.KeycontactorRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author chenxinli
 * @date 2020年4月8日
 * 
 */
@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository repository;

	@Autowired
	private KeycontactorRepository keycontactorRepository;
	

	public int saveUser(User user) {
		checkArgument(user!=null&&user.getOpenid().length()>0);
		return repository.save(user).getId();
	}
	

	public Page<User> findAll(int page, int size) {
		return repository.findAll(dftPage(page, size));
	}

	public User findByOpenid(String openid) {
		checkArgument(openid.length()>0);
		return repository.findByOpenid(openid);
	}
	
	public User findByCellphone(String cellphone) {
		checkArgument(cellphone.length()>0);
		return repository.findByCellphone(cellphone);
	}


	public User find(int id) {
		checkArgument(id > 0);
		Optional<User> info = repository.findById(id);
		if (info.isPresent())
			return info.get();
		return null;
	}
	
	/**
	 * 绑定手机号
	 * @param user
	 * @return
	 */
	public int binding(User user,String cellphone) {
		user.setCellphone(cellphone);
		int result = saveUser(user);
		logger.info("Wechat Binding:User:"+user.getId()+" Phone:"+user.getCellphone());
		return result;
	}
	
	/**
	 * 解绑手机号
	 * @param user
	 * @return
	 */
	public int unbinding(User user) {
		user.setCellphone("");
		int result = saveUser(user);
		logger.info("Wechat Unbinding:User:"+user.getId()+" Phone:"+user.getCellphone());
		return result;
	}

	/**
	 * 判断用户是否有绑定手机号
	 * @param userid
	 * @return
	 */
	public boolean hasbinding(int userid){
		User user = find(userid);
		if(user!=null && !StringUtils.isEmpty(user.getCellphone())){
			return true;
		}
		return false;
	}

	/**
	 * 判断用户是否是企业核心联系人
	 * @param userid
	 * @return
	 */
	public boolean hasCompany(int userid){
		if(hasbinding(userid)){
			User user = find(userid);
			List<Keycontactor> list = keycontactorRepository.findDistinctCustomidByCellphone(user.getCellphone());
			if(!list.isEmpty()){
				return true;
			}
			return false;
		}

		return false;
	}

	private static final Sort DFTSORT = Sort.by(Sort.Direction.DESC, "id");

	private Pageable dftPage(int page, int size) {
		checkArgument(page >= 0);
		checkArgument(size > 0);
		return PageRequest.of(page, size, DFTSORT);
	}
}
