package com.example.CarRentalAplication;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;


public class DateValidator {
    private final HashMap<Date,Date> startEndMap;


    public DateValidator(HashMap<Date, Date> startEndMap) {
        this.startEndMap = startEndMap;
    }

    public Boolean validateDate(Date requestedSTART, Date requestedEND) {
        Boolean validationResult = false;


        // checks if start and end of rental is between some other term
        if( validateDate(requestedSTART) || validateDate(requestedEND)){
            validationResult = true;
        }


        // in case requested term is longer than all scheduled terms
        // some other term         3   4   5
        // requested  term 1  2   3   4   5   6   7
        // return false
        for (Map.Entry<Date,Date> entry : startEndMap.entrySet()){

            Date startTerm = entry.getKey();
            Date endTerm = entry.getValue();

            if( requestedSTART.compareTo(startTerm) < 0 && requestedEND.compareTo(endTerm) > 0){
                validationResult = true;
                break;
            }
        }

        return validationResult;
    }

    public Boolean validateDate(Date requestedDate) {
        Boolean validationResult = false;

        for (Map.Entry<Date,Date> entry : startEndMap.entrySet()){

            Date startTerm = entry.getKey();
            Date endTerm = entry.getValue();

            // if day of new rental term is at the same day as others
            // return false
            if(requestedDate.compareTo(startTerm) == 0 || requestedDate.compareTo(endTerm) == 0){
                validationResult = true;
                break;
            }

            // if day of new rental term is between some other term
            // return false
            if(requestedDate.compareTo(startTerm) > 0 && requestedDate.compareTo(endTerm) < 0){
                validationResult = true;
                break;
            }
        }
        return validationResult;
    }
}
