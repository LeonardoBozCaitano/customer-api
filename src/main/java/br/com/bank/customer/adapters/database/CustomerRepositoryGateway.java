package br.com.bank.customer.adapters.database;

import br.com.bank.customer.adapters.database.entity.CustomerEntity;
import br.com.bank.customer.adapters.database.impl.CustomerRepository;
import br.com.bank.customer.core.domain.Customer;
import br.com.bank.customer.core.port.CustomerDatabasePort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record CustomerRepositoryGateway(
        CustomerRepository customerRepository) implements CustomerDatabasePort {

    public Customer save(Customer customer) {
        var customerEntity = CustomerEntity.from(customer);
        customerEntity = customerRepository.save(customerEntity);
        return customerEntity.toDto();
    }

    public Optional<Customer> findById(String customerId) {
        return customerRepository.findById(customerId).or(Optional::empty).map(CustomerEntity::toDto);
    }
}
