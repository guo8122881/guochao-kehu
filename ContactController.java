package com.hwua.crm.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwua.crm.entity.Contact;
import com.hwua.crm.entity.Customer;
import com.hwua.crm.service.ContactService;
import com.hwua.crm.service.CustomerService;
import com.hwua.crm.util.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;
    @RequestMapping(value= "/addContact",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addContact(Contact contact) {
        System.out.println(contact);
        int a = contactService.saveOrUpdateContact(contact);
        UpdateResult ur = null;
        if (a == 1) {
            ur = new UpdateResult(true, "操作成功！");
        } else {
            ur = new UpdateResult(false, "操作失败！");
        }

        return ur.toJSONString();

    }
    @RequestMapping(value = "/queryContactor",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryCustomer(String customerid, int page , int rows){
       /*int customerid2 =Integer.valueOf(customerid);*/
       /* int customerid2 = Integer.parseInt(customerid);*/
        //查询之前开始分页
        Page pp=PageHelper.startPage(page,rows);

        List<Map<String,Object>> list=contactService.queryContactor(customerid);
        //查询之后获取总条数
        long total=pp.getTotal();
        JSONObject obj= new JSONObject();
        obj.put("total",total);
        JSONArray array=(JSONArray) JSONArray.toJSON(list);
        obj.put("rows",array);
        return obj.toJSONString();

    }
    @RequestMapping(value = "/queryGender",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryGender(){
        List<Map<String,Object>> users=contactService.queryGender();
        System.err.println(users);
        JSONArray array=(JSONArray)JSONArray.toJSON(users);
        JSONObject result= new JSONObject();
        return array.toJSONString();
    }
}
