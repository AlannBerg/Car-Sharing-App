package com.example.CarRentalAplication.QueryBuilder;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
public class Querybuilder {
    public static String buildQuery(Optional<Integer> localizationID,Optional<Integer> priceFrom,
                                    Optional<Integer> priceTo, Optional<Integer> hpowerFrom, Optional<Integer> hpowerTo){

        String  basicquery = "SELECT car FROM Car car ";
        List<String> querryFinders = new ArrayList<>();

        localizationID.ifPresent( integer -> querryFinders.add(" car.currentLocation = " + integer));
        priceFrom.ifPresent(integer -> querryFinders.add(" car.rentfee > " + integer));
        priceTo.ifPresent(integer -> querryFinders.add(" car.rentfee < " + integer));
        hpowerFrom.ifPresent(integer -> querryFinders.add(" car.horsepower > " + integer));
        hpowerTo.ifPresent(integer -> querryFinders.add(" car.horsepower < " + integer));

        if(querryFinders.isEmpty()){
            return basicquery;
        }else {
            return querryFinders.stream().collect(Collectors.joining(" AND ", basicquery + " WHERE ", ""));
        }
    }


}
