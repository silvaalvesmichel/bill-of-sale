package br.com.bill.of.sale.service.mapper;

import br.com.bill.of.sale.domain.User;
import br.com.bill.of.sale.service.dto.UserDTO;
import br.com.bill.of.sale.service.dto.UserNewDTO;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserMapper {


    public User dtoToEntity(UserNewDTO userNewDTO){
        return User.builder()
                .id(userNewDTO.getId())
                .name(userNewDTO.getName())
                .email(userNewDTO.getEmail())
                .password(userNewDTO.getPassword())
                .cnpj(userNewDTO.getCnpj())
                .companyName(userNewDTO.getCompanyName())
                .phoneNumber(userNewDTO.getPhoneNumber())
                .build();
    }

    public UserDTO entityToDTO(User user){
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .cnpj(user.getCnpj())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
