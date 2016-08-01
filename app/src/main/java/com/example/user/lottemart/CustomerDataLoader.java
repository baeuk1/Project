package com.example.user.lottemart;

/**
 * Created by user on 2016-07-11.
 */
import android.util.Log;

/**
 * Created by user on 2016-07-06.
 */
public class CustomerDataLoader {
    private final int maxCustomer = 240;
    private Customer[] customerList;

    public CustomerDataLoader(){
        customerList = new Customer[maxCustomer];
        getCustomerDatas();
    }

    private void getCustomerDatas(){
        for(int i=0; i<2; i++)
            for(int j=0; j<6; j++)
                for(int k=0; k<5; k++)
                    for(int l=0; l<4; l++){
                        String id = "id"+String.valueOf(i+1)+String.valueOf(j+1)+String.valueOf(k+1)+String.valueOf(l+1);
                        String gender = intToBoolean(i) ? "여" : "남";
                        int age = (j+1)*10 + (i + k + l) % 10;
                        String area;
                        String job;
                        switch(k){
                            case 0 : area = "서울"; break;
                            case 1 : area = "경기"; break;
                            case 2 : area = "강원"; break;
                            case 3 : area = "전라"; break;
                            case 4 : area = "경상"; break;
                            default: area = ""; break;
                        }
                        switch (l){
                            case 0 : job = "학생"; break;
                            case 1 : job = "회사원"; break;
                            case 2 : job = "공무원"; break;
                            case 3 : job = "자영업"; break;
                            default: job = ""; break;
                        }
                        int index = i*120 + j*20 + k*4 + l;
                        customerList[index] = new Customer(id,gender,age,area,job);
                    }
    }
    private boolean intToBoolean(int i){
        if(i==0) return false;
        else return true;
    }
    public int isIdValid(String id){
        for(int i=0; i< maxCustomer; i++){
            if(customerList[i].getId().equals(id)) return i;
        }
        return -1;
    }
    public String getGender(int customerIndex){
        return customerList[customerIndex].getGender();
    }
    public int getAge(int customerIndex){
        return customerList[customerIndex].getAge();
    }
    public String getArea(int customerIndex){
        return customerList[customerIndex].getArea();
    }
    public String getJob(int customerIndex){
        return customerList[customerIndex].getJob();
    }
}