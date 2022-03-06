package br.com.bank.customer.service;

import br.com.bank.customer.handler.dto.MovementDTO;
import org.springframework.stereotype.Service;

@Service
public interface MovementService {
    void create(MovementDTO movementDTO);
}
