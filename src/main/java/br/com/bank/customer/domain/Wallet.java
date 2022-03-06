package br.com.bank.customer.domain;

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
@Entity
@Table(name = "wallet")
@NoArgsConstructor
@AllArgsConstructor
public class Wallet implements Serializable {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    @Column
    private String id;

    private BigDecimal balance;

    @OneToOne(mappedBy = "wallet")
    private Customer customer;

    @OneToMany(mappedBy="wallet")
    private List<Movement> movements = new ArrayList<>();

}
