package com.example.CarRentalAplication.Security;
import com.example.CarRentalAplication.Repositories.ClientSECURITYREPOSITORY;
import com.example.CarRentalAplication.models.Clientsecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;


@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private ClientSECURITYREPOSITORY clientSECURITYREPOSITORY;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Clientsecurity usersec = clientSECURITYREPOSITORY.findByClientName(username);

        if (usersec == null) {
            throw new UsernameNotFoundException(username);
        }

        return new MyUserDetails(usersec);
    }




}
