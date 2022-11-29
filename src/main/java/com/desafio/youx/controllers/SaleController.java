package com.desafio.youx.controllers;

import com.desafio.youx.dtos.ClientDto;
import com.desafio.youx.dtos.SaleDto;
import com.desafio.youx.models.ClientModel;
import com.desafio.youx.models.SaleModel;
import com.desafio.youx.services.SaleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600) // permitir acesso de qualquer origem

public class SaleController {

        @Autowired
        private SaleService saleService;

        @GetMapping
        @RequestMapping("/sales")
        public Page<SaleModel> findSale(Pageable pageable){
            return saleService.findSales(pageable);
        }

        @PostMapping
        @RequestMapping("/sales/add")
        public ResponseEntity<Object> saveSale(@RequestBody SaleModel saleModel){

            return ResponseEntity.status(HttpStatus.CREATED).body(saleService.save(saleModel));
        }

        @GetMapping
        @RequestMapping("/sales/findById/{id}")
        public ResponseEntity<Object> findById(@PathVariable Long id){
            Optional<SaleModel> saleModel = saleService.findById(id);
            if(saleModel.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(saleModel);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venda não encontrada");
        }

        @DeleteMapping("/sales/delete/{id}")
        public ResponseEntity<Object> deleteSale(@PathVariable(value = "id") Long id){
            Optional<SaleModel> parkingSpotModelOptional = saleService.findById(id);
            if (!parkingSpotModelOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venda não encontrada.");
            }
            saleService.delete(parkingSpotModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Venda deletada com sucesso.");
        }



    @PutMapping
    @RequestMapping("/sales/update/{id}")
    public ResponseEntity<Object> updateSale(@PathVariable(value = "id") Long id, @RequestBody @Valid SaleDto saleDto){
        Optional<SaleModel> saleModelOptional = saleService.findById(id);
        if (!saleModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
        var saleModel = saleModelOptional.get();
        BeanUtils.copyProperties(saleDto, saleModel);
        return ResponseEntity.status(HttpStatus.OK).body(saleService.save(saleModel));
    }



    //Relatorios
    @GetMapping
    @RequestMapping("/sales/report")
    public ResponseEntity<Object>report(){
        return ResponseEntity.status(HttpStatus.OK).body(saleService.salesInTheYear());
    }

}
