package br.com.bank.customer.handler;

import br.com.bank.customer.domain.Customer;
import br.com.bank.customer.domain.Wallet;
import br.com.bank.customer.handler.dto.CreateCostumerDTO;
import br.com.bank.customer.service.CustomerService;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerHandlerTest {

    @InjectMocks
    private CustomerHandler customerHandler;

    @Mock
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateCustomerSuccessfully() {
        var input = createCostumerDTO();
        var expectedOutput = createResponseCostumer(input);

        Mockito.when(customerService.create(input)).thenReturn(expectedOutput);

        var response = assertDoesNotThrow(() -> customerHandler.create(input));

        assertEquals(expectedOutput.toDto(), response);
    }

    private CreateCostumerDTO createCostumerDTO() {
        return CreateCostumerDTO.builder()
                .document(RandomString.make(13))
                .name(RandomString.make(200))
                .build();
    }

    private Customer createResponseCostumer(CreateCostumerDTO input) {
        return Customer.builder()
                .id(UUID.randomUUID().toString())
                .name(input.getName())
                .document(input.getDocument())
                .wallet(Wallet.builder().balance(BigDecimal.TEN).build())
                .build();
    }

    @Test
    void shouldReturnACostumerByIdSuccessfully() {
        var expectedCustomer = createResponseCostumer(createCostumerDTO());

        Mockito.when(customerService.getById(expectedCustomer.getId())).thenReturn(expectedCustomer);

        var response = assertDoesNotThrow(() -> customerHandler.getById(expectedCustomer.getId()));

        assertEquals(expectedCustomer.toDto(), response);
    }
}