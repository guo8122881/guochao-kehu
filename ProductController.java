package com.hwua.crm.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwua.crm.entity.*;
import com.hwua.crm.service.ProductService;
import com.hwua.crm.service.StatisticsService;
import com.hwua.crm.util.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/productquery")
    public String index(){
        return "product/productquery";
    }

    @RequestMapping("/productStock")
    public String productStock(){
        return "product/productStock";
    }

    @RequestMapping("/productDictionaries")
    public String productDictionaries(){
        return "product/productDictionaries";
    }

    @RequestMapping(value = "/queryproduct",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryproduct(Product product, int page , int rows){
        //查询之前开始分页
        Page pp=PageHelper.startPage(page,rows);
        List<Map<String,Object>> list=productService.queryproduct(product);
        //查询之后获取总条数
        long total=pp.getTotal();
        JSONObject obj= new JSONObject();
        obj.put("total",total);
        JSONArray array=(JSONArray) JSONArray.toJSON(list);
        obj.put("rows",array);
        return obj.toJSONString();
    }


    @RequestMapping(value = "/queryStock",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryStock(ProductStock productStock, int page , int rows){
        //查询之前开始分页
        Page pp=PageHelper.startPage(page,rows);
        List<Map<String,Object>> list=productService.queryStock(productStock);
        //查询之后获取总条数
        long total=pp.getTotal();
        JSONObject obj= new JSONObject();
        obj.put("total",total);
        JSONArray array=(JSONArray) JSONArray.toJSON(list);
        obj.put("rows",array);
        return obj.toJSONString();
    }

    @RequestMapping(value = "/queryAlldata",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryAlldata( int page , int rows){
        //查询之前开始分页
        Page pp=PageHelper.startPage(page,rows);
        List<Map<String,Object>> list=productService.queryAlldata();
        //查询之后获取总条数
        long total=pp.getTotal();
        JSONObject obj= new JSONObject();
        obj.put("total",total);
        JSONArray array=(JSONArray) JSONArray.toJSON(list);
        obj.put("rows",array);
        return obj.toJSONString();
    }


    @RequestMapping(value = "/queryGroupby",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryGroupby(){
        List<Map<String,Object>> users=productService.queryGroupby();
        System.err.println(users);
        JSONArray array=(JSONArray)JSONArray.toJSON(users);
        JSONObject result= new JSONObject();
        return array.toJSONString();
    }

    @RequestMapping(value= "/insertAndupdate",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String insertAndupdate(DataType dataType) {
        System.out.println(dataType);
        int a = productService.insertAndupdate(dataType);
        UpdateResult ur = null;
        if (a == 1) {
            ur = new UpdateResult(true, "操作成功！");
        } else {
            ur = new UpdateResult(false, "操作失败！");
        }

        return ur.toJSONString();
    }



    @RequestMapping(value= "/deltype",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deltype(DataType dataType) {
        System.out.println(dataType);
        int a = productService.deltype(dataType);
        UpdateResult ur = null;
        if (a == 1) {
            ur = new UpdateResult(true, "操作成功！");
        } else {
            ur = new UpdateResult(false, "操作失败！");
        }

        return ur.toJSONString();

    }
}

