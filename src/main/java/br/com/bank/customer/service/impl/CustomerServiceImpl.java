package br.com.bank.customer.service.impl;

import br.com.bank.customer.domain.Customer;
import br.com.bank.customer.handler.dto.CreateCostumerDTO;
import br.com.bank.customer.handler.dto.MovementDTO;
import br.com.bank.customer.repository.CustomerRepository;
import br.com.bank.customer.service.CustomerService;
import br.com.bank.customer.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final WalletService walletService;

    @Override
    public Customer create(CreateCostumerDTO createCostumerDTO) {
        var customer = Customer.from(createCostumerDTO);
        customer.setWallet(walletService.create());

        return customerRepository.save(customer);
    }

    @Override
    public Customer getById(String customerId) {
        return customerRepository.getById(customerId);
    }

    @Override
    public Customer updateCustomerWalletWithMovement(MovementDTO movementDTO) {
        var customer = getById(movementDTO.getCustomerId());
        var wallet = walletService.updateWalletWithMovement(customer.getWallet(), movementDTO);
        customer.setWallet(wallet);

        return customer;
    }
}
