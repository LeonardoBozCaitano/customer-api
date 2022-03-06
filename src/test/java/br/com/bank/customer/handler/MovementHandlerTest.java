package br.com.bank.customer.handler;

import br.com.bank.customer.domain.enums.MovementType;
import br.com.bank.customer.handler.dto.MovementDTO;
import br.com.bank.customer.service.MovementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MovementHandlerTest {

    @InjectMocks
    private MovementHandler movementHandler;

    @Mock
    private MovementService movementService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateCustomerSuccessfully() {
        var input = createMovementDTO();

        Mockito.doNothing().when(movementService).create(input);

        assertDoesNotThrow(() -> movementHandler.create(input));
    }

    private MovementDTO createMovementDTO() {
        return MovementDTO.builder()
                .customerId(UUID.randomUUID().toString())
                .amount(BigDecimal.ZERO)
                .type(MovementType.DEPOSIT)
                .build();
    }
}