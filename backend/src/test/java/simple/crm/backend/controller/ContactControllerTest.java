package simple.crm.backend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Connection;
import java.util.List;
import javax.sql.DataSource;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import simple.crm.backend.dto.contact.ContactRequestDto;
import simple.crm.backend.dto.contact.ContactResponseDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactControllerTest {
    protected static MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll(@Autowired WebApplicationContext applicationContext) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
    }

    @AfterAll
    static void afterAll(@Autowired DataSource dataSource) {
        teardown(dataSource);
    }

    @SneakyThrows
    static void teardown(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(true);
            ScriptUtils.executeSqlScript(connection,
                    new ClassPathResource("database/after/after_afterTestExecution.sql")
            );
        }
    }

    @Test
    @WithUserDetails("test@mail.com")
    @DisplayName("Create contact, valid request")
    @Sql(scripts = {
            "classpath:database/before/before_TestExecution.sql",
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/after/after_afterTestExecution.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void createContact_ValidRequest_Ok() throws Exception {
        //Given
        ContactRequestDto requestDto = new ContactRequestDto()
                .setEmail("mail@mail.com ")
                .setName("Glory")
                .setPhone("(343)373-3733")
                .setClientId(1L);
        String jsonRequest = objectMapper.writeValueAsString(requestDto);

        //When
        mockMvc.perform(
                        post("/contacts")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("test@mail.com")
    @DisplayName("Update contact, valid request")
    @Sql(scripts = {
            "classpath:database/before/before_TestExecution.sql",
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/after/after_afterTestExecution.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void updateContact_ValidRequest_Ok() throws Exception {
        //Given
        Long id = 1L;
        String expectedContactName = "Glory UPD";
        ContactRequestDto requestDto = new ContactRequestDto()
                .setEmail("mail@mail.com")
                .setName("Glory UPD")
                .setPhone("(343)373-3733")
                .setClientId(1L);
        String jsonRequest = objectMapper.writeValueAsString(requestDto);

        //When
        MvcResult mvcResult = mockMvc.perform(
                        put("/contacts/{id}", id)
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk()).andReturn();
        ContactResponseDto actual = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(), ContactResponseDto.class);
        //Then
        assertNotNull(actual);
        assertEquals(expectedContactName, actual.getName());
    }

    @Test
    @WithUserDetails("test@mail.com")
    @DisplayName("Get all contacts, valid request")
    @Sql(scripts = {
            "classpath:database/before/before_TestExecution.sql",
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/after/after_afterTestExecution.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getAllContacts_ValidRequest_Ok() throws Exception {
        //Given

        MvcResult result = mockMvc.perform(
                        get("/contacts")
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk())
                .andReturn();

        List<ContactResponseDto> actual = objectMapper.readValue(result
                .getResponse().getContentAsString(), new TypeReference<>() {
                });
        //Then
        assertNotNull(actual);
        assertEquals(2, actual.size());
    }

    @Test
    @WithUserDetails("test@mail.com")
    @DisplayName("Get contacts by client, valid request")
    @Sql(scripts = {
            "classpath:database/before/before_TestExecution.sql",
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/after/after_afterTestExecution.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getContactsByClient_ValidRequest_Ok() throws Exception {
        //Given
        Long clientId = 1L;
        String expectedContactName = "Joe";
        MvcResult result = mockMvc.perform(
                        get("/contacts/client/{clientId}", clientId)
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk())
                .andReturn();

        List<ContactResponseDto> actual = objectMapper.readValue(result
                .getResponse().getContentAsString(), new TypeReference<>() {
                });
        //Then
        assertNotNull(actual);
        assertEquals(expectedContactName, actual.get(0).getName());
    }

    @Test
    @WithUserDetails("test@mail.com")
    @DisplayName("Get contact by id, valid request")
    @Sql(scripts = {
            "classpath:database/before/before_TestExecution.sql",
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/after/after_afterTestExecution.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getContactById_ValidRequest_Ok() throws Exception {
        //Given
        Long id = 1L;
        String expectedName = "Joe";
        //When

        MvcResult mvcResult = mockMvc.perform(
                        get("/contacts/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk()).andReturn();

        ContactResponseDto actual = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(), ContactResponseDto.class);
        //Then
        assertNotNull(actual);
        assertEquals(expectedName, actual.getName());
    }

    @Test
    @WithUserDetails("test@mail.com")
    @DisplayName("Delete contact, valid request")
    @Sql(scripts = {
            "classpath:database/before/before_TestExecution.sql",
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/after/after_afterTestExecution.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void deleteContact_ValidRequest_Ok() throws Exception {
        //Given
        Long id = 2L;
        //When
        mockMvc.perform(
                        delete("/contacts/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();
    }
}
