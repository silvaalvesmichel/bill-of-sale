package br.com.bill.of.sale.service.mapper;

import br.com.bill.of.sale.domain.Revenue;
import br.com.bill.of.sale.service.dto.RevenueDTO;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RevenueMapper {

    public Revenue dtoToEntity(RevenueDTO revenueNewDTO){
        return Revenue.builder()
                .id(revenueNewDTO.getId())
                .amount(revenueNewDTO.getAmount())
                .invoiceId(revenueNewDTO.getInvoiceId())
                .description(revenueNewDTO.getDescription())
                .accrualDate(revenueNewDTO.getAccrualDate())
                .transactionDate(revenueNewDTO.getTransactionDate())
                .build();
    }
}
