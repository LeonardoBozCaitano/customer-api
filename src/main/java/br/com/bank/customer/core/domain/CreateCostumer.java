package br.com.bank.customer.core.domain;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCostumer {

    @NonNull
    private String name;

    @NonNull
    private String document;

}
