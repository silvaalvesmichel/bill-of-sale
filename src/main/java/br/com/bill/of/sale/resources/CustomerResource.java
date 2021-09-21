package br.com.bill.of.sale.resources;

import br.com.bill.of.sale.service.CustomerService;
import br.com.bill.of.sale.service.dto.CustomerDTO;
import br.com.bill.of.sale.service.dto.CustomerResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@Api(value = "Customer")
@RequestMapping(value = "/api")
public class CustomerResource {

    private CustomerService customerService;

    @ApiOperation(value = "Register a company")
    @PostMapping("/customers")
    public ResponseEntity<CustomerResponseDTO> saveCustomer(@RequestBody CustomerDTO customerDTO){
        CustomerResponseDTO response =  customerService.saveCustomer(customerDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Search for a company")
    @GetMapping("/customers/{customerID}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Integer customerID){
        CustomerDTO response =  customerService.getCustomer(customerID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Search for one or more companies")
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> getCustomers(@RequestParam String name,@RequestParam String cnpj){
        List<CustomerDTO> response =  customerService.getCustomers(name, cnpj);
        return new ResponseEntity<List<CustomerDTO>>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Update a company")
    @PutMapping("/customers/{customerID}")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Integer customerID){
        customerService.updateCustomer(customerID, customerDTO);
        return ResponseEntity.ok().build();
    }
}
