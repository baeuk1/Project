package com.example.user.lottemart;

/**
 * Created by user on 2016-07-11.
 */
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by user on 2016-07-06.
 */
public class CustomerDataLoader {
    private static final int MAXCUSTOMER = 100;
    private Context context;
    private Customer[] customers;
    private int numOfCustomers = 0;
    private String tempStr; // before parse
    private String[] customerData; // Data of one customer before parsing (ex : kim26#1234#male#32)
    private String[] oneCustomer; // after parse
    private InputStream fis;
    private int idIndex = -1;

    public CustomerDataLoader(Context context, InputStream fis){
        this.context = context;
        this.fis = fis;
        customers = new Customer[MAXCUSTOMER];
        customerData = new String[MAXCUSTOMER];
        oneCustomer = new String[Customer.numOfProperty];
        Log.d("Baeuk", "created");
        parser(context);
    }
    public int isIdValid(String id){
        for(int i=0; i<numOfCustomers; i++){
            if(id.equals(customers[i].getId())){
                idIndex = i;
                return idIndex;
            }
        }
        return -1;
    }
    public boolean isPwCorrect(String pw, int idIndex){
        if(idIndex == -1) return false;
        if(pw.equals(customers[idIndex].getPw())) return true;
        else return false;
    }
    public int getIdIndex(){
        return idIndex;
    }
    private int parser(Context context){
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(fis,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            int i=0;
            while((tempStr = bufferedReader.readLine())!=null){

                customerData[numOfCustomers++] = tempStr;
                Log.d("efg", customerData[i++]);
            }

            fis.close();
            bufferedReader.close();

        } catch (IOException e){
            Toast.makeText(context,"Failed to Load File",Toast.LENGTH_SHORT).show();
        }
        for(int i=0; i<numOfCustomers; i++) {
            oneCustomer = customerData[i].split("#");
            Log.d("customer",oneCustomer[0]);
            /*
            customers[i] = new Customer();
            customers[i].setId(oneCustomer[0]);
            customers[i].setPw(oneCustomer[1]);
            customers[i].setName(oneCustomer[2]);
            customers[i].setGender(oneCustomer[3]);
            customers[i].setAge(Integer.parseInt(oneCustomer[4]));
            customers[i].setRegion(oneCustomer[5]);
            customers[i].setJob(oneCustomer[6]);
            customers[i].setJoinDate(Date.valueOf(oneCustomer[7]));
            customers[i].setLevel(oneCustomer[8]);
            */
        }
        return numOfCustomers;
    }
}