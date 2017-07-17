package com.example.surajmalviya.schoolbuddy.utility;

import android.content.Intent;
import android.util.Log;

/**
 * Created by Suraj Malviya on 13/06/2017.
 */

public class GeneralValidation {

    public static boolean ensureFullName(String input){

        input=input.trim();
        for(int i=0;i<input.length();i++){
            if(input.charAt(i)==' '){
                return true;
            }
        }
        return false;
    }

    public static boolean isValidContactNumber(String number){
        return number.length()==10;
    }

    public static boolean isValidDateOfBirth(String dob){
        dob=dob.trim();
        if(dob.length()==10){
            boolean send=false;
            if(dob.charAt(2)=='/' && dob.charAt(5)=='/'){
                try{
                    int day= Integer.parseInt(dob.substring(0,2));
                    int month=Integer.parseInt(dob.substring(3,5));
                    int year=Integer.parseInt(dob.substring(6,10));
                    send = true;
                }
                catch(NumberFormatException nfe){
                    send=false;
                }
            }
            return send;
        }else{
            return false;
        }
    }
}
