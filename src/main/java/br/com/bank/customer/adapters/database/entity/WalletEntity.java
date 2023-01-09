package br.com.bank.customer.adapters.database.entity;


import br.com.bank.customer.core.domain.Customer;
import br.com.bank.customer.core.domain.Wallet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@Entity
@Table(name = "wallet")
@NoArgsConstructor
@AllArgsConstructor
public class WalletEntity implements Serializable {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    @Column
    private String id;

    private BigDecimal balance;

    @OneToOne(mappedBy = "wallet")
    private CustomerEntity customer;

    public Wallet toDto() {
        return Wallet.builder()
                .id(this.getId())
                .balance(this.balance)
                .build();
    }

}
