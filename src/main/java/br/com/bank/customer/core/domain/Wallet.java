package br.com.bank.customer.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    private String id;

    private BigDecimal balance;

    private Customer customer;

    public static Wallet createNewWalletTo(Customer customer) {
        return Wallet.builder()
                .balance(BigDecimal.ZERO)
                .customer(customer)
                .build();
    }
}
