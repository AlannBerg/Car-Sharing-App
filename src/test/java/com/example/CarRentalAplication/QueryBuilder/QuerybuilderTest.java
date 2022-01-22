package com.example.CarRentalAplication.QueryBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class QuerybuilderTest {
    private Querybuilder querybuilder;

    @BeforeEach
    void setUp(){
         querybuilder = new Querybuilder();
    }
    @Test
    void buildQuerywithAllParams() {
        Optional<Integer> localizationID = Optional.of(1);
        Optional<Integer> priceFrom = Optional.of(10);
        Optional<Integer> priceTo = Optional.of(100);
        Optional<Integer> hpowerFrom = Optional.of(20);
        Optional<Integer> hpowerTo= Optional.of(200);

        String buildetQuery = "SELECT car FROM Car car  WHERE  car.currentLocation = 1"+
                " AND  car.rentfee > 10 AND  car.rentfee < 100 AND  car.horsepower > 20 AND  car.horsepower < 200";

        assertEquals(buildetQuery, Querybuilder.buildQuery(localizationID,priceFrom,priceTo,hpowerFrom,hpowerTo));
    }
}