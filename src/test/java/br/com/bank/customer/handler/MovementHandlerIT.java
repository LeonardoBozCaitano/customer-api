package br.com.bank.customer.handler;

import br.com.bank.customer.IntegrationTestBase;
import br.com.bank.customer.domain.enums.MovementType;
import br.com.bank.customer.handler.dto.CreateCostumerDTO;
import br.com.bank.customer.handler.dto.CustomerDTO;
import br.com.bank.customer.handler.dto.MovementDTO;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MovementHandlerIT extends IntegrationTestBase {

    @Autowired
    private MovementHandler movementHandler;

    @Autowired
    private CustomerHandler customerHandler;

    @Test
    void shouldCreateADepositMovementSuccessfully() throws Exception {
        var customer = customerHandler.create(createCostumerDTO());
        var depositedAbout = BigDecimal.TEN;
        var movementDTO = MovementDTO.builder()
                .customerId(customer.getId())
                .type(MovementType.DEPOSIT)
                .amount(depositedAbout)
                .build();

        webApplication.perform(post(MovementHandler.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movementDTO)))
                .andExpect(status().isCreated());

        var resultedCustomer = customerHandler.getById(customer.getId());

        Assertions.assertEquals(depositedAbout, resultedCustomer.getWallet().getBalance());
    }

    @Test
    void shouldCreateAPaymentMovementSuccessfully() throws Exception {
        var customer = customerHandler.create(createCostumerDTO());
        var depositedAbout = BigDecimal.TEN;
        var movementDTO = MovementDTO.builder()
                .customerId(customer.getId())
                .type(MovementType.PAYMENT)
                .amount(depositedAbout)
                .build();

        webApplication.perform(post(MovementHandler.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movementDTO)))
                .andExpect(status().isCreated());

        var resultedCustomer = customerHandler.getById(customer.getId());

        Assertions.assertEquals(depositedAbout.multiply(BigDecimal.valueOf(-1)), resultedCustomer.getWallet().getBalance());
    }

    @Test
    void shouldCreateAWithdrawMovementSuccessfully() throws Exception {
        var customer = customerHandler.create(createCostumerDTO());
        var depositedAbout = BigDecimal.TEN;
        var movementDTO = MovementDTO.builder()
                .customerId(customer.getId())
                .type(MovementType.WITHDRAW)
                .amount(depositedAbout)
                .build();

        webApplication.perform(post(MovementHandler.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movementDTO)))
                .andExpect(status().isCreated());

        var resultedCustomer = customerHandler.getById(customer.getId());

        Assertions.assertEquals(depositedAbout.multiply(BigDecimal.valueOf(-1)), resultedCustomer.getWallet().getBalance());
    }

    @Test
    void shouldCreateATransferInMovementSuccessfully() throws Exception {
        var customer = customerHandler.create(createCostumerDTO());
        var depositedAbout = BigDecimal.TEN;
        var movementDTO = MovementDTO.builder()
                .customerId(customer.getId())
                .type(MovementType.TRANSFER_IN)
                .amount(depositedAbout)
                .build();

        webApplication.perform(post(MovementHandler.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movementDTO)))
                .andExpect(status().isCreated());

        var resultedCustomer = customerHandler.getById(customer.getId());

        Assertions.assertEquals(depositedAbout, resultedCustomer.getWallet().getBalance());
    }

    @Test
    void shouldCreateATransferOutMovementSuccessfully() throws Exception {
        var customerBalance = BigDecimal.valueOf(50);
        var customer = createCustomerWithBalance(customerBalance);
        var transferAmount = BigDecimal.TEN;
        var expectedBalance = customerBalance.subtract(transferAmount);
        var movementDTO = MovementDTO.builder()
                .customerId(customer.getId())
                .type(MovementType.TRANSFER_OUT)
                .amount(transferAmount)
                .build();

        webApplication.perform(post(MovementHandler.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movementDTO)))
                .andExpect(status().isCreated());

        var resultedCustomer = customerHandler.getById(customer.getId());

        Assertions.assertEquals(expectedBalance, resultedCustomer.getWallet().getBalance());
    }

    @Test
    void shouldThrowErrorWhenCreateATransferOutWithoutBalance() throws Exception {
        var customerBalance = BigDecimal.valueOf(10);
        var customer = createCustomerWithBalance(customerBalance);
        var transferAmount = BigDecimal.valueOf(50);
        var movementDTO = MovementDTO.builder()
                .customerId(customer.getId())
                .type(MovementType.TRANSFER_OUT)
                .amount(transferAmount)
                .build();

        webApplication.perform(post(MovementHandler.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movementDTO)))
                .andExpect(status().isBadRequest());

        var resultedCustomer = customerHandler.getById(customer.getId());

        Assertions.assertEquals(customerBalance, resultedCustomer.getWallet().getBalance());
    }

    private CreateCostumerDTO createCostumerDTO() {
        return CreateCostumerDTO.builder()
                .name(RandomString.make(20))
                .document(RandomString.make(13))
                .build();
    }

    private CustomerDTO createCustomerWithBalance(BigDecimal balance) {
        var customer = customerHandler.create(createCostumerDTO());
        var movementDTO = MovementDTO.builder()
                .customerId(customer.getId())
                .type(MovementType.DEPOSIT)
                .amount(balance)
                .build();
        movementHandler.create(movementDTO);
        return customer;
    }
}
