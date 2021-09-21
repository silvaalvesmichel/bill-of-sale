package br.com.bill.of.sale.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnFindMonthRevenueDTO {

    private Integer monthNumber;
    private Double monthRevenue;
}
