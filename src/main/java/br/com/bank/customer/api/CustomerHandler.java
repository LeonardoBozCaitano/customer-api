package br.com.bank.customer.api;

import br.com.bank.customer.api.dto.CreateCustomerDTO;
import br.com.bank.customer.api.dto.CustomerDTO;
import br.com.bank.customer.core.usecases.CustomerCreateUseCase;
import br.com.bank.customer.core.usecases.CustomerFindUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CustomerHandler.BASE_URL)
public record CustomerHandler(
        CustomerCreateUseCase customerCreateUseCase,
        CustomerFindUseCase customerFindUseCase) {

    public static final String BASE_URL = "/customer";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO create(@Validated @RequestBody CreateCustomerDTO createCostumerDto) {
        var customer = customerCreateUseCase.execute(createCostumerDto.toDomain());
        return CustomerDTO.fromDomain(customer);
    }

    @GetMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getById(@Validated @PathVariable String customerId) {
        var customer = customerFindUseCase.execute(customerId);
        return CustomerDTO.fromDomain(customer);
    }
}
