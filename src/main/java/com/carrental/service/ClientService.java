package com.carrental.service;

import com.carrental.model.Client;
import com.carrental.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public void addClient(Client client) {
        PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        clientRepository.save(client);
    }

    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Transactional
    public boolean isEmailInUse(String email){
        boolean emailInUse = true;
        if (clientRepository.findByEmail(email) == null) {
            emailInUse = false;
        }
        return emailInUse;
    }

}
