package br.com.bank.customer.handler.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private String id;

    private String name;

    private String document;

    private WalletDTO wallet;

}
