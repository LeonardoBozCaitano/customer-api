package br.com.bank.customer.core.port;

import br.com.bank.customer.core.domain.Wallet;

public interface WalletDatabasePort {
    Wallet create();
}
