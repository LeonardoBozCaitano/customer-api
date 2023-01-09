package br.com.bank.customer.core.usecases;

import br.com.bank.customer.core.domain.CreateCostumer;
import br.com.bank.customer.core.domain.Customer;

public interface CustomerCreateUseCase {
    Customer execute(CreateCostumer createCostumer);
}
