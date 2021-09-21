package br.com.bill.of.sale.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNewDTO extends UserDTO{

    private String password;
    private String passwordConfirmation;
}
