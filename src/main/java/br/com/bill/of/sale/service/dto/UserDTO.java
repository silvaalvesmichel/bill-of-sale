package br.com.bill.of.sale.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Integer id;
    private String name;
    private String email;
    private String cnpj;
    private String companyName;
    private String phoneNumber;
}
