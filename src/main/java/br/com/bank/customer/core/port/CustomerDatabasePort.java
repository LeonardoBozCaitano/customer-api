package br.com.bank.customer.core.port;

import br.com.bank.customer.core.domain.Customer;

import java.util.Optional;

public interface CustomerDatabasePort {
    Customer save(Customer customer);
    Optional<Customer> findById(String customerId);
}
