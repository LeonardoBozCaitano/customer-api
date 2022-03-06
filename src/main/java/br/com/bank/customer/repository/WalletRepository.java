package br.com.bank.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.bank.customer.domain.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {

}