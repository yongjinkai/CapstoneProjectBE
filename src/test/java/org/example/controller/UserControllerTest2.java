package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.EnumRole;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false) // addFilter = false to disable securi
class UserControllerTest2 {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String API_ENDPOINT = "/api/user";
    private final List<User> userList = new ArrayList<>();

    private User user1, user2;

    // Runs before each JUnit test operation
    @BeforeEach
    void setUp() {
        // delete all records before running the tests
//        userRepository.deleteAll();

        // arrange (precondition)
        user1 = User.builder()
                .name("John Smith")
                .email("johnsmith@gmail.com")
                .phone("98765432")
                .role(EnumRole.Patient)
                .password("nurse1234")
                .build();

        // arrange (precondition)
        user2 = User.builder()
                .name("Joe Smith")
                .email("joesmith@gmail.com")
                .phone("98765411")
                .role(EnumRole.Patient)
                .password("nurse1234")
                .build();

        userList.add(user1);
        userList.add(user2);
    }

    @Test
    @DisplayName("** JUnit test: save user from User Ctrl. **")
    void saveUser() throws Exception {
        // arrange - setup precondition
        String requestBody = objectMapper.writeValueAsString(user1);

        // act - action or behaviour to test
        ResultActions resultActions = mockMvc.perform(post(API_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        // assert - verify the output
        resultActions.andExpect(status().isOk())
                .andDo(print());
//                .andExpect(jsonPath("$.name").value(user1.getName()))
//                .andExpect(jsonPath("$.email").value(user1.getEmail()))
//                .andExpect(jsonPath("$.phone").value(user1.getPhone()))
//                .andExpect(jsonPath("$.role").value(EnumRole.Patient))
//                .andExpect(jsonPath("$.password").value(user1.getPassword()))
//                .andExpect(result -> assertNotNull(result.getResponse().getContentAsString()))
//                .andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains(user1.getEmail())));
    }

    @Test
    @DisplayName("** JUnit test: update user from User Ctrl. **")
    void updateUser() throws Exception {
        // arrange - setup precondition
        userRepository.save(user1);

        User updateUser = userRepository.findById(user1.getUserId()).get();

        updateUser.setName("John Doe");
        updateUser.setEmail("johndoe@gmail.com");
        updateUser.setPhone("98765432");
        updateUser.setRole(EnumRole.Patient);
        updateUser.setPassword("abcd1234");

        String requestBody = objectMapper.writeValueAsString(updateUser);

        // act -  action or behaviour to test
        ResultActions resultActions = mockMvc.perform(put(API_ENDPOINT.concat("/{id}"), updateUser.getUserId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        // assert - verify the output
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name").value(user1.getName()))
                .andExpect(jsonPath("$.email").value(user1.getEmail()))
                .andExpect(jsonPath("$.phone").value(user1.getPhone()))
                .andExpect(jsonPath("$.role").value("Patient"))
                .andExpect(jsonPath("$.password").value(user1.getPassword()));
    }
}