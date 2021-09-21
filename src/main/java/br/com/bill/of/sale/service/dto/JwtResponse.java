package br.com.bill.of.sale.service.dto;

import br.com.bill.of.sale.security.UserSS;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {

    private String token;
    private UserSS user;
}
