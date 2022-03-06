package br.com.bank.customer.handler;

import br.com.bank.customer.IntegrationTestBase;
import br.com.bank.customer.handler.advice.HandlerAdvice;
import br.com.bank.customer.handler.dto.CreateCostumerDTO;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

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
				.andExpect(jsonPath("$.wallet.balance", equalTo(0)))
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

	private CreateCostumerDTO createCostumerDTO() {
		return CreateCostumerDTO.builder()
				.name(RandomString.make(20))
				.document(RandomString.make(13))
				.build();
	}
}
