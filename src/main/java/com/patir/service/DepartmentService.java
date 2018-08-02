package com.patir.service;

import com.patir.bean.Department;
import com.patir.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    public int deleteByDeptId(Integer deptId){
        return departmentMapper.deleteById(deptId);
    }

    public int updateDeptById(Integer deptId, Department department){
        return departmentMapper.updateDeptById(deptId, department);
    }

    public int InsertDept(Department department) {
        return departmentMapper.insertDept(department);
    }

    public Department selectDeptById(Integer deptId){
        return departmentMapper.selectOneById(deptId);
    }

    public Department selectByLeader(String leader) {
        return departmentMapper.selectOneByLeader(leader);
    }

    public Department selectByName(String deptName){
        return departmentMapper.selectOneByName(deptName);
    }

    public int getDeptCounts(){
        return departmentMapper.countDepts();
    }

    public List<Department> selectDeptByLO(Integer offset,Integer limit) {
        return departmentMapper.selectDeptsByLimitAndOffset(offset,limit);
    }

    public List<Department> getDeptName(){
        return departmentMapper.selectDeptList();
    }

    public int selectByNL(String deptName,String leader) {
        return departmentMapper.checkDeptsExistsByNameAndleader(deptName,leader);
    }
}
