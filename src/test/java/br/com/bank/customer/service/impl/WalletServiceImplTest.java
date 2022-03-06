package br.com.bank.customer.service.impl;

import br.com.bank.customer.domain.Wallet;
import br.com.bank.customer.domain.enums.MovementType;
import br.com.bank.customer.handler.dto.MovementDTO;
import br.com.bank.customer.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class WalletServiceImplTest {

    @InjectMocks
    private WalletServiceImpl walletService;

    @Mock
    private WalletRepository walletRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateNewWalletSuccessfully() {
        var wallet = createWallet();
        Mockito.when(walletRepository.save(Mockito.any(Wallet.class))).thenReturn(wallet);

        var response = assertDoesNotThrow(() -> walletService.create());

        assertEquals(wallet, response);
    }

    @Test
    void shouldUpdateWalletSuccessfully() {
        var wallet = createWallet();
        Mockito.when(walletRepository.save(wallet)).thenReturn(wallet);

        var response = assertDoesNotThrow(() -> walletService.update(wallet));

        assertEquals(wallet, response);
    }

    @Test
    void shouldUpdateWalletWithMovementSuccessfully() {
        var wallet = createWallet();
        var movement = createMovement(MovementType.DEPOSIT, BigDecimal.TEN);
        Mockito.when(walletRepository.save(wallet)).thenReturn(wallet);

        var response = assertDoesNotThrow(() -> walletService.updateWalletWithMovement(wallet, movement));

        assertEquals(wallet, response);

    }

    private Wallet createWallet() {
        return Wallet.builder()
                .id(UUID.randomUUID().toString())
                .balance(BigDecimal.ZERO)
                .build();
    }

    private MovementDTO createMovement(MovementType type, BigDecimal amount) {
        return MovementDTO.builder()
                .customerId(UUID.randomUUID().toString())
                .amount(amount)
                .type(type)
                .build();
    }

}