package br.com.bill.of.sale.resources;

import br.com.bill.of.sale.service.RevenueService;
import br.com.bill.of.sale.service.dto.RevenueDTO;
import br.com.bill.of.sale.service.dto.RevenueResponseDTO;
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
@Api(value = "Revenue")
@RequestMapping(value = "/api")
public class RevenueResource {

    private RevenueService revenueService;

    @ApiOperation(value = "Register a recipe")
    @PostMapping("/revenues/{customerID}")
    public ResponseEntity<RevenueResponseDTO> saveRevenue(@PathVariable Integer customerID, @RequestBody RevenueDTO revenueNewDTO){
        log.debug("nevenues");
        RevenueResponseDTO response =  revenueService.saveRevenue(revenueNewDTO, customerID);
        return new ResponseEntity<RevenueResponseDTO>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a recipe")
    @PutMapping("/revenues/{revenueID}")
    public ResponseEntity<?> updateRevenues(@PathVariable Integer revenueID, @RequestBody RevenueDTO revenueNewDTO){
        revenueService.updateRevenue(revenueID, revenueNewDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Delete a recipe")
    @DeleteMapping("/revenues/{revenueID}")
    public ResponseEntity<?> deleteRevenues(@PathVariable Integer revenueID, @RequestBody RevenueDTO revenueNewDTO){
        revenueService.deleteRevenue(revenueID);
        return ResponseEntity.ok().build();
    }

}
