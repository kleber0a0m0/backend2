package com.desafio.youx.services;

import com.desafio.youx.models.SaleModel;
import com.desafio.youx.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class SaleService {

        @Autowired
        SaleRepository saleRepository;

        public Page<SaleModel> findSales(Pageable pageable){
            return saleRepository.findAll(pageable);
        }

        @Transactional
        public SaleModel save(SaleModel saleModel) {
            return saleRepository.save(saleModel);
        }

        public Optional<SaleModel> findById(Long id) {
            return saleRepository.findById(id);
        }

        @Transactional
        public void delete(SaleModel saleModel) {
            saleRepository.delete(saleModel);
        }

        public String salesInTheYear(){
            LocalDate date = LocalDate.now();
            int year = Integer.parseInt(date.toString().substring(0,4));
            System.out.println(saleRepository.marchTotalSold()==null?0:saleRepository.marchTotalSold());
            return (
                    "{\n" +

                            "\"januaryTotalSold\": " + (saleRepository.januaryTotalSold()==null?"0":saleRepository.januaryTotalSold()) + ",\n" +
                            "\"februaryTotalSold\": " + (saleRepository.februaryTotalSold()==null?"0":saleRepository.februaryTotalSold()) + ",\n" +
                            "\"marchTotalSold\": " + (saleRepository.marchTotalSold()==null?"0":saleRepository.marchTotalSold()) + ",\n" +
                            "\"aprilTotalSold\": " + (saleRepository.aprilTotalSold()==null?"0":saleRepository.aprilTotalSold()) + ",\n" +
                            "\"mayTotalSold\": " + (saleRepository.mayTotalSold()==null?"0":saleRepository.mayTotalSold()) + ",\n" +
                            "\"juneTotalSold\": " + (saleRepository.juneTotalSold()==null?"0":saleRepository.juneTotalSold()) + ",\n" +
                            "\"julyTotalSold\": " + (saleRepository.julyTotalSold()==null?"0":saleRepository.julyTotalSold()) + ",\n" +
                            "\"augustTotalSold\": " + (saleRepository.augustTotalSold()==null?"0":saleRepository.augustTotalSold()) + ",\n" +
                            "\"septemberTotalSold\": " + (saleRepository.septemberTotalSold()==null?"0":saleRepository.septemberTotalSold()) + ",\n" +
                            "\"octoberTotalSold\": " + (saleRepository.octoberTotalSold()==null?"0":saleRepository.octoberTotalSold()) + ",\n" +
                            "\"novemberTotalSold\": " + (saleRepository.novemberTotalSold()==null?"0":saleRepository.novemberTotalSold()) + ",\n" +
                            "\"decemberTotalSold\": " + (saleRepository.decemberTotalSold()==null?"0":saleRepository.decemberTotalSold()) + ",\n" +
                            "  \"januaryQuantitySold\": " + saleRepository.januaryQuantitySold() + ",\n" +
                            "  \"februaryQuantitySold\": " + saleRepository.februaryQuantitySold() + ",\n" +
                            "  \"marchQuantitySold\": " + saleRepository.marchQuantitySold() + ",\n" +
                            "  \"aprilQuantitySold\": " + saleRepository.aprilQuantitySold() + ",\n" +
                            "  \"mayQuantitySold\": " + saleRepository.mayQuantitySold() + ",\n" +
                            "  \"juneQuantitySold\": " + saleRepository.juneQuantitySold() + ",\n" +
                            "  \"julyQuantitySold\": " + saleRepository.julyQuantitySold() + ",\n" +
                            "  \"augustQuantitySold\": " + saleRepository.augustQuantitySold() + ",\n" +
                            "  \"septemberQuantitySold\": " + saleRepository.septemberQuantitySold() + ",\n" +
                            "  \"octoberQuantitySold\": " + saleRepository.octoberQuantitySold() + ",\n" +
                            "  \"novemberQuantitySold\": " + saleRepository.novemberQuantitySold() + ",\n" +
                            "  \"decemberQuantitySold\": " + saleRepository.decemberQuantitySold() + "\n" +
                            "}"
                    );
        }

}

