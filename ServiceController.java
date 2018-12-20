package com.hwua.crm.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwua.crm.entity.Role;
import com.hwua.crm.entity.Service;
import com.hwua.crm.service.RoleService;
import com.hwua.crm.service.ServiceService;
import com.hwua.crm.util.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sservice")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @RequestMapping("/index")
    public String index(){
        return "service/service";
    }

    @RequestMapping("/servicehandle")
    public String servicehandle(){
        return "service/servicehandle";
    }
    @RequestMapping("/servicemanage")
    public String servicemanage(){
        return "service/servicemanage";
    }

    @RequestMapping(value= "/addService",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addService(Service service) {

        int a = serviceService.addService(service);
        UpdateResult ur = null;
        if (a == 1) {
            ur = new UpdateResult(true, "操作成功！");
        } else {
            ur = new UpdateResult(false, "操作失败！");
        }

        return ur.toJSONString();

    }
    @RequestMapping(value = "/queryService",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryService(int page , int rows){
        /*int customerid2 =Integer.valueOf(customerid);*/
        /* int customerid2 = Integer.parseInt(customerid);*/
        //查询之前开始分页
        Page pp=PageHelper.startPage(page,rows);

        List<Map<String,Object>> list=serviceService.queryService();
        //查询之后获取总条数
        long total=pp.getTotal();
        JSONObject obj= new JSONObject();
        obj.put("total",total);
        JSONArray array=(JSONArray) JSONArray.toJSON(list);
        obj.put("rows",array);
        return obj.toJSONString();

    }
    @RequestMapping(value = "/updateService",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateService(Service service){
        int a=serviceService.updateService(service);
        UpdateResult ur=null;
        if(a==1){
            ur=new UpdateResult(true,"操作成功！");
        }else if(a==2){
            ur=new UpdateResult(false,"角色名称重复！");
        }else{
            ur=new UpdateResult(false,"操作失败！");
        }
        return ur.toJSONString();

    }
    @RequestMapping(value = "/querymyService",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String querymyService(int page , int rows ,String Assignor){
        /*int customerid2 =Integer.valueOf(customerid);*/
        /* int customerid2 = Integer.parseInt(customerid);*/
        //查询之前开始分页
        Page pp=PageHelper.startPage(page,rows);

        List<Map<String,Object>> list=serviceService.querymyService(Assignor);
        //查询之后获取总条数
        long total=pp.getTotal();
        JSONObject obj= new JSONObject();
        obj.put("total",total);
        JSONArray array=(JSONArray) JSONArray.toJSON(list);
        obj.put("rows",array);
        return obj.toJSONString();
    }
    @RequestMapping(value = "/updateHandleway",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateHandleway(Service service){
        int a=serviceService.updateHandleway(service);
        UpdateResult ur=null;
        if(a==1){
            ur=new UpdateResult(true,"操作成功！");
        }else if(a==2){
            ur=new UpdateResult(false,"角色名称重复！");
        }else{
            ur=new UpdateResult(false,"操作失败！");
        }
        return ur.toJSONString();

    }

    @RequestMapping("/servicefeedback")
    public String servicefeedback(){
        return "service/servicefeedback";
    }
    @RequestMapping("/servicefile")
    public String servicefile(){
        return "service/servicefile";
    }

    @RequestMapping(value = "/queryKeedback",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryKeedback(int page , int rows ,String Assignor){
        /*int customerid2 =Integer.valueOf(customerid);*/
        /* int customerid2 = Integer.parseInt(customerid);*/
        //查询之前开始分页
        Page pp=PageHelper.startPage(page,rows);

        List<Map<String,Object>> list=serviceService.queryKeedback(Assignor);
        //查询之后获取总条数
        long total=pp.getTotal();
        JSONObject obj= new JSONObject();
        obj.put("total",total);
        JSONArray array=(JSONArray) JSONArray.toJSON(list);
        obj.put("rows",array);
        return obj.toJSONString();
    }
    @RequestMapping(value = "/queryAll",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryAll(int page , int rows ,Service service){
        /*int customerid2 =Integer.valueOf(customerid);*/
        /* int customerid2 = Integer.parseInt(customerid);*/
        //查询之前开始分页
        Page pp=PageHelper.startPage(page,rows);

        List<Map<String,Object>> list=serviceService.queryAll(service);
        //查询之后获取总条数
        long total=pp.getTotal();
        JSONObject obj= new JSONObject();
        obj.put("total",total);
        JSONArray array=(JSONArray) JSONArray.toJSON(list);
        obj.put("rows",array);
        return obj.toJSONString();
    }


    @RequestMapping(value = "/updateHandleresult",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateHandleresult(Service service){
        int a=serviceService.updateHandleresult(service);
        UpdateResult ur=null;
        if(a==1){
            ur=new UpdateResult(true,"操作成功！");
        }else if(a==2){
            ur=new UpdateResult(false,"角色名称重复！");
        }else{
            ur=new UpdateResult(false,"操作失败！");
        }
        return ur.toJSONString();
    }

    @RequestMapping(value = "/queryDatatype",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryDatatype(){
        List<Map<String,Object>> users=serviceService.queryDatatype();
        System.err.println(users);
        JSONArray array=(JSONArray)JSONArray.toJSON(users);
        JSONObject result= new JSONObject();
        return array.toJSONString();
    }

}
