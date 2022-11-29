package com.desafio.youx.repositories;

import com.desafio.youx.models.ClientModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel,Long> {
    boolean existsByCnpj(String cnpj);

    @Query("SELECT c FROM ClientModel c WHERE c.cnpj LIKE %:search% OR c.name LIKE %:search%")
    Page<ClientModel> findByCnpjOrName(Pageable pageable,@Param("search") String search);
}
