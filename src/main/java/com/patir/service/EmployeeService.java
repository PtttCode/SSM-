package com.patir.service;

import com.patir.bean.Employee;
import com.patir.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    public int getEmpCount(){
        return employeeMapper.total();
    }
    public List<Employee> getEmpList(Integer offset,Integer limit){
        return employeeMapper.selectByLimitAndOffset(offset,limit);
    }
    public Employee getEmpById(Integer empId){
        return employeeMapper.selectOneById(empId);
    }
    public Employee getEmpByName(String empName){
        return employeeMapper.selectOneByName(empName);
    }
    public int updateEmpById(Integer empId,Employee employee){
        return employeeMapper.updateOneById(empId,employee);
    }
    public int deleteEmpById(Integer empId){
        return employeeMapper.deleteOneById(empId);
    }
    public int InsertEmp(Employee employee){
        return employeeMapper.insertOne(employee);
    }
}
