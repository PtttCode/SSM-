package com.patir.mapper;

import com.patir.bean.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EmployeeMapper {

    String TableName="tbl_emp";
    String InsertFields="emp_name,emp_email,gender,department_id";
    String SelectFields="emp_id,"+InsertFields;

    /*********************************删除****************************************/
    @Delete({"Delete From",TableName,"Where emp_id=#{empId}"})
    int deleteOneById(@Param("empId") Integer empId);

    /*********************************修改****************************************/
    int updateOneById(@Param("empId") Integer empId,@Param("employee") Employee employee);

    /*********************************插入****************************************/
    @Insert({"Update From",TableName,"(",InsertFields,")"+
            "Values(#{empName},"+
            "#{empEmail},"+
            "#{gender},"+
            "#{departmentId})"})
    int insertOne(Employee employee);


    /*********************************查询****************************************/

    Employee selectOneById(@Param("empId") Integer empId);
    Employee selectOneByName(@Param("empName") String name);
    Employee selectWithDeptById(@Param("empId") Integer empId);

    /**
     * 分页查询
     * @param limit 返回记录最大行数
     * @param offset 返回记录行的偏移量
     * @return 如offset=10，limit=5的时候，就会从数据库第11行记录开始返回5条查询结果，即范围从(offset+1)---(offset+limit)
     */
    List<Employee> selectByLimitAndOffset(@Param("offset") Integer offset,@Param("limit") Integer limit);


    @Select({"Select * From",TableName})
    int total();


}
