package com.snapp.fintech.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snapp.fintech.config.security.service.AuthenticationService;
import com.snapp.fintech.config.security.service.dto.request.SignUpRequest;
import com.snapp.fintech.config.security.service.dto.request.SigninRequest;
import com.snapp.fintech.config.security.service.dto.response.JwtAuthenticationResponse;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class BaseTestUtils {

    @Autowired
    protected MockMvc mockMvc;

    protected ObjectMapper objectMapper = new ObjectMapper();

    protected String performSignInAndGetToken(SignUpRequest request) throws Exception {
        JwtAuthenticationResponse response = new JwtAuthenticationResponse("sample-token");


        String responseContent = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/signup")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        return objectMapper.readValue(responseContent, JwtAuthenticationResponse.class).getToken();
    }

}
