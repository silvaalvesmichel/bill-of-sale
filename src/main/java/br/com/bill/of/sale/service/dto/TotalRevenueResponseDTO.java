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
public class TotalRevenueResponseDTO {

    @JsonProperty("total_revenue")
    private Double totalRevenue;
    @JsonProperty("max_revenue_amount")
    private Double maxRevenueAmount;
}
