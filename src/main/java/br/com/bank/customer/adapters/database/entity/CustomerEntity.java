package br.com.bank.customer.adapters.database.entity;

import br.com.bank.customer.core.domain.Customer;
import br.com.bank.customer.core.domain.Wallet;
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
public class CustomerEntity implements Serializable {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String document;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet", referencedColumnName="id")
    private WalletEntity wallet;

    public static CustomerEntity from(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId())
                .name(customer.getName())
                .wallet(WalletEntity
                        .builder()
                        .id(customer.getWallet().getId())
                        .build())
                .document(customer.getDocument()).build();
    }

    public Customer toDto() {
        return Customer.builder()
                .id(this.getId())
                .name(this.getName())
                .wallet(this.getWallet().toDto())
                .document(this.getDocument()).build();
    }
}
