package br.com.bank.customer.api;

import br.com.bank.customer.api.dto.CreateCustomerDTO;
import br.com.bank.customer.core.domain.Customer;
import br.com.bank.customer.core.usecases.CustomerCreateUseCase;
import br.com.bank.customer.core.usecases.CustomerFindUseCase;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerHandlerTest {

    @InjectMocks
    private CustomerHandler customerHandler;

    @Mock
    private CustomerCreateUseCase customerCreateUseCase;

    @Mock
    private CustomerFindUseCase customerFindUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateCustomerSuccessfully() {
        var input = createCostumerDTO();
        var expectedOutput = createResponseCostumer(input);

        Mockito.when(customerCreateUseCase.execute(input.toDomain())).thenReturn(expectedOutput);

        var response = assertDoesNotThrow(() -> customerHandler.create(input));

        assertEquals(expectedOutput.getName(), response.getName());
        assertEquals(expectedOutput.getDocument(), response.getDocument());
    }

    private CreateCustomerDTO createCostumerDTO() {
        return CreateCustomerDTO.builder()
                .document(RandomString.make(13))
                .name(RandomString.make(200))
                .build();
    }

    private Customer createResponseCostumer(CreateCustomerDTO input) {
        return Customer.builder()
                .id(UUID.randomUUID().toString())
                .name(input.getName())
                .document(input.getDocument())
                .build();
    }

    @Test
    void shouldReturnACostumerByIdSuccessfully() {
        var expectedCustomer = createResponseCostumer(createCostumerDTO());

        Mockito.when(customerFindUseCase.execute(expectedCustomer.getId())).thenReturn(expectedCustomer);

        var response = assertDoesNotThrow(() -> customerHandler.getById(expectedCustomer.getId()));

        assertEquals(expectedCustomer.getId(), response.getId());
        assertEquals(expectedCustomer.getName(), response.getName());
        assertEquals(expectedCustomer.getDocument(), response.getDocument());
    }
}