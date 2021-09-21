package br.com.bill.of.sale.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RevenueCustomerResponseDTO {

    private List<RevenueCustomerDTO> revenue;
    @JsonProperty("max_revenue_amount")
    private Double maxRevenueAmount;
}
