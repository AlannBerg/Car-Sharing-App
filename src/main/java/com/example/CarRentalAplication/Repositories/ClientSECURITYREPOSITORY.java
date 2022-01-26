package com.example.CarRentalAplication.Repositories;


import com.example.CarRentalAplication.models.Clientsecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;


@Repository
public interface ClientSECURITYREPOSITORY extends JpaRepository<Clientsecurity,Integer> {

    @Query("SELECT client from Clientsecurity client where client.username = :name")
    Clientsecurity findByClientName(String name);

}
