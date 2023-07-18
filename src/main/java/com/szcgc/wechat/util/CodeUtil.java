package com.szcgc.wechat.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * 调用google的zxing生成的永久二维码
 * 
 * @author yangyc
 *
 */
public class CodeUtil {
    public static final Logger logger = LoggerFactory.getLogger(CodeUtil.class);

    // 二维码生成
    public static void encode(String conent, String filePath) {
        Charset charset = Charset.forName("UTF-8");
        CharsetEncoder encoder = charset.newEncoder();
        byte[] b = null;
        try {
            // Convert a string to ISO-8859-1 bytes in a ByteBuffer
            logger.info("CodeUtil encode(String conent, String filePath) --------> conent.length: " + conent.length());
            ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(conent));
            b = bbuf.array();
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
        String data = "";
        try {
            data = new String(b, "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // get a byte matrix for the data
        BitMatrix matrix = null;
        int h = 900;
        int w = 800;
        com.google.zxing.Writer writer = new QRCodeWriter();
        try {
            matrix = writer.encode(data, com.google.zxing.BarcodeFormat.QR_CODE, w, h);
        } catch (com.google.zxing.WriterException e) {
            e.printStackTrace();
        }
        java.nio.file.Path codeImgPath = Paths.get(filePath);
        try {
            MatrixToImageWriter.writeToPath(matrix, "PNG", codeImgPath);
            logger.info("CodeUtil encode() printing to " + codeImgPath.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    // 二维码解析
//    public static void decode(String file) {
//        try {
//            Result result = null;
//            BufferedImage image = null;
//            image = ImageIO.read(new File(file));
//            LuminanceSource source = new BufferedImageLuminanceSource(image);
//            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//            Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
//            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
//            result = new MultiFormatReader().decode(bitmap, hints);
//            String rtn = result.getText();
//            System.out.println(rtn);
//            System.out.println(rtn.length());
//        } catch (Exception ex) {
//            System.out.println(ex.toString());
//        }
//    }
}
