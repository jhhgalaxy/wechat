package com.szcgc.test;

import ch.qos.logback.core.util.StringCollectionUtil;
import org.junit.Test;

/**
 * @Author: chenxinli
 * @Date: 2020/8/28 11:17
 * @Description:
 */
public class TestClass {

    @Test
    public void test(){
        String str = "4556364607935616";
        if(str.length()<=4){
            System.out.println(str);
        }else{
            StringBuilder sb = new StringBuilder(str);
            int i = 0;
            for(i = 0;i<sb.length()-4;i++){
                sb.replace(i,i+1,"#");
            }


            System.out.println(sb.toString());
        }

    }
}
