package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.annotate.JsonIgnore;
import org.example.model.EnumRole;
import org.example.model.Patient;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false) // addFilter = false to disable security filter for unit test.
class UserControllerTest {

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
        userRepository.deleteAll();

        // arrange (precondition)
        user1 = User.builder()
                .name("John Smith")
                .email("johnsmith@gmail.com")
                .phone("98765432")
                .role(EnumRole.Nurse)
                .password("nurse1234")
                .build();

        // arrange (precondition)
        user2 = User.builder()
                .name("Joe Smith")
                .email("joesmith@gmail.com")
                .phone("98765411")
                .role(EnumRole.Nurse)
                .password("nurse1234")
                .build();

        userList.add(user1);
        userList.add(user2);
    }

    @Test
    @DisplayName("** JUnit test: get all users from User Ctrl. **")
    void allUsers() throws Exception {
        // arrange - setup precondition
        userRepository.saveAll(userList);

        // act - action or behaviour to test
        ResultActions resultActions = mockMvc.perform(get(API_ENDPOINT));

        // assert - verify the output (as expected)
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(userList.size())));
    }

    @Test
    @DisplayName("** JUnit test: get user by email from User Ctrl. **")
    void getUserByEmail() throws Exception {
        // arrange - setup precondition
        userRepository.save(user1);
        // act -  action or behaviour to test
        ResultActions resultActions = mockMvc.perform(get(API_ENDPOINT.concat("/{email}"), user1.getEmail()));
        // assert - verify the output
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name").value(user1.getName()))
                .andExpect(jsonPath("$.email").value(user1.getEmail()))
                .andExpect(jsonPath("$.phone").value(user1.getPhone()))
//                .andExpect(jsonPath("$.role").value(user1.getRole()))
                .andExpect(jsonPath("$.role").value("Nurse"))
                .andExpect(jsonPath("$.password").value(user1.getPassword()))
                .andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains(user1.getEmail())));
    }
}