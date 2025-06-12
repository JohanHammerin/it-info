package se.johan.ituppgift.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import se.johan.ituppgift.dto.AppUserDTO;
import se.johan.ituppgift.model.AppUser;
import se.johan.ituppgift.model.LoginRequest;
import se.johan.ituppgift.repository.AppUserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserCreationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String jwtToken;

    @BeforeEach
    void setUp() throws Exception {
        appUserRepository.deleteAll();

        AppUser existingUser = new AppUser();
        existingUser.setUsername("existingUser");
        existingUser.setPassword(passwordEncoder.encode("existingPassword"));
        existingUser.setRole("ADMIN");
        appUserRepository.save(existingUser);

        LoginRequest loginRequest = new LoginRequest("existingUser", "existingPassword");

        MvcResult authResult = mockMvc.perform(post("/request-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn();

        jwtToken = authResult.getResponse().getContentAsString();
        assertNotNull(jwtToken);
        assertFalse(jwtToken.isEmpty());
    }

    @Test
    void createNewUser_shouldSucceed_whenAuthenticatedWithValidJwt() throws Exception {

        mockMvc.perform(post("/registration/new")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "fresh_new_user")
                        .param("password", "StrongP4$$w0rd!!")
                        .param("consentGiven", "true")
                        .param("role", "USER")) // <--- Lägg till rollen här
                .andExpect(status().isCreated());

        assertNotNull(appUserRepository.findByUsername("fresh_new_user"));
    }

    @Test
    void createNewUser_shouldFail_whenJwtIsMissing() throws Exception {
        AppUserDTO newAccountDto = new AppUserDTO();
        newAccountDto.setUsername("unauthenticated_user");
        newAccountDto.setPassword("weak_pass"); //

        mockMvc.perform(post("/registration/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", newAccountDto.getUsername())
                        .param("password", newAccountDto.getPassword()))
                .andExpect(status().isUnauthorized());
    }
}