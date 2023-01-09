package br.com.bank.customer.core.usecases.impl;

import br.com.bank.customer.core.domain.CreateCostumer;
import br.com.bank.customer.core.domain.Customer;
import br.com.bank.customer.core.domain.Wallet;
import br.com.bank.customer.core.port.CustomerDatabasePort;
import br.com.bank.customer.core.usecases.WalletCreateUseCase;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CustomerCreateUseCaseImplTest {

    @InjectMocks
    private CustomerCreateUseCaseImpl customerCreateUseCase;

    @Mock
    private CustomerDatabasePort customerDatabase;

    @Mock
    private WalletCreateUseCase walletCreateUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateTheCustomerSuccessfully() {
        var createCostumer = newCreateCustomer();
        var wallet = Wallet.builder().build();
        var customer = Customer.from(createCostumer, wallet);

        Mockito.when(walletCreateUseCase.execute()).thenReturn(wallet);
        Mockito.when(customerDatabase.save(customer)).thenReturn(customer);

        var response = customerCreateUseCase.execute(createCostumer);

        assertEquals(customer, response);
    }

    private CreateCostumer newCreateCustomer() {
        return CreateCostumer.builder()
                .name(RandomString.make(200))
                .document(RandomString.make(15))
                .build();
    }
}