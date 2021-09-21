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
public class CustomerDTO {

    @JsonProperty("customer_id")
    private Integer id;
    private String cnpj;
    @JsonProperty(value = "commercial_name")
    private String commercialName;
    @JsonProperty(value ="legal_name")
    private String legalName;
}
