package com.szcgc.wechat.smart;

/**
 * @Author: chenxinli
 * @Date: 2020/4/21 15:00
 * @Description:
 */
public enum MsgTypeEm {
    TYPE_0("0", "项目进展"),
    TYPE_1("1", "收费信息"),
    TYPE_2("2", "还款还息信息"),
    TYPE_3("3", "投诉反馈");

    /**
     * key值
     */
    private String key;

    /**
     * 对应名称
     */
    private String name;

    private MsgTypeEm(String key, String name){
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameByKey(String typeKey){
        for(MsgTypeEm en : MsgTypeEm.values()){
            if(en.getKey().equals(typeKey)){
                return en.getName();
            }
        }
        return "";
    }
}
