package br.com.bank.customer.service;

import br.com.bank.customer.domain.Wallet;
import br.com.bank.customer.handler.dto.MovementDTO;

public interface WalletService {
    Wallet create();
    Wallet update(Wallet wallet);
    Wallet updateWalletWithMovement(Wallet wallet, MovementDTO movementDTO);
}
