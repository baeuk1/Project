package com.example.user.lottemart;

/**
 * Created by user on 2016-07-11.
 */
public class Customer {
    private String id;
    private String gender;
    private int age;
    private String area;
    private String job;

    public Customer(String id, String gender, int age, String area, String job){
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.area = area;
        this.job = job;
    }
    public String getId(){
        return id;
    }
    public String getGender() { return gender; }
    public int getAge() { return age; }
    public String getArea() { return area; }
    public String getJob() { return job; }
}