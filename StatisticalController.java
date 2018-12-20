package com.hwua.crm.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwua.crm.entity.Customer;
import com.hwua.crm.entity.Role;
import com.hwua.crm.entity.Service;
import com.hwua.crm.service.RoleService;
import com.hwua.crm.service.StatisticsService;
import com.hwua.crm.util.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/statistics")
public class StatisticalController {
    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping("/Statisticscontribution")
    public String index(){
        return "Statistics/Statisticscontribution";
    }

    @RequestMapping("/Statisticsconstitute")
    public String Statisticsconstitute(){
        return "Statistics/Statisticsconstitute";
    }

    @RequestMapping("/Statisticsservice")
    public String Statisticsservice(){
        return "Statistics/Statisticsservice";
    }

    @RequestMapping("/StatisticsLoss")
    public String StatisticsLoss(){
        return "Statistics/StatisticsLoss";
    }

    @RequestMapping(value = "/querystatistics",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String querystatistics( int page , int rows){
        //查询之前开始分页
        Page pp=PageHelper.startPage(page,rows);
        List<Map<String,Object>> list=statisticsService.querystatistics();
        //查询之后获取总条数
        long total=pp.getTotal();
        JSONObject obj= new JSONObject();
        obj.put("total",total);
        JSONArray array=(JSONArray) JSONArray.toJSON(list);
        obj.put("rows",array);
        return obj.toJSONString();
    }


    @RequestMapping(value = "/queryconstitute",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryconstitute( int page , int rows){
        //查询之前开始分页
        Page pp=PageHelper.startPage(page,rows);
        List<Map<String,Object>> list=statisticsService.queryconstitute();
        //查询之后获取总条数
        long total=pp.getTotal();
        JSONObject obj= new JSONObject();
        obj.put("total",total);
        JSONArray array=(JSONArray) JSONArray.toJSON(list);
        obj.put("rows",array);
        return obj.toJSONString();
    }

    @RequestMapping(value = "/querystatisticsService",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String querystatisticsService(Service service, int page , int rows){
        System.err.println("------------------------------"+service);
        //查询之前开始分页
        Page pp=PageHelper.startPage(page,rows);
        List<Map<String,Object>> list=statisticsService.querystatisticsService(service);
        //查询之后获取总条数
        long total=pp.getTotal();
        JSONObject obj= new JSONObject();
        obj.put("total",total);
        JSONArray array=(JSONArray) JSONArray.toJSON(list);
        obj.put("rows",array);
        return obj.toJSONString();
    }

    @RequestMapping(value = "/querystatisticsLoss",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String querystatisticsLoss(Customer customer, int page , int rows){
        //查询之前开始分页
        Page pp=PageHelper.startPage(page,rows);
        List<Map<String,Object>> list=statisticsService.querystatisticsLoss(customer);
        //查询之后获取总条数
        long total=pp.getTotal();
        JSONObject obj= new JSONObject();
        obj.put("total",total);
        JSONArray array=(JSONArray) JSONArray.toJSON(list);
        obj.put("rows",array);
        return obj.toJSONString();
    }

}
