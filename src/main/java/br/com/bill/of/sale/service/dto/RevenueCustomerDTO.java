package br.com.bill.of.sale.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RevenueCustomerDTO {

    @JsonProperty("customer_name")
    private String customerName;
    private Double revenue;


}
