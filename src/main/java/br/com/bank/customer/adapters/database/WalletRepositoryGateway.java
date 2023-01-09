package br.com.bank.customer.adapters.database;

import br.com.bank.customer.adapters.database.entity.WalletEntity;
import br.com.bank.customer.adapters.database.impl.WalletRepository;
import br.com.bank.customer.core.domain.Wallet;
import br.com.bank.customer.core.port.WalletDatabasePort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public record WalletRepositoryGateway(
        WalletRepository walletRepository) implements WalletDatabasePort {

    public Wallet create() {
        var walletEntity = WalletEntity.builder()
                .balance(BigDecimal.ZERO)
                .build();
        walletEntity = walletRepository.save(walletEntity);
        return walletEntity.toDto();
    }
}
