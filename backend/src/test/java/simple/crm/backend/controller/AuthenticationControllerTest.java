package simple.crm.backend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import simple.crm.backend.dto.user.request.UserLoginRequestDto;
import simple.crm.backend.dto.user.request.UserRegistrationRequestDto;
import simple.crm.backend.dto.user.response.UserResponseDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationControllerTest {
    protected static MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll(
            @Autowired WebApplicationContext applicationContext
    ) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @DisplayName("Register like user - expected result: user successfully register ")
    @Sql(scripts = {
            "classpath:database/before/before_TestExecution.sql",
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/after/after_afterTestExecution.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void register_ValidRequest_Success() throws Exception {
        //Given
        UserRegistrationRequestDto requestDto = new UserRegistrationRequestDto()
                .setEmail("john.doe@example.com")
                .setFirstName("John")
                .setLastName("Doe")
                .setPassword("securePassword123")
                .setRepeatPassword("securePassword123");
        String request = objectMapper.writeValueAsString(requestDto);
        //When
        UserResponseDto expected = new UserResponseDto()
                .setId(3L)
                .setEmail("john.doe@example.com")
                .setFirstName("John")
                .setLastName("Doe");
        MvcResult result = mockMvc.perform(
                        post("/auth/register")
                                .content(request)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        UserResponseDto actual = objectMapper.readValue(result
                .getResponse().getContentAsString(), UserResponseDto.class);
        //Then
        assertNotNull(result);
        assertEquals(expected, actual);
    }

    @Test
    @WithMockUser
    @DisplayName("Login like user - expected result: successfully login")
    @Sql(scripts = {
            "classpath:database/before/before_TestExecution.sql",
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/after/after_afterTestExecution.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void login_ValidCredentials_Success() throws Exception {
        //Given
        UserLoginRequestDto requestDto = new UserLoginRequestDto()
                .setEmail("bob@mail.com")
                .setPassword("password");
        String jsonRequest = objectMapper.writeValueAsString(requestDto);
        //Then
        mockMvc.perform(
                        post("/auth/login")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser
    @DisplayName("Login like user with invalid password - "
            + "expected result: return error - BAD REQUEST")
    public void login_InCorrectPassword_GetError() throws Exception {
        //Given
        UserLoginRequestDto requestDto = new UserLoginRequestDto()
                .setEmail("bob@mail.com")
                .setPassword("invalid password");
        String jsonRequest = objectMapper.writeValueAsString(requestDto);
        //Then
        mockMvc.perform(
                        post("/auth/login")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }
}
