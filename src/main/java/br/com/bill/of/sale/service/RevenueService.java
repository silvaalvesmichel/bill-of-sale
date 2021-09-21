package br.com.bill.of.sale.service;

import br.com.bill.of.sale.domain.Customer;
import br.com.bill.of.sale.domain.Revenue;
import br.com.bill.of.sale.repository.PartnerCompanyRepository;
import br.com.bill.of.sale.repository.RevenueRepository;
import br.com.bill.of.sale.service.dto.*;
import br.com.bill.of.sale.service.exception.InvalidParamException;
import br.com.bill.of.sale.service.exception.ObjectNotFoundException;
import br.com.bill.of.sale.service.mapper.RevenueMapper;
import br.com.bill.of.sale.service.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RevenueService {

    private RevenueRepository revenueRepository;
    private PartnerCompanyRepository partnerCompanyRepository;
    private RevenueMapper revenueMapper;


    public RevenueResponseDTO saveRevenue(RevenueDTO revenueNewDTO, Integer customerID) {
        revenueValidate(revenueNewDTO);
        if (customerID == null) {
            throw new InvalidParamException("Favor informe o customer ID");
        }

        Optional<Customer> company = partnerCompanyRepository.findById(customerID);

        if (company.isEmpty()) {
            throw new ObjectNotFoundException("customer ID invalido");
        }
        Revenue revenue = revenueMapper.dtoToEntity(revenueNewDTO);
        revenue.setCompany(company.orElse(null));
        Revenue revenueNew = revenueRepository.save(revenue);

        return RevenueResponseDTO.builder().id(revenueNew.getId()).build();

    }

    public void updateRevenue(Integer revenueID, RevenueDTO revenueNewDTO) {
        revenueValidate(revenueNewDTO);
        if (revenueID == null) {
            throw new InvalidParamException("Favor informe o identificador da receita");
        }

        Optional<Revenue> revenue = revenueRepository.findById(revenueID);

        if (revenue.isEmpty()) {
            throw new ObjectNotFoundException("Identificador da receita invalido");
        }
        revenueNewDTO.setId(revenue.orElse(null).getId());
        Revenue revenueUpdate = revenueMapper.dtoToEntity(revenueNewDTO);
        revenueUpdate.setCompany(revenue.orElse(null).getCompany());
        revenueRepository.save(revenueUpdate);
    }

    public void deleteRevenue(Integer revenueID) {
        revenueRepository.deleteById(revenueID);
    }

    public TotalRevenueResponseDTO totalRevenue(Integer fiscalYear) {
        Double totalRenenue = revenueRepository.findTotalRevenue(fiscalYear);
        Double maxRevenueAmount = getMaxRevenueAmount(fiscalYear);
        return TotalRevenueResponseDTO.builder().totalRevenue(totalRenenue).maxRevenueAmount(maxRevenueAmount).build();
    }

    public RevenueMonthResponseDTO monthRevenue(Integer fiscalYear) {
        List<MonthRevenueDTO> monthRevenuesDTO = new ArrayList<MonthRevenueDTO>();
        List<ReturnFindMonthRevenueDTO> monthRevenues = revenueRepository.findMonthRevenue(fiscalYear);
        monthRevenues.stream().forEach(monthRevenue ->
                monthRevenuesDTO.add(MonthRevenueDTO.builder().monthName(Month.of(monthRevenue.getMonthNumber()).name()).monthRevenue(monthRevenue.getMonthRevenue()).build())
        );
        Double maxRevenueAmount = getMaxRevenueAmount(fiscalYear);

        return RevenueMonthResponseDTO.builder().revenue(monthRevenuesDTO).maxRevenueAmount(maxRevenueAmount).build();

    }

    private Double getMaxRevenueAmount(Integer fiscalYear) {
        return revenueRepository.findMaxRevenueAmount(fiscalYear);
    }

    public RevenueCustomerResponseDTO costumerRevenue(Integer fiscalYear) {
        List<RevenueCustomerDTO> revenueCustomerDTOS = new ArrayList<RevenueCustomerDTO>();
        List<Revenue> revenues = revenueRepository.findRevenueCustomer(fiscalYear);
        revenues.stream().forEach(revenue ->
                revenueCustomerDTOS.add(RevenueCustomerDTO.builder().customerName(revenue.getCompany().getCommercialName()).revenue(revenue.getAmount()).build())
        );
        return RevenueCustomerResponseDTO.builder().revenue(revenueCustomerDTOS).maxRevenueAmount(getMaxRevenueAmount(fiscalYear)).build();

    }

    private void revenueValidate(RevenueDTO revenueNewDTO) {

        if(revenueNewDTO.getAmount() == null){
            throw new InvalidParamException("Favor informe o Valor");
        }
        if(Util.isnull(revenueNewDTO.getDescription())){
            throw new InvalidParamException("Favor informe a descricao");
        }
        if(Util.isnull(revenueNewDTO.getInvoiceId())){
            throw new InvalidParamException("Favor informe o identificador da fatura");
        }
        if(revenueNewDTO.getAccrualDate() == null){
            throw new InvalidParamException("Favor informe a data da competencia");
        }
        if(revenueNewDTO.getTransactionDate() == null){
            throw new InvalidParamException("Favor informe a data de ocorrencia");
        }
    }
}
