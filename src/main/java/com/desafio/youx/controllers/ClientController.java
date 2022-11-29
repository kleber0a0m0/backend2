package com.desafio.youx.controllers;

import com.desafio.youx.dtos.ClientDto;
import com.desafio.youx.models.ClientModel;
import com.desafio.youx.services.ClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600) // permitir acesso de qualquer origem

public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    @RequestMapping("/clients")
    public Page<ClientModel> finsClients(Pageable pageable){
        return clientService.findClients(pageable);
    }

    @GetMapping
    @RequestMapping("/clients/all")
    public List<ClientModel> findClientsAll(){
        return clientService.findClientsAll();
    }

    @PostMapping
    @RequestMapping("/clients/add")
    public ResponseEntity<Object> saveClient(@RequestBody @Valid ClientDto clientDto){

        if(clientService.existsByCnpj(clientDto.getCnpj())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CNPJ já cadastrado");
        }

        var clientModel = new ClientModel();
        BeanUtils.copyProperties(clientDto, clientModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(clientModel));
    }

    @GetMapping
    @RequestMapping("/clients/find")
    public Page<ClientModel> findByCNPJorName(Pageable pageable,@RequestParam(value = "search")String search){
        return clientService.findByCNPJorName(pageable,search);
    }

    @GetMapping
    @RequestMapping("/clients/findById/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id){
        Optional<ClientModel> clientModel = clientService.findById(id);
        if(clientModel.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(clientModel);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
    }




    @DeleteMapping("/clients/delete/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") Long id){
        Optional<ClientModel> parkingSpotModelOptional = clientService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
        clientService.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso.");
    }

    @PutMapping
    @RequestMapping("/clients/update/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") Long id, @RequestBody @Valid ClientDto clientDto){
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        if (!clientModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
        var clientModel = clientModelOptional.get();
        BeanUtils.copyProperties(clientDto, clientModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientService.save(clientModel));
    }

}
