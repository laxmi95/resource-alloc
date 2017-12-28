package com.example.laxmi.assessmentproject;


public class Employee {

    private int empID;
    private String empName,emailID,unit,phoneNumber;

    public Employee(int empID, String empName, String emailID, String unit, String phoneNumber) {
        this.empID = empID;
        this.empName = empName;
        this.emailID = emailID;
        this.unit = unit;
        this.phoneNumber = phoneNumber;
    }

    public Employee(String empName, String emailID, String unit, String phoneNumber) {
        this.empName = empName;
        this.emailID = emailID;
        this.unit = unit;
        this.phoneNumber = phoneNumber;
    }

    public Employee(){

    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

