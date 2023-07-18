package com.szcgc.wechat.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * @Author: chenxinli
 * @Date: 2020/6/3 16:02
 * @Description:
 */
public class CustomJsonLocalDateDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException{
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = jp.getText();
        if(!date.trim().isEmpty()){
            return LocalDate.parse(date);
        }else{
            return null;
        }
    }

}
