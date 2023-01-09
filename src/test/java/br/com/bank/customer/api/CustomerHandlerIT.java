package br.com.bank.customer.api;

import br.com.bank.customer.IntegrationTestBase;
import br.com.bank.customer.api.dto.CreateCustomerDTO;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerHandlerIT extends IntegrationTestBase {

	@Autowired
	private CustomerHandler customerHandler;

	@Test
	void shouldCreateANewCustomerSuccessfully() throws Exception {
		var createCustomerDTO = createCostumerDTO();

		webApplication.perform(post(CustomerHandler.BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(createCustomerDTO)))
				.andExpect(jsonPath("$.id", notNullValue()))
				.andExpect(jsonPath("$.name", equalTo(createCustomerDTO.getName())))
				.andExpect(jsonPath("$.document", equalTo(createCustomerDTO.getDocument())))
				.andExpect(status().isCreated());
	}

	@Test
	void shouldGetACustomerSuccessfully() throws Exception {
		var customer = customerHandler.create(createCostumerDTO());

		webApplication.perform(get(CustomerHandler.BASE_URL + "/" + customer.getId())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", equalTo(customer.getId())))
				.andExpect(jsonPath("$.name", equalTo(customer.getName())))
				.andExpect(jsonPath("$.document", equalTo(customer.getDocument())))
				.andExpect(status().isOk());
	}

	private CreateCustomerDTO createCostumerDTO() {
		return CreateCustomerDTO.builder()
				.name(RandomString.make(20))
				.document(RandomString.make(13))
				.build();
	}
}
