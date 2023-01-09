package br.com.bank.customer.core.usecases;

import br.com.bank.customer.core.domain.Customer;

public interface CustomerFindUseCase {
    Customer execute(String id);
}
