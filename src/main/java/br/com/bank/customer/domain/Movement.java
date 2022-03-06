package br.com.bank.customer.domain;

import br.com.bank.customer.domain.enums.MovementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "movement")
@NoArgsConstructor
@AllArgsConstructor
public class Movement implements Serializable {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    @Column
    private String id;

    private MovementType type;

    private BigDecimal amount;

    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="wallet", nullable=false)
    private Wallet wallet;

}
