package br.com.bank.customer.service.impl;

import br.com.bank.customer.domain.Customer;
import br.com.bank.customer.domain.Wallet;
import br.com.bank.customer.handler.dto.CreateCostumerDTO;
import br.com.bank.customer.repository.CustomerRepository;
import br.com.bank.customer.service.WalletService;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private WalletService walletService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateCustomerSuccessfully() {
        var input = createCustomerDTO();
        var expectedResponse = Customer.from(input);

        Mockito.when(walletService.create()).thenReturn(Wallet.builder().build());
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(expectedResponse);

        var response = assertDoesNotThrow(() -> customerService.create(input));

        assertEquals(expectedResponse, response);

    }

    private CreateCostumerDTO createCustomerDTO() {
        return CreateCostumerDTO.builder()
                .name(RandomString.make(200))
                .document(RandomString.make(13))
                .build();
    }
}