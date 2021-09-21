package br.com.bill.of.sale.service.mapper;

import br.com.bill.of.sale.domain.Customer;
import br.com.bill.of.sale.service.dto.CustomerDTO;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CustomerMapper {

    public Customer dtoToEntity(CustomerDTO customerDTO){
        return Customer.builder()
                .id(customerDTO.getId())
                .cnpj(customerDTO.getCnpj())
                .commercialName(customerDTO.getCommercialName())
                .legalName(customerDTO.getLegalName())
                .build();
    }

    public CustomerDTO entityToDto(Customer customerD){
        return CustomerDTO.builder()
                .id(customerD.getId())
                .cnpj(customerD.getCnpj())
                .commercialName(customerD.getCommercialName())
                .legalName(customerD.getLegalName())
                .build();
    }
}
