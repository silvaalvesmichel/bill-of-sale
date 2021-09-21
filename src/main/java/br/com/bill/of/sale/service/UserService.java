package br.com.bill.of.sale.service;

import br.com.bill.of.sale.domain.User;
import br.com.bill.of.sale.repository.UserRepository;
import br.com.bill.of.sale.service.dto.UserDTO;
import br.com.bill.of.sale.service.dto.UserNewDTO;
import br.com.bill.of.sale.service.dto.UserResponseDTO;
import br.com.bill.of.sale.service.exception.EmailAlreadyInUseException;
import br.com.bill.of.sale.service.exception.InvalidParamException;
import br.com.bill.of.sale.service.exception.UserNotFoundException;
import br.com.bill.of.sale.service.mapper.UserMapper;
import br.com.bill.of.sale.service.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    public UserResponseDTO saveUser(UserNewDTO userNewDTO) {
        userValidate(userNewDTO);
        User user = getUserByEmail(userNewDTO.getEmail());
        if (Objects.isNull(user)) {
            if (userNewDTO.getPassword().equals(userNewDTO.getPasswordConfirmation())) {
                User newUser = userMapper.dtoToEntity(userNewDTO);
                newUser.setPassword(bCryptPasswordEncoder.encode(userNewDTO.getPassword()));
                newUser = userRepository.save(newUser);
                return UserResponseDTO.builder().id(newUser.getId()).build();
            } else {
                throw new EmailAlreadyInUseException("A confirmacao da senha precisa ser igual a senha");
            }
        } else {
            throw new EmailAlreadyInUseException("O Email recebido ja se encontra em uso");
        }
    }

    private void userValidate(UserNewDTO userNewDTO) {
        if (Util.isnull(userNewDTO.getName())) {
            throw new InvalidParamException("Favor informe o nome do usuario");
        }
        if (Util.isnull(userNewDTO.getEmail())) {
            throw new InvalidParamException("Favor informe o email");
        }
        if (Util.isnull(userNewDTO.getCnpj())) {
            throw new InvalidParamException("Favor informe o cnpj");
        }
        if (userNewDTO.getCnpj() != null || !userNewDTO.getCnpj().equals("")) {
            if (Util.isCNPJ(userNewDTO.getCnpj())) {
                throw new InvalidParamException("Favor informe o CNPJ valido");
            }
        }
        if (Util.isnull(userNewDTO.getPassword())) {
            throw new InvalidParamException("Favor informe o password");
        }
        if (Util.isnull(userNewDTO.getPasswordConfirmation())) {
            throw new InvalidParamException("Favor informe a confirmacao do password");
        }
        if (Util.isnull(userNewDTO.getPhoneNumber())) {
            throw new InvalidParamException("Favor informe o telefone");
        }
        if (Util.isnull(userNewDTO.getCompanyName())) {
            throw new InvalidParamException("Favor informe o nome da empresa");
        }
    }

    public void deleteUser(UserNewDTO userNewDTO) {
        User user = userMapper.dtoToEntity(userNewDTO);
        userRepository.delete(user);
    }

    public UserDTO getUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.orElse(null) == null) {
            throw new UserNotFoundException("Usuario nao encontrado");
        }

        return userMapper.entityToDTO(user.orElse(null));
    }
}
