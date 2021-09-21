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
public class MonthRevenueDTO {

    @JsonProperty("month_name")
    private String monthName;
    @JsonProperty("month_revenue")
    private Double monthRevenue;

}
