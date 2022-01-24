package com.example.CarRentalAplication.Repositories;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    private ClientRepository clientRepository;
    @Test
    void findByID() {
    }

    @Test
    void saveClient() {
    }

    @Test
    void findByEmail() {
    }
}