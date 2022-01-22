package com.example.CarRentalAplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DateValidatorTest {

    private final HashMap<Date,Date> startEndMap = new HashMap<>();

    private DateValidator dateValidator;


    @Test
    void validateDateExaclyTheSameTermsShouldReturnTrue() {
        Date starting = Date.valueOf("2000-01-01");
        Date ending = Date.valueOf("2000-01-03");

        startEndMap.put(starting , ending);

        DateValidator dateValidator = new DateValidator(startEndMap);

        assertTrue(dateValidator.validateDate(starting,ending));

    }

    @Test
    void testValidateDateStartingDateBetweenOtherDateSholdReturTrue() {
        Date starting = Date.valueOf("2000-01-01");
        Date ending = Date.valueOf("2000-01-03");

        Date clientRequestedStarting = Date.valueOf("2000-01-02");
        Date clientRequestedEnding = Date.valueOf("2000-01-06");

        startEndMap.put(starting , ending);

        DateValidator dateValidator = new DateValidator(startEndMap);

        assertTrue(dateValidator.validateDate(clientRequestedStarting,clientRequestedEnding));
    }

    @Test
    void testValidateDateEndingDateBetweenOtherDateSholdReturTrue() {
        Date starting = Date.valueOf("2000-01-01");
        Date ending = Date.valueOf("2000-01-03");

        Date clientRequestedStarting = Date.valueOf("1999-12-25");
        Date clientRequestedEnding = Date.valueOf("2000-01-02");

        startEndMap.put(starting , ending);

        DateValidator dateValidator = new DateValidator(startEndMap);

        assertTrue(dateValidator.validateDate(clientRequestedStarting,clientRequestedEnding));
    }

    @Test
    void testValidateDateRequestedTermLongerThanOtherSholdReturTrue() {
        Date starting = Date.valueOf("2000-01-01");
        Date ending = Date.valueOf("2000-01-03");

        Date clientRequestedStarting = Date.valueOf("1999-12-25");
        Date clientRequestedEnding = Date.valueOf("2000-02-10");

        startEndMap.put(starting , ending);

        DateValidator dateValidator = new DateValidator(startEndMap);

        assertTrue(dateValidator.validateDate(clientRequestedStarting,clientRequestedEnding));
    }

    @Test
    void testValidateDateAllGoodSholdReturFalse() {
        Date starting = Date.valueOf("2000-01-01");
        Date ending = Date.valueOf("2000-01-03");

        Date clientRequestedStarting = Date.valueOf("2000-01-10");
        Date clientRequestedEnding = Date.valueOf("2000-01-15");

        startEndMap.put(starting , ending);

        DateValidator dateValidator = new DateValidator(startEndMap);

        assertFalse(dateValidator.validateDate(clientRequestedStarting,clientRequestedEnding));
    }



}