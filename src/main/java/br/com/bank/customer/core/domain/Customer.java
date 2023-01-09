package br.com.bank.customer.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private String id;

    private String name;

    private String document;

    private Wallet wallet;

    public static Customer from(CreateCostumer customerDto, Wallet wallet) {
        return Customer.builder()
                .name(customerDto.getName())
                .wallet(wallet)
                .document(customerDto.getDocument()).build();
    }
}
