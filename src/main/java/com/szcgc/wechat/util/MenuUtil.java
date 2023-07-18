package com.szcgc.wechat.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.szcgc.wechat.entity.Button;
import com.szcgc.wechat.entity.ClickButton;
import com.szcgc.wechat.entity.PhotoOrAlbumButton;
import com.szcgc.wechat.entity.SubButton;
import com.szcgc.wechat.entity.ViewButton;

/**
 * 这个类主要用来创建菜单，并将菜单转换为json串，
 */
public class MenuUtil {

    private static final Logger logger = LoggerFactory.getLogger(MenuUtil.class);
    private static final String Menu_Url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * @description:创建菜单
     * @return
     * @throws IOException
     * @author yangyc
     * @date Mar 26, 2020
     * @version V1.0
     */
    public static String menuInit() throws IOException {

        // 应该从配置表中读取数据的（待补充完善）；
        Button button = new Button();
        button.getButton().add(new ClickButton("一级点击", "1"));
        button.getButton().add(new ViewButton("一级跳转", "http://www.baidu.com"));
        SubButton subBtn = new SubButton("我有子菜单");
        subBtn.getSub_button().add(new PhotoOrAlbumButton("传图", "31"));
        subBtn.getSub_button().add(new ClickButton("二级点击", "32"));
        subBtn.getSub_button().add(new ViewButton("二级跳转", "http://www.taobao.com"));
        button.getButton().add(subBtn);
        String menuData = JsonUtil.objToJsonMap(button);
        logger.info("menuInit menuData is : " + menuData);
        System.out.println("menuInit menuData is : " + menuData);
        return menuData;
    }

    public static void setMenu() throws IOException {
        String accessToken = TokenUtil.getAccessToken();
        String url = Menu_Url.replace("ACCESS_TOKEN", accessToken);
        String menuData = menuInit();
        String res = HttpClientUtil.post(url, menuData);
        logger.info("setMenu post res is： " + res);
    }

}
