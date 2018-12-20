package com.hwua.crm.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwua.crm.entity.Role;
import com.hwua.crm.entity.Sales;
import com.hwua.crm.service.SalesService;
import com.hwua.crm.serviceImpl.SalesServiceImpl;
import com.hwua.crm.util.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sales")
public class SalesController {
    @Autowired
    private SalesService salesService;
    @RequestMapping("/querysales")
    public String querysales(){
        //WEB-INF/view/user/user.jsp
        return "sales/sales";
    }
    @RequestMapping("/querysales2")
    public String querysales2(){
        //WEB-INF/view/user/user.jsp
        return "sales/sales2";
    }
    @RequestMapping("/buyplan")
    public String buyplan(){
        //WEB-INF/view/user/user.jsp
        return "sales/buyplan";
    }
    //String buyusername,int successid,String outline,String buysource,String Contacts,String Contactsphone,String Opportunity,String Establishdata
    @RequestMapping(value= "/Addbuyplan",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String Addbuyplan(Sales sales){
        System.out.println(sales);
        int a=salesService.addBuyplan(sales);
        UpdateResult ur=null;
        if(a==1){
            ur=new UpdateResult(true,"操作成功！");
        }else{
            ur=new UpdateResult(false,"操作失败！");
        }

        return ur.toJSONString();
    }
    @RequestMapping(value = "/querySales",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String querySales(Sales sales, int page , int rows){
        //查询之前开始分页
        Page pp=PageHelper.startPage(page,rows);

        List<Map<String,Object>> list=salesService.querySales(sales);
        System.err.println(list);
        //查询之后获取总条数
        long total=pp.getTotal();
        JSONObject obj= new JSONObject();
        obj.put("total",total);
        JSONArray array=(JSONArray) JSONArray.toJSON(list);
        obj.put("rows",array);
        return obj.toJSONString();

    }
    @RequestMapping(value = "/querySales2",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String querySales2(Sales sales, int page , int rows){
        //查询之前开始分页
        Page pp=PageHelper.startPage(page,rows);

        List<Map<String,Object>> list=salesService.querySales2(sales);
        System.err.println(list);
        //查询之后获取总条数
        long total=pp.getTotal();
        JSONObject obj= new JSONObject();
        obj.put("total",total);
        JSONArray array=(JSONArray) JSONArray.toJSON(list);
        obj.put("rows",array);
        return obj.toJSONString();

    }
    @RequestMapping(value = "/saveOrUpdateSales",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String saveOrUpdateSales(Sales sales){
        int a=salesService.updateSales(sales);
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
    @RequestMapping(value = "/queryUsername",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryUsername(){
        List<Map<String,Object>> users=salesService.queryUsername();
        System.err.println(users);
        JSONArray array=(JSONArray)JSONArray.toJSON(users);
        JSONObject result= new JSONObject();
        return array.toJSONString();
    }
    /*@RequestMapping(value = "/queryPlan",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryPlan(String Assignor, HttpSession session){
        String username=(String)session.getAttribute("username");
        List<Map<String,Object>> plan=salesService.queryPlan(username);
        System.err.println(plan);
        JSONArray array=(JSONArray)JSONArray.toJSON(plan);
        JSONObject result= new JSONObject();
        return array.toJSONString();
    }*/
    @RequestMapping(value = "/queryPlan",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryPlan(String salesid, HttpSession session){
        List<Map<String,Object>> plan=salesService.queryPlan(salesid);
        System.err.println(plan);
        JSONArray array=(JSONArray)JSONArray.toJSON(plan);
        return array.toJSONString();
    }
    @RequestMapping(value= "/addplans",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addplans(Sales sales){
        System.out.println(sales);
        int a=salesService.addplans(sales);
        UpdateResult ur=null;
        if(a==1){
            ur=new UpdateResult(true,"操作成功！");
        }else{
            ur=new UpdateResult(false,"操作失败！");
        }

        return ur.toJSONString();
    }
}
