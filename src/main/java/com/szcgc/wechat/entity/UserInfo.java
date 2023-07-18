package com.szcgc.wechat.entity;

import java.util.List;

public class UserInfo {
	// 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号
	private int subscribe;
	// 用户的标识
	private String openId;
	// 用户的昵称
	private String nickName;
	// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private int sex;
	// 用户的语言
	private String language;
	// 用户所在城市
	private String city;
	// 用户所在省份
	private String province;
	// 用户所在国家
	private String country;
	// 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	private String headImgUrl;
	// 用户关注时间，为时间戳
	private String subscribeTime;
	// 用户将公众号绑定到微信开放平台帐号后，出现该字段
	private String unionId;
	// 公众号运营者对粉丝的备注
	private String remark;
	// 用户所在的分组ID（兼容旧的用户分组接口）
	private int groupId;
	// 用户被打上的标签ID列表
	private List<Integer> tagIdList;
	// 用户关注的渠道来源 ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码 等
	private String subscribeScene;
	// 二维码扫码场景（开发者自定义）
	private String qrScene;
	// 二维码扫码场景描述（开发者自定义）
	private String qrSceneStr;

	public int getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public List<Integer> getTagIdList() {
		return tagIdList;
	}

	public void setTagIdList(List<Integer> tagIdList) {
		this.tagIdList = tagIdList;
	}

	public String getSubscribeScene() {
		return subscribeScene;
	}

	public void setSubscribeScene(String subscribeScene) {
		this.subscribeScene = subscribeScene;
	}

	public String getQrScene() {
		return qrScene;
	}

	public void setQrScene(String qrScene) {
		this.qrScene = qrScene;
	}

	public String getQrSceneStr() {
		return qrSceneStr;
	}

	public void setQrSceneStr(String qrSceneStr) {
		this.qrSceneStr = qrSceneStr;
	}

}
