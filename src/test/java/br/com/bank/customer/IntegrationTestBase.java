package br.com.bank.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@WebAppConfiguration
@Transactional
public class IntegrationTestBase {

  @Autowired
  private WebApplicationContext webApplicationContext;

  protected MockMvc webApplication;

  @BeforeEach
  public void setUp() {
    webApplication = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  protected static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
