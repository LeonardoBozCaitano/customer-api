package br.com.bank.customer.core.usecases.impl;

import br.com.bank.customer.core.domain.Wallet;
import br.com.bank.customer.core.port.WalletDatabasePort;
import br.com.bank.customer.core.usecases.WalletCreateUseCase;
import org.springframework.stereotype.Service;

@Service
public record WalletCreateUseCaseImpl(
        WalletDatabasePort walletDatabasePort) implements WalletCreateUseCase {

    public Wallet execute() {
        return walletDatabasePort.create();
    }
}
