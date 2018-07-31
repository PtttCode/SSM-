package com.patir.mapper;

import com.patir.bean.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DepartmentMapper {

    String TableName="tbl_dept";
    String InsertFields="dept_name,dept_leader";
    String SelectFields="dept_id as 'deptId' , " +
            "dept_name as 'deptName' , " +
            "dept_leader as 'deptLeader'";

    /*******************************删除**********************************/

    @Delete({"Delete From",TableName,"Where dept_id=#{deptId}"})
    int deleteById(@Param("dept_id") Integer deptId);


    int updateDeptById(@Param("deptId") Integer deptId,
                       @Param("department") Department department);

    /*******************************插入**********************************/
    @Insert({"Update From",TableName,"(",InsertFields,")"+
                                            "Values(#{deptName},"+
                                            "{#deptLeader})"})
    int insetDept(@Param("department")  Department department);


    /**
     * =================================查询============================================
     */
    @Select({"SELECT", SelectFields, "FROM", TableName, "WHERE dept_id=#{deptId}" })
    Department selectOneById(@Param("deptId") Integer deptId);
    @Select({"SELECT", SelectFields, "FROM", TableName, "WHERE dept_leader=#{deptLeader}" })
    Department selectOneByLeader(@Param("deptLeader") String deptLeader);
    @Select({"SELECT", SelectFields, "FROM", TableName, "WHERE dept_name=#{deptName}" })
    Department selectOneByName(@Param("deptName") String deptName);
    @Select({"SELECT", SelectFields, "FROM", TableName})
    List<Department> selectDeptList();

    List<Department> selectDeptsByLimitAndOffset(@Param("offset") Integer offset,
                                                 @Param("limit") Integer limit);

    /*************************指定上司和部门名查询************************/
    @Select({"SELECT COUNT(dept_id) FROM", TableName,
            "WHERE deptLeader = #{deptLeader} OR deptName = #{deptName}"})
    int checkDeptsExistsByNameAndleader(@Param("deptLeader") String deptLeader,
                                        @Param("deptName") String deptName);

    /*************************部门总数************************/
    @Select({"SELECT COUNT(*) FROM", TableName})
    int countDepts();
}
