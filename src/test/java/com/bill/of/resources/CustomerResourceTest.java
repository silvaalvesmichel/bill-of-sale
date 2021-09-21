package com.bill.of.resources;

import br.com.bill.of.sale.service.CustomerService;
import br.com.bill.of.sale.service.dto.CustomerDTO;
import br.com.bill.of.sale.service.dto.CustomerResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
public class CustomerResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private JacksonTester<CustomerResponseDTO> dtoCustomerJacksonTester;
    private JacksonTester<CustomerDTO> dtoCustomerGetJacksonTester;
    private JacksonTester<List<CustomerDTO>> dtoCustomersGetJacksonTester;

    @Before
    public void setup() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);

    }

    @Test
    public void saveCustomerTest() throws Exception {
        CustomerDTO customerDTO = mockCustomer();
        Mockito.when(customerService.saveCustomer(customerDTO)).thenReturn(mockCustomerSaveReturn());
        mockMvc.perform(post("/api/customers").contentType("application/json")
                .content(dtoCustomerJacksonTester.write(mockCustomerSaveReturn()).getJson())).andExpect(status().isCreated());

    }

    @Test
    public void getCustomerTest() throws Exception {
        Mockito.when(customerService.getCustomer(1)).thenReturn(mockGetCustomer());
        mockMvc.perform(get("/api/customers/1").contentType("application/json")
                .content(dtoCustomerGetJacksonTester.write(mockGetCustomer()).getJson())).andExpect(status().isOk());

    }
    @Test
    public void getCustomersTest() throws Exception {
        Mockito.when(customerService.getCustomers("teste","45.918.100/0001-30")).thenReturn(mockGetCustomers());
        mockMvc.perform(post("/api/customers").contentType("application/json")
                .content(dtoCustomersGetJacksonTester.write(mockGetCustomers()).getJson())).andExpect(status().isOk());

    }


    private List<CustomerDTO> mockGetCustomers() {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        customerDTOS.add(mockGetCustomer());
        return customerDTOS;
    }

    private CustomerDTO mockGetCustomer() {

        return CustomerDTO.builder().id(1)
                .cnpj("45.918.100/0001-30")
                .legalName("teste legal name")
                .commercialName("teste nome comercial")
                .build();
    }

    private CustomerResponseDTO mockCustomerSaveReturn() {
        return CustomerResponseDTO.builder().id(1).build();
    }

    private CustomerDTO mockCustomer() {
        return CustomerDTO.builder()
                .cnpj("45.918.100/0001-30")
                .commercialName("name teste")
                .legalName("teste legal name")
                .build();
    }


}
