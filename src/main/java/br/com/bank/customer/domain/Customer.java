package br.com.bank.customer.domain;

import br.com.bank.customer.handler.dto.CreateCostumerDTO;
import br.com.bank.customer.handler.dto.CustomerDTO;
import br.com.bank.customer.handler.dto.WalletDTO;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String document;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet", nullable=false, referencedColumnName="id")
    private Wallet wallet;

    public static Customer from(CreateCostumerDTO customerDto) {
        return Customer.builder()
                .name(customerDto.getName())
                .document(customerDto.getDocument()).build();
    }

    public CustomerDTO toDto() {
        return CustomerDTO.builder()
                .id(this.getId())
                .name(this.getName())
                .document(this.getDocument())
                .wallet(WalletDTO.builder().balance(this.wallet.getBalance()).build())
                .build();
    }
}
