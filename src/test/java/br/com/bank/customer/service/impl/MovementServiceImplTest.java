package br.com.bank.customer.service.impl;

import br.com.bank.customer.domain.Customer;
import br.com.bank.customer.domain.Movement;
import br.com.bank.customer.domain.Wallet;
import br.com.bank.customer.domain.enums.MovementType;
import br.com.bank.customer.handler.dto.MovementDTO;
import br.com.bank.customer.repository.MovementRepository;
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

class MovementServiceImplTest {

    @InjectMocks
    private MovementServiceImpl movementService;

    @Mock
    private CustomerService customerService;

    @Mock
    private MovementRepository movementRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateMovementSuccessfully() {
        var input = createMovementDTO();
        var customer = createCostumerDTO();

        Mockito.when(customerService.updateCustomerWalletWithMovement(input)).thenReturn(customer);
        Mockito.when(movementRepository.save(Mockito.any(Movement.class))).thenReturn(Movement.builder().build());

        assertDoesNotThrow(() -> movementService.create(input));
    }

    private MovementDTO createMovementDTO() {
        return MovementDTO.builder()
                .customerId(UUID.randomUUID().toString())
                .type(MovementType.DEPOSIT)
                .amount(BigDecimal.TEN)
                .build();
    }

    private Customer createCostumerDTO() {
        return Customer.builder()
                .id(UUID.randomUUID().toString())
                .name(RandomString.make(100))
                .document(RandomString.make(13))
                .wallet(Wallet.builder().balance(BigDecimal.TEN).build())
                .build();
    }
}