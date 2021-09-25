package com.example.madproject;

public class StudentModel {
    private String id;
    private String fname,lname,address,gender,pnumber,parentPnumber;
    private long started, finished;


    public StudentModel(String id,String fname,String lname,String address,String gender,String pnumber,String parentPnumber,long started, long finished) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.gender = gender;
        this.pnumber = pnumber;
        this.parentPnumber = parentPnumber;
        this.started = started;
        this.finished = finished;
    }


    public StudentModel() {

    }


    public String getStudentId() {
        return id;
    }

    public void setStudentId(String id) {
        this.id = id;
    }

    public String getStudentFname() {
        return fname;
    }

    public void setStudentFname(String fname) {
        this.fname = fname;
    }

    public String getStudentLname() {
        return lname;
    }

    public void setStudentLname(String lname) {
        this.lname = lname;
    }

    public String getStudentAddress() {
        return address;
    }

    public void setStudentAddress(String address) {
        this.address = address;
    }

    public String getStudentGender() {
        return gender;
    }

    public void setStudentGender(String gender) {
        this.gender = gender;
    }

    public String getStudentnumber() {
        return pnumber;
    }

    public void setStudentnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getParentnumber() {
        return parentPnumber;
    }

    public void setParentnumber(String parentPnumber) {
        this.parentPnumber = parentPnumber;
    }

    public long getStarted() {
        return started;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }


}




















