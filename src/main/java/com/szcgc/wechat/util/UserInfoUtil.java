package com.szcgc.wechat.util;

import com.szcgc.wechat.entity.UserInfo;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserInfoUtil {

	private static final Logger logger = LoggerFactory.getLogger(UserInfoUtil.class);
	private static final String GET_OPENIDS_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
	private static final String GET_USERINFO_URL = " https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	/**
	 * @description:获取全部关注用户的openid,用户数量不超过10000的情况下
	 * @return 存着String型openid的list
	 * @author yangyc
	 * @date Mar 31, 2020
	 * @version V1.0
	 * @throws JSONException
	 */
	public static List<String> getOpenIds() throws JSONException {
		String accessToken = TokenUtil.getAccessToken();
		String getOpenIdsUrl = GET_OPENIDS_URL.replace("ACCESS_TOKEN", accessToken);
		String openIdsStr = HttpClientUtil.get(getOpenIdsUrl);
		// openIdsStr是向微信公众号平台发送get请求后的反馈值，需要作为日志输出
		logger.info("openIdsStr is : " + openIdsStr);
		JSONObject openIdsObj = new JSONObject(openIdsStr);
		JSONObject dataObj = openIdsObj.getJSONObject("data");
		JSONArray openIdsArray = dataObj.getJSONArray("openid");
		if (openIdsArray != null) {
			// 遍历JSONArray,存入List中
			List<String> openIdsList = new ArrayList<String>();
			for (int i = 0; i < openIdsArray.length(); i++) {
				String openId = openIdsArray.getString(i);
				openIdsList.add(openId);
			}
			return openIdsList;
		}
		return null;
	}

	/**
	 * @description:通过用的openId，获取全部关注用户的UserInfo
	 * @param openId
	 * @return
	 * @author yangyc
	 * @date Mar 31, 2020
	 * @version V1.0
	 * @throws JSONException
	 */
	public static UserInfo getUserInfo(String openId) throws JSONException {
		String accessToken = TokenUtil.getAccessToken();
		String getUserInfoUrl = GET_USERINFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		String userInfoStr = HttpClientUtil.get(getUserInfoUrl);
		logger.info("userInfoStr is: " + userInfoStr);
		JSONObject userInfoObj = new JSONObject(userInfoStr);
		UserInfo userInfo = new UserInfo();
		if (userInfoObj != null) {
			userInfo.setSubscribe(userInfoObj.getInt("subscribe"));
			userInfo.setOpenId(userInfoObj.getString("openid"));
			userInfo.setNickName(userInfoObj.getString("nickname"));
			userInfo.setSex(userInfoObj.getInt("sex"));
			userInfo.setLanguage(userInfoObj.getString("language"));
			userInfo.setCity(userInfoObj.getString("city"));
			userInfo.setProvince(userInfoObj.getString("province"));
			userInfo.setCountry(userInfoObj.getString("country"));
			userInfo.setHeadImgUrl(userInfoObj.getString("headimgurl"));
			userInfo.setSubscribeTime(userInfoObj.getString("subscribe_time"));
			// 没有绑定平台的话，该字段在微信公众号返回的字段中没有，则会报错
			// userInfo.setUnionId(userInfoObj.getString("unionid"));
			userInfo.setRemark(userInfoObj.getString("remark"));
			userInfo.setGroupId(userInfoObj.getInt("groupid"));
			JSONArray tagIdArray = userInfoObj.getJSONArray("tagid_list");
			if (tagIdArray != null) {
				// 遍历JSONArray,存入List中
				List<Integer> tagIdList = new ArrayList<Integer>();
				for (int i = 0; i < tagIdArray.length(); i++) {
					int tagId = tagIdArray.getInt(i);
					tagIdList.add(tagId);
				}
				userInfo.setTagIdList(tagIdList);
			}
			userInfo.setSubscribeScene(userInfoObj.getString("subscribe_scene"));
			userInfo.setQrScene(userInfoObj.getString("qr_scene"));
			userInfo.setQrSceneStr(userInfoObj.getString("qr_scene_str"));
		}
		return userInfo;
	}

}
