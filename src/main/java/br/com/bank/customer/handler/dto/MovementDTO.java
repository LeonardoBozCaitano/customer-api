package br.com.bank.customer.handler.dto;

import br.com.bank.customer.domain.enums.MovementType;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovementDTO {

    @NonNull
    private String customerId;

    @NonNull
    private BigDecimal amount;

    @NonNull
    private MovementType type;

}
