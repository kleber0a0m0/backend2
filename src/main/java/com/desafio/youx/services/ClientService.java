package com.desafio.youx.services;

import com.desafio.youx.models.ClientModel;
import com.desafio.youx.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// Melhoria futuras: criar interface ClientService
@Service
public class ClientService {
    //Ponto de injeção ClientRepository
    @Autowired
    ClientRepository clientRepository;

    public Page<ClientModel> findClients(Pageable pageable){
        return clientRepository.findAll(pageable);
    }

    public List<ClientModel> findClientsAll(){
        return clientRepository.findAll();
    }

    @Transactional
    public ClientModel save(ClientModel clientModel) {
        return clientRepository.save(clientModel);
    }



    public boolean existsByCnpj(String cnpj) {
        return clientRepository.existsByCnpj(cnpj);
    }

    public Page<ClientModel> findByCNPJorName(Pageable pageable, String search) {
        return clientRepository.findByCnpjOrName(pageable,search);
    }

    public Optional<ClientModel> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public void delete(ClientModel clientModel) {
        clientRepository.delete(clientModel);
    }
}

