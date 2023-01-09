package br.com.bank.customer.core.domain;

import br.com.bank.customer.core.domain.enums.MovementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movement {

    private String id;

    private MovementType type;

    private BigDecimal amount;

    private LocalDateTime date;

    private Wallet wallet;

}
