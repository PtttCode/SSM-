package com.patir.controller;

import com.patir.bean.Employee;
import com.patir.service.EmployeeService;
import com.patir.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/patir/emp")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/deleteEmp/{empId}",method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMsg deleteEmp(@PathVariable("empId") Integer empId)
    {
        int num=0;
        if(empId>0){
            num=employeeService.deleteEmpById(empId);
        }
        if(num!=1)
        {
            return JsonMsg.fail().addInfo("EmpDeleteError","员工删除异常");
        }
        return JsonMsg.success();
    }


    @RequestMapping(value = "/updateEmp/{empId}",method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg updateEmp(@PathVariable("empId") Integer empId, Employee employee)
    {
        int num=employeeService.updateEmpById(empId,employee);
        if(num!=1) {
            return JsonMsg.fail().addInfo("EmpDeleteError","员工删除异常");
        }
        return JsonMsg.success();
    }

    @RequestMapping(value = "/checkEmpList",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg checkEmpExist(@PathVariable("empName") String empName) {
        String corrName="(^[a-zA-Z0-9_-]{3,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if(!empName.matches(corrName)) {
            return JsonMsg.fail().addInfo("nameError","请输入姓名为2-5位中文或6-16位英文和数字组合");
        }
        if(employeeService.getEmpByName(empName)!=null){
            return JsonMsg.fail().addInfo("nameError","用户名重复");
        }
        return JsonMsg.success();
    }

    @RequestMapping(value = "/getTotalPages", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getTotalPage(){
        int totalItems = employeeService.getEmpCount();
        //获取总的页数
        int temp = totalItems / 5;
        int totalPages = (totalItems % 5 == 0) ? temp : temp+1;
        return JsonMsg.success().addInfo("totalPages", totalPages);
    }

    @RequestMapping(value = "/addEmp", method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg addEmp(Employee employee){
        int res = employeeService.InsertEmp(employee);
        if (res == 1){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }

    @RequestMapping(value = "/getEmpById/{empId}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getEmpById(@PathVariable("empId") Integer empId){
        Employee employee=employeeService.getEmpById(empId);
        if(employee!=null) {
            return JsonMsg.success().addInfo("getEmp",employee);
        }
        else{
            return JsonMsg.fail();
        }
    }

    @RequestMapping(value = "/getEmpList", method = RequestMethod.GET)
    public ModelAndView getEmp(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo){
        ModelAndView mv = new ModelAndView("employeePage");
        int limit = 5;
        // 记录的偏移量(即从第offset行记录开始查询)，
        // 如第1页是从第1行(offset=(21-1)*5=0,offset+1=0+1=1)开始查询；
        // 第2页从第6行(offset=(2-1)*5=5,offset+1=5+1=6)记录开始查询
        int offset = (pageNo-1)*limit;
        //获取指定页数包含的员工信息
        List<Employee> employees = employeeService.getEmpList(offset, limit);
        //获取总的记录数
        int totalItems = employeeService.getEmpCount();
        //获取总的页数
        int temp = totalItems / limit;
        int totalPages = (totalItems % limit == 0) ? temp : temp+1;
        //当前页数
        int curPage = pageNo;

        //将上述查询结果放到Model中，在JSP页面中可以进行展示
        mv.addObject("employees", employees)
                .addObject("totalItems", totalItems)
                .addObject("totalPages", totalPages)
                .addObject("curPage", curPage);
        return mv;
    }
}
