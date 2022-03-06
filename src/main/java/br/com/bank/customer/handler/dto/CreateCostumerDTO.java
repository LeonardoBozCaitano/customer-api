package br.com.bank.customer.handler.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCostumerDTO {

    @NonNull
    private String name;

    @NonNull
    private String document;

}
