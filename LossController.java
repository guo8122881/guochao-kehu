package com.hwua.crm.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwua.crm.entity.Contact;
import com.hwua.crm.entity.Customer;
import com.hwua.crm.entity.Loss;
import com.hwua.crm.service.ContactService;
import com.hwua.crm.service.LossService;
import com.hwua.crm.util.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/loss")
public class LossController {
    @Autowired
    private LossService lossService;
    @RequestMapping("/index")
    public String index(){
        return "loss/loss";
    }

    @RequestMapping(value = "/queryLoss",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryLoss(int page , int rows){
        /*int customerid2 =Integer.valueOf(customerid);*/
        /* int customerid2 = Integer.parseInt(customerid);*/
        //查询之前开始分页
        Page pp=PageHelper.startPage(page,rows);

        List<Map<String,Object>> list=lossService.queryLoss();
        System.err.println("--------------------"+list);
        //查询之后获取总条数
        long total=pp.getTotal();
        JSONObject obj= new JSONObject();
        obj.put("total",total);
        JSONArray array=(JSONArray) JSONArray.toJSON(list);
        obj.put("rows",array);
        return obj.toJSONString();

    }
    @RequestMapping(value= "/addLoss",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addLoss(Loss loss) {
        int a = lossService.addLoss(loss);
        UpdateResult ur = null;
        if (a == 1) {
            ur = new UpdateResult(true, "操作成功！");
        } else {
            ur = new UpdateResult(false, "操作失败！");
        }

        return ur.toJSONString();

    }
    @RequestMapping(value = "/queryLosscontent",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryLosscontent(String customerid, int page , int rows){
        /*int customerid2 =Integer.valueOf(customerid);*/
        /* int customerid2 = Integer.parseInt(customerid);*/
        //查询之前开始分页
        Page pp=PageHelper.startPage(page,rows);

        List<Map<String,Object>> list=lossService.queryLosscontent(customerid);
        System.err.println("-------------------------"+list);
        //查询之后获取总条数
        long total=pp.getTotal();
        JSONObject obj= new JSONObject();
        obj.put("total",total);
        JSONArray array=(JSONArray) JSONArray.toJSON(list);
        obj.put("rows",array);
        return obj.toJSONString();

    }
    @RequestMapping(value= "/updateLoss",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateLoss(Customer customer) {
        int a = lossService.updateLoss(customer);
        UpdateResult ur = null;
        if (a == 1) {
            ur = new UpdateResult(true, "操作成功！");
        } else {
            ur = new UpdateResult(false, "操作失败！");
        }

        return ur.toJSONString();

    }

}
