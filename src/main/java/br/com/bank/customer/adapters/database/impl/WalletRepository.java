package br.com.bank.customer.adapters.database.impl;

import br.com.bank.customer.adapters.database.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<WalletEntity, String>{

}
