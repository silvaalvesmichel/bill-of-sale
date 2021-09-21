package br.com.bill.of.sale.repository;

import br.com.bill.of.sale.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "select c from Customer c where (:cnpj is null or c.cnpj = :cnpj) and (:name is null or c.commercialName LIKE %:name%)")
    List<Customer> fidByNameCnpj(@Param("name") String name, @Param("cnpj") String cnpj);
}
