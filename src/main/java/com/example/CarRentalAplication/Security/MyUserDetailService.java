package com.example.CarRentalAplication.Security;
import com.example.CarRentalAplication.Exceptions.InvalidUserName;
import com.example.CarRentalAplication.Repositories.ClientRepository;
import com.example.CarRentalAplication.models.Client;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;


    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<Client> clients = clientRepository.findByName(username);

        if(clients.isEmpty()){
            throw new InvalidUserName();
        }

        return new MyUserDetails(clients.get(0));
    }




}
