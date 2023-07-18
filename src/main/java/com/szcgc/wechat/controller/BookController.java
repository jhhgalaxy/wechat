package com.szcgc.wechat.controller;

import com.szcgc.wechat.smart.entity.BookPurchase;
import com.szcgc.wechat.smart.service.BookService;
import com.szcgc.wechat.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: chenxinli
 * @Date: 2020/7/31 15:27
 * @Description:
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/book")
    public String bookIndex(){
        return "szcgc/book/index";
    }

    @RequestMapping(value = "/book/success")
    public String bookSuccess(){
        return "szcgc/book/success";
    }

    @ResponseBody
    @RequestMapping(value = "/book/purchase")
    public String bookPurchase(BookPurchase bookPurchase) throws IOException {

        Map<String, String> map = new HashMap<String, String>();
        if(bookPurchase!=null){
            BookPurchase rst = bookService.insert(bookPurchase);
            if(rst!=null&&rst.getId()>0){
                map.put("code", "0");
                map.put("msg", "添加成功！");
                return JsonUtil.objToJsonMap(map);
            }

        }
        map.put("code", "-1");
        map.put("msg", "添加失败！");
        return JsonUtil.objToJsonMap(map);
    }

    @ResponseBody
    @RequestMapping(value = "/book/getlist",produces = "application/json; charset=utf-8")
    public List<BookPurchase> pushPurchaseList() throws IOException {
        List<BookPurchase> list = bookService.findUnPushList();
        bookService.setListPushed(list);
        return list;
    }

//    @ResponseBody
//    @RequestMapping(value = "/book/getlisttest",produces = "application/json; charset=utf-8")
//    public String purchaseList(HttpServletResponse response) throws IOException {
//        //response.setContentType("text/html;charset=utf-8");
//        List<BookPurchase> list = bookService.findUnPushList();
//        //bookService.setListPushed(list);
//
//        Map<String,Object> map = new HashMap<>();
//        map.put("data",list);
//        map.put("result","200");
//
//        return JsonUtil.objToJsonMap(map);
//    }
}
