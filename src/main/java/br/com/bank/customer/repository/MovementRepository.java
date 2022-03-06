package br.com.bank.customer.repository;

import br.com.bank.customer.domain.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRepository extends JpaRepository<Movement, String> {

}