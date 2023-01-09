package br.com.bank.customer.adapters.database.impl;

import br.com.bank.customer.adapters.database.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String>{

}
