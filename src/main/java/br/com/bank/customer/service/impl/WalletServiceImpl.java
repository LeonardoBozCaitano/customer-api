package br.com.bank.customer.service.impl;

import br.com.bank.customer.domain.Wallet;
import br.com.bank.customer.handler.dto.MovementDTO;
import br.com.bank.customer.repository.WalletRepository;
import br.com.bank.customer.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Override
    public Wallet create() {
        var wallet = Wallet.builder().balance(BigDecimal.ZERO).build();
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet update(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet updateWalletWithMovement(Wallet wallet, MovementDTO movementDTO) {
        wallet.setBalance(movementDTO.getType().execute(wallet.getBalance(), movementDTO.getAmount()));

        return update(wallet);
    }
}
