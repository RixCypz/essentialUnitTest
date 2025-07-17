package essential.test.UnitTestPractice.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DisplayName("UserController Integration Tests")
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("should create user with orders successfully")
    void testCreateUserWithOrders() throws Exception {
        String json = """
            {
              "name": "John Doe",
              "orders": [
                { "productName": "Product A", "quantity": 2 },
                { "productName": "Product B", "quantity": 5 }
              ]
            }
        """;

        mockMvc.perform(post("/test/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.message").value("User created successfully"))
            .andExpect(jsonPath("$.data.name").value("John Doe"))
            .andExpect(jsonPath("$.data.orders[0].productName").value("Product A"))
            .andExpect(jsonPath("$.data.orders[1].quantity").value(5));
    }

    @Test
    @DisplayName("Should return 400 when request is invalid")
    void shouldReturn400_whenRequestIsInvalid() throws Exception {
        String json = "{}";

        mockMvc.perform(post("/test/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isBadRequest());
    }
}

