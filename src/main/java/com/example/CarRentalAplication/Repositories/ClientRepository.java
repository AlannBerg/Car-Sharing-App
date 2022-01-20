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
    private String select = "SELECT client FROM  Client client ";


    public Client findByID(Integer id) {
        return entityManager.createQuery(
                 select +"WHERE client.id = " + id.toString(),
                Client.class).getResultList().get(0);
    }

    public void saveClient(Client client){
        entityManager.persist(client);
    }

    public List<Client> findByEmail(String email) {
        return entityManager.createQuery(
                select + "where client.email = " + email,
                Client.class).getResultList();
    }
}
