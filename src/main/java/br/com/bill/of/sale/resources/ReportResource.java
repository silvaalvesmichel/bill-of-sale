package br.com.bill.of.sale.resources;

import br.com.bill.of.sale.service.RevenueService;
import br.com.bill.of.sale.service.dto.RequestReportRevenueDTO;
import br.com.bill.of.sale.service.dto.RevenueCustomerResponseDTO;
import br.com.bill.of.sale.service.dto.RevenueMonthResponseDTO;
import br.com.bill.of.sale.service.dto.TotalRevenueResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@Api(value = "Customer")
@RequestMapping(value = "/api")
public class ReportResource {

    private RevenueService revenueService;

    @ApiOperation(value = "Performs the calculation of total revenue")
    @PostMapping("/reports/total-revenue")
    public ResponseEntity<TotalRevenueResponseDTO> totalRevenue(@RequestBody RequestReportRevenueDTO request){
        TotalRevenueResponseDTO response =  revenueService.totalRevenue(request.getFiscalYear());
        return new ResponseEntity<TotalRevenueResponseDTO>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Search for the months with revenue for a given year and the highest revenue")
    @PostMapping("/reports/revenue-by-month")
    public ResponseEntity<RevenueMonthResponseDTO> monthRevenue(@RequestBody RequestReportRevenueDTO request){
        RevenueMonthResponseDTO response =  revenueService.monthRevenue(request.getFiscalYear());
        return new ResponseEntity<RevenueMonthResponseDTO>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "It searches for revenue with certain companies of that year and the highest revenue")
    @PostMapping("/reports/revenue-by-customer")
    public ResponseEntity<RevenueCustomerResponseDTO> costumerRevenue(@RequestBody RequestReportRevenueDTO request){
        RevenueCustomerResponseDTO response =  revenueService.costumerRevenue(request.getFiscalYear());
        return new ResponseEntity<RevenueCustomerResponseDTO>(response, HttpStatus.OK);
    }
}
