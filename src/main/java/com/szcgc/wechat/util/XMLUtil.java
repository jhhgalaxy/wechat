package com.szcgc.wechat.util;

import java.io.Writer;

import com.szcgc.wechat.entity.Article;
import com.szcgc.wechat.entity.BaseMessage;
import com.szcgc.wechat.entity.Image;
import com.szcgc.wechat.entity.ImageMessage;
import com.szcgc.wechat.entity.Music;
import com.szcgc.wechat.entity.MusicMessage;
import com.szcgc.wechat.entity.NewsMessage;
import com.szcgc.wechat.entity.TextMessage;
import com.szcgc.wechat.entity.Video;
import com.szcgc.wechat.entity.VideoMessage;
import com.szcgc.wechat.entity.Voice;
import com.szcgc.wechat.entity.VoiceMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 处理XML
 * 
 * @author yangyc
 * @date 2020-3-25
 */
public class XMLUtil {

	/**
	 * 扩展xstream，使其支持CDATA块
	 * 
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		@Override
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("rawtypes")
				@Override
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
					if (name.equals("CreateTime")) {
						cdata = false;
					} else {
						cdata = true;
					}
				}

				@Override
				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	/**
	 * 把消息对象处理为xml数据包
	 * 
	 * @param msg
	 * @return
	 */
	public static String msgToXml(BaseMessage msg) {
		xstream.processAnnotations(BaseMessage.class);
		xstream.processAnnotations(TextMessage.class);
		xstream.processAnnotations(Article.class);
		xstream.processAnnotations(Image.class);
		xstream.processAnnotations(ImageMessage.class);
		xstream.processAnnotations(Music.class);
		xstream.processAnnotations(MusicMessage.class);
		xstream.processAnnotations(NewsMessage.class);
		xstream.processAnnotations(Video.class);
		xstream.processAnnotations(VideoMessage.class);
		xstream.processAnnotations(Voice.class);
		xstream.processAnnotations(VoiceMessage.class);

		String xml = xstream.toXML(msg);
		return xml;
	}

}
