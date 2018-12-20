package com.hwua.crm.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwua.crm.entity.Customer;
import com.hwua.crm.entity.Sales;
import com.hwua.crm.service.CustomerService;
import com.hwua.crm.service.SalesService;
import com.hwua.crm.util.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
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
    @RequestMapping("/index")
    public String buyplan(){
        //WEB-INF/view/user/user.jsp
        return "customer/customer";
    }
    //String buyusername,int successid,String outline,String buysource,String Contacts,String Contactsphone,String Opportunity,String Establishdata

    @RequestMapping(value = "/queryCustomer",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryCustomer(Customer customer, int page , int rows){
        //查询之前开始分页
        Page pp=PageHelper.startPage(page,rows);

        List<Map<String,Object>> list=customerService.queryCustomer(customer);
        System.err.println(list);
        //查询之后获取总条数
        long total=pp.getTotal();
        JSONObject obj= new JSONObject();
        obj.put("total",total);
        JSONArray array=(JSONArray) JSONArray.toJSON(list);
        obj.put("rows",array);
        return obj.toJSONString();

    }
    @RequestMapping(value= "/saveOrUpdateCustomer",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addCustomer(Customer customer){
        System.out.println(customer);
        int a=customerService.saveOrUpdateCustomer(customer);
        UpdateResult ur=null;
        if(a==1){
            ur=new UpdateResult(true,"操作成功！");
        }else{
            ur=new UpdateResult(false,"操作失败！");
        }

        return ur.toJSONString();
    }


}
