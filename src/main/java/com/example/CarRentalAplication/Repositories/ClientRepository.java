package com.example.CarRentalAplication.Repositories;

import com.example.CarRentalAplication.models.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class ClientRepository {
    private final EntityManager entityManager;


    public List<Client> findByID(Integer id) {
        return entityManager.createQuery(
                "SELECT client FROM  Client client WHERE client.id = " + id.toString(),
                Client.class).getResultList();
    }

    public void saveClient(Client client){
        entityManager.persist(client);
    }

    public List<Client> findByEmail(String email) {
        return entityManager.createQuery(
                "SELECT client FROM  Client client where client.email = " + email,
                Client.class).getResultList();
    }
}
