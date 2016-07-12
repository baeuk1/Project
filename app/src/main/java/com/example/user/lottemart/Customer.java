package com.example.user.lottemart;

import java.util.Date;

/**
 * Created by user on 2016-07-11.
 */
public class Customer {
    public static int numOfProperty = 9;
    private String id;
    private String pw;
    private String name;
    private String gender;
    private int age;
    private String region;
    private String job;
    private Date joinDate;
    private String level;

    public void setId(String id){
        this.id = id;
    }
    public void setPw(String pw){
        this.pw = pw;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setRegion(String region){
        this.region = region;
    }
    public void setJob(String job){
        this.job = job;
    }
    public void setJoinDate(Date joinDate){
        this.joinDate = joinDate;
    }
    public void setLevel(String level){
        this.level = level;
    }
    public String getId(){
        return id;
    }
    public String getPw(){
        return pw;
    }
}