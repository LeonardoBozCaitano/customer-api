package br.com.bank.customer.handler;

import br.com.bank.customer.handler.dto.MovementDTO;
import br.com.bank.customer.service.MovementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(MovementHandler.BASE_URL)
@AllArgsConstructor
public class MovementHandler {

    public static final String BASE_URL = "/movement";

    private final MovementService movementService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Validated @RequestBody MovementDTO movementDTO) {
        movementService.create(movementDTO);
    }
}
