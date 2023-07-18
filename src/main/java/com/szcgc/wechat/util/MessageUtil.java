package com.szcgc.wechat.util;

import com.szcgc.wechat.entity.BaseMessage;
import com.szcgc.wechat.entity.TextMessage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息处理工具
 * 
 * @author yangyc
 * @date 2020-3-19
 */
public class MessageUtil {
    public static final Logger logger = LoggerFactory.getLogger(MessageUtil.class);

    /**
     * 解析微信公众号推送的XML数据包
     * 
     * @param is
     * @return 接收到的消息的map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseRequest(InputStream is) {
        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        try {
            // 读取输入流，获取文档对象
            Document document = reader.read(is);
            // 根据文档对象获取根节点
            Element root = document.getRootElement();
            List<Element> elements = root.elements();
            for (Element e : elements) {
                map.put(e.getName(), e.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 处理用户消息的回复事件，在WeChatServlet类中调用
     * 
     * @param requestMap
     * @return XML数据包
     */
    public static String dealResponse(Map<String, String> requestMap) {
        BaseMessage msg = null;
        String msgtype = requestMap.get("MsgType");
        logger.info("dealResponse  msgtype is: " + msgtype);
        switch (msgtype) {
        case "text":
            // 处理文本消息
            msg = dealTextMessage(requestMap);
            break;
        case "image":

            break;
        case "voice":

            break;
        case "video":

            break;
        case "music":

            break;
        case "news":

            break;
        case "event":
            // 处理事件消息(事件消息我只能response，不能主动推送时间消息
            msg = dealEventMessage(requestMap);
            break;
        default:

            break;
        }
        if (msg != null) {
            // 把消息对象处理为xml数据包
            return XMLUtil.msgToXml(msg);
        }
        return null;
    }

    /**
     * @description:处理接收到的事件推送
     * @param requestMap
     * @return
     * @author yangyc
     * @date Mar 27, 2020
     * @version V1.0
     */
    private static BaseMessage dealEventMessage(Map<String, String> requestMap) {
        String event = requestMap.get("Event");
        logger.info("dealEventMessage  event is: " + event);
        switch (event) {
        // 订阅事件
        case "subscribe":
            String eventKey = requestMap.get("EventKey");
            logger.info("dealEventMessage  eventKey is: " + eventKey);
            if (eventKey != null) {
                return new TextMessage(requestMap, "你扫描二维码实现的关注，业务需求在这里添加");
            }
            return new TextMessage(requestMap, "欢迎订阅公众号");
        // 取消订阅事件
        case "unsubscribe":
            break;
        // 扫描事件
        case "SCAN":
            return new TextMessage(requestMap, "你扫描了二维码，扫码业务需求在这里添加");
        // 上报地理位置事件
        case "LOCATION":
            break;
        // 点击菜单拉取消息事件
        case "CLICK":
            return dealClickEvent(requestMap);
        // 点击菜单跳转链接事件
        case "VIEW":
            return dealViewEvent(requestMap);
        default:

            break;
        }
        return null;
    }

    /**
     * @description:处理菜单点击事件
     * @param requestMap
     * @return
     * @author yangyc
     * @date Mar 27, 2020
     * @version V1.0
     */
    private static BaseMessage dealClickEvent(Map<String, String> requestMap) {

        String eventKey = requestMap.get("EventKey");
        logger.info("dealClickEvent  eventKey is: " + eventKey);
        switch (eventKey) {
        // 点击一级菜单
        case "1":
            return new TextMessage(requestMap, "你点了一下第一个一级菜单");
        case "32":
            break;
        default:

            break;
        }
        return null;

    }

    /**
     * @description:处理菜单跳转事件
     * @param requestMap
     * @return
     * @author yangyc
     * @date Mar 27, 2020
     * @version V1.0
     */
    private static BaseMessage dealViewEvent(Map<String, String> requestMap) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 处理文本消息
     * 
     * @param requestMap
     * @return
     */
    private static BaseMessage dealTextMessage(Map<String, String> requestMap) {
        // 用户发送的内容
        String getContent = requestMap.get("Content");
        logger.info("dealTextMessage  getContent is: " + getContent);
        // 用来测试返回不同类型的消息
        if (getContent.equals("测试返回值")) {
            TextMessage tm = new TextMessage(requestMap, "文本消息返回"); // 返回文本消息
            return tm;

            // 返回图文消息
//			List<Article> articles = new ArrayList<Article>();
//			articles.add(new Article("图文标题", "图文描述", "http://mmbiz.qpic.cn/mmbiz_jpg/k8J9PT5Ih0F3ZMvKaTUNEaK3Yhx5icmNj9DRbKtCjmgKNZsXlFBS2YiaYpM4JicN44JE9CcayiaexRjpfsbiagnXPyA/0", "www.baidu.com"));
//			NewsMessage nm = new NewsMessage(requestMap, articles);
//			return nm;

            // 返回图片消息
//			String mediaId = "X3eXZiZk9NZuk6oehcGAXIx5fQ9bDqseV5SpdFEVbTmrhKuIGAsLl1LzxnMdFKtd";
//			Image image = new Image(mediaId);
//			ImageMessage im = new ImageMessage(requestMap, image);
//			return im;			
        }
        if (getContent.equals("网页授权")) {
            TextMessage tm = new TextMessage(requestMap,
                    "点击<a href = \"" + NetWorkAuthorizedUtil.authorize_url + "\">这里</a>登陆");
            return tm;
        }

        return null;
    }

}
