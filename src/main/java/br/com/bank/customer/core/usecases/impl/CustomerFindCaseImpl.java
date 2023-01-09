package br.com.bank.customer.core.usecases.impl;

import br.com.bank.customer.core.domain.Customer;
import br.com.bank.customer.core.port.CustomerDatabasePort;
import br.com.bank.customer.core.usecases.CustomerFindUseCase;
import org.springframework.stereotype.Service;

@Service
public record CustomerFindCaseImpl(CustomerDatabasePort customerDatabase) implements CustomerFindUseCase {

    public Customer execute(String createCostumer) {
        return customerDatabase.findById(createCostumer).orElseThrow(RuntimeException::new);
    }
}
