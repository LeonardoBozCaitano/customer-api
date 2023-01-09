package br.com.bank.customer.api.dto;

import br.com.bank.customer.core.domain.Customer;
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

    public static CustomerDTO fromDomain(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .document(customer.getDocument())
                .build();
    }
}
