package br.com.bill.of.sale.repository;

import br.com.bill.of.sale.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerCompanyRepository extends JpaRepository<Customer, Integer> {
}
