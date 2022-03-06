package br.com.bank.customer.service.impl;

import br.com.bank.customer.domain.Movement;
import br.com.bank.customer.domain.Wallet;
import br.com.bank.customer.handler.dto.MovementDTO;
import br.com.bank.customer.repository.MovementRepository;
import br.com.bank.customer.service.CustomerService;
import br.com.bank.customer.service.MovementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class MovementServiceImpl implements MovementService {

    private final CustomerService customerService;

    private final MovementRepository movementRepository;

    @Override
    public void  create(MovementDTO movementDTO) {
        var customer = customerService.updateCustomerWalletWithMovement(movementDTO);
        save(movementDTO, customer.getWallet());
    }

    private void save(MovementDTO movementDTO, Wallet wallet) {
        movementRepository.save(Movement.builder()
                .amount(movementDTO.getAmount())
                .type(movementDTO.getType())
                .date(LocalDateTime.now())
                .wallet(wallet)
                .build());
    }
}
