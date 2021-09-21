package br.com.bill.of.sale.repository;

import br.com.bill.of.sale.domain.Revenue;
import br.com.bill.of.sale.service.dto.ReturnFindMonthRevenueDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Integer> {

    @Query(value = "select SUM(r.amount) from Revenue r where year(r.transactionDate) = :fiscalYear")
    Double findTotalRevenue(Integer fiscalYear);

    @Query(value = "select MAX(r.amount) from Revenue r where year(r.transactionDate) = :fiscalYear")
    Double findMaxRevenueAmount(@Param("fiscalYear") Integer fiscalYear);

    @Query(value = "select new br.com.bill.of.sale.service.dto.ReturnFindMonthRevenueDTO(month(r.transactionDate), r.amount) from Revenue r where year(r.transactionDate) = :fiscalYear")
    List<ReturnFindMonthRevenueDTO> findMonthRevenue(@Param("fiscalYear") Integer fiscalYear);

    @Query(value = "select r from Revenue r where year(r.transactionDate) = :fiscalYear")
    List<Revenue> findRevenueCustomer(@Param("fiscalYear") Integer fiscalYear);
}
