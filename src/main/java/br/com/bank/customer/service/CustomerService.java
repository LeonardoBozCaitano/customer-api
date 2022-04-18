package br.com.bank.customer.service;

import br.com.bank.customer.domain.Customer;
import br.com.bank.customer.handler.dto.CreateCostumerDTO;
import br.com.bank.customer.handler.dto.MovementDTO;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    Customer create(CreateCostumerDTO customerDto);
    Customer getById(String customerId);
    Customer find(String document);
    Customer updateCustomerWalletWithMovement(MovementDTO movementDTO);
}
