package com.hwua.crm.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwua.crm.entity.Contact;
import com.hwua.crm.service.ContactService;
import com.hwua.crm.service.OrderService;
import com.hwua.crm.util.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping(value = "/queryOrder",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryOrder(String customerid, int page , int rows){
       /*int customerid2 =Integer.valueOf(customerid);*/
       /* int customerid2 = Integer.parseInt(customerid);*/
        //查询之前开始分页
        Page pp=PageHelper.startPage(page,rows);

        List<Map<String,Object>> list=orderService.queryOrder(customerid);
        //查询之后获取总条数
        long total=pp.getTotal();
        JSONObject obj= new JSONObject();
        obj.put("total",total);
        JSONArray array=(JSONArray) JSONArray.toJSON(list);
        obj.put("rows",array);
        return obj.toJSONString();

    }
}
