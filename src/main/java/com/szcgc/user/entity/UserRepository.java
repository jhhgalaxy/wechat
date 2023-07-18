/**
 * Project:szcgcWechatServer
 * File:UserRepository.java
 * Date:2020年4月8日
 * Author:chenxinli
 * Description:
 */
package com.szcgc.user.entity;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author chenxinli
 * @date 2020年4月8日
 * 
 */
public interface UserRepository extends PagingAndSortingRepository<User, Integer>{

	User findByCellphone(String cellphone);
	
	User findByOpenid(String openid);
}
