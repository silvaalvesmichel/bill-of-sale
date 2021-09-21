package br.com.bill.of.sale.resources;

import br.com.bill.of.sale.service.UserService;
import br.com.bill.of.sale.service.dto.UserDTO;
import br.com.bill.of.sale.service.dto.UserNewDTO;
import br.com.bill.of.sale.service.dto.UserResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@AllArgsConstructor
@RestController
@Api(value = "User")
@RequestMapping(value = "/api")
public class UserResource {

    private UserService userService;

    @ApiOperation(value = "Register a user")
    @PostMapping("/users")
    public ResponseEntity<UserResponseDTO> users(@RequestBody UserNewDTO userNewDTO){
        UserResponseDTO response =  userService.saveUser(userNewDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @ApiOperation(value = "Perform user ID search")
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id){
        UserDTO response =userService.getUser(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}