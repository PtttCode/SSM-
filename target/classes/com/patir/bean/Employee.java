package com.patir.bean;

public class Employee {

    private Integer empId;
    private String empName;
    private String empEmail;
    private String gender;
    private Integer departmentId;

    private Department department;

    public String getEmpEmail() {
        return empEmail;
    }
    public void setEmpEmail(String empEmail){
        this.empEmail=empEmail;
    }

    public Integer getEmpId() {
        return empId;
    }
    public void setEmpId(Integer empId) {
        this.empId=empId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString()
    {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empEmail='" + empEmail + '\'' +
                ", gender='" + gender + '\'' +
                ", departmentId=" + departmentId + '\'' +
                ", Department=" + department +
                '}';
    }

    public Employee(){
    }

    public Employee(Integer empId, String empName, String empEmail, String gender, Integer departmentId) {
        this.empId = empId;
        this.empName = empName;
        this.empEmail = empEmail;
        this.gender = gender;
        this.departmentId = departmentId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
