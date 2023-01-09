package br.com.bank.customer.api.dto;

import br.com.bank.customer.core.domain.CreateCostumer;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerDTO {

    @NonNull
    private String name;

    @NonNull
    private String document;

    public CreateCostumer toDomain() {
        return CreateCostumer.builder()
                .document(this.document)
                .name(this.name).build();
    }
}
