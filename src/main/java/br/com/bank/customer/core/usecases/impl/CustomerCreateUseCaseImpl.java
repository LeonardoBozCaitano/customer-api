package br.com.bank.customer.core.usecases.impl;

import br.com.bank.customer.core.domain.CreateCostumer;
import br.com.bank.customer.core.domain.Customer;
import br.com.bank.customer.core.domain.Wallet;
import br.com.bank.customer.core.port.CustomerDatabasePort;
import br.com.bank.customer.core.usecases.CustomerCreateUseCase;
import br.com.bank.customer.core.usecases.WalletCreateUseCase;
import org.springframework.stereotype.Service;

@Service
public record CustomerCreateUseCaseImpl (
        CustomerDatabasePort customerDatabase,
        WalletCreateUseCase walletCreateUseCase) implements CustomerCreateUseCase {

    public Customer execute(CreateCostumer createCostumer) {
        Wallet wallet = walletCreateUseCase.execute();
        Customer customer = Customer.from(createCostumer, wallet);
        return customerDatabase.save(customer);
    }
}
