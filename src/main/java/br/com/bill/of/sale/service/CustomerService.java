package br.com.bill.of.sale.service;

import br.com.bill.of.sale.domain.Customer;
import br.com.bill.of.sale.repository.CustomerRepository;
import br.com.bill.of.sale.service.dto.CustomerDTO;
import br.com.bill.of.sale.service.dto.CustomerResponseDTO;
import br.com.bill.of.sale.service.exception.InvalidParamException;
import br.com.bill.of.sale.service.exception.ObjectNotFoundException;
import br.com.bill.of.sale.service.mapper.CustomerMapper;
import br.com.bill.of.sale.service.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerResponseDTO saveCustomer(CustomerDTO customerDTO) {
        customerValidate(customerDTO);
        Customer customer = customerMapper.dtoToEntity(customerDTO);
        customer = customerRepository.save(customer);
        return CustomerResponseDTO.builder().id(customer.getId()).build();

    }

    public CustomerDTO getCustomer(Integer customerID) {

        if (customerID == null) {
            throw new InvalidParamException("Favor informe o identificador da empresa");
        }

        Customer customer = customerRepository.getById(customerID);

        if (customer == null) {
            throw new ObjectNotFoundException("Empresa nao encontrada");
        }

        return customerMapper.entityToDto(customer);
    }

    public List<CustomerDTO> getCustomers(String name, String cnpj) {
        List<CustomerDTO> customerDTO = new ArrayList<CustomerDTO>();
        List<Customer> customers = null;
        if ((name == null || name.equals("")) && (cnpj == null || cnpj.equals(""))) {
            customers = customerRepository.findAll();
        } else {
           cnpj = Util.formatValueIsNullEmpty(cnpj);
           name = Util.formatValueIsNullEmpty(name);
            customers = customerRepository.fidByNameCnpj(name, cnpj);
        }

        customers.stream().forEach(customer -> customerDTO.add(customerMapper.entityToDto(customer)));

        return customerDTO;

    }

    public void updateCustomer(Integer customerID, CustomerDTO customerDTO) {
        CustomerDTO customer = getCustomer(customerID);
        customerDTO.setId(customer.getId());
        saveCustomer(customerDTO);
    }

    private void customerValidate(CustomerDTO customerDTO) {
        if(Util.isnull(customerDTO.getCnpj())){
            throw new InvalidParamException("Favor informe o CNPJ");
        }
        if(Util.isnull(customerDTO.getCommercialName())){
            throw new InvalidParamException("Favor informe o nome comercial");
        }
        if(Util.isnull(customerDTO.getLegalName())){
            throw new InvalidParamException("Favor informe o nome legal");
        }
    }
}
