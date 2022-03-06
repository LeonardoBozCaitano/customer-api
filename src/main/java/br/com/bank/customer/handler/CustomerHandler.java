package br.com.bank.customer.handler;

import br.com.bank.customer.handler.dto.CreateCostumerDTO;
import br.com.bank.customer.handler.dto.CustomerDTO;
import br.com.bank.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CustomerHandler.BASE_URL)
@AllArgsConstructor
public class CustomerHandler {

    public static final String BASE_URL = "/customer";

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO create(@Validated @RequestBody CreateCostumerDTO createCostumerDto) {
        return customerService.create(createCostumerDto).toDto();
    }

    @GetMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getById(@Validated @PathVariable String customerId) {
         return customerService.getById(customerId).toDto();
    }
}
