package br.com.bill.of.sale.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RevenueDTO {

    @JsonProperty("revenue_id")
    private Integer id;
    private Double amount;
    @JsonProperty("invoice_id")
    private String invoiceId;
    private String description;
    @JsonProperty("accrual_date")
    private Date accrualDate;
    @JsonProperty("transaction_date")
    private Date transactionDate;
}
