package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.EnumRole;
import org.example.model.Nurse;
import org.example.model.User;
import org.example.repository.NurseRepository;
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

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false) // addFilter = false to disable security filter for unit test.
class NurseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private NurseRepository nurseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String API_ENDPOINT = "/api/nurse";
    private final List<Nurse> nurseList = new ArrayList<>();

    private Nurse nurse1, nurse2;

    // Runs before each JUnit test operation
    @BeforeEach
    void setUp() {
        // delete all records before running the tests
        nurseRepository.deleteAll();

        // arrange (precondition)
        User user1 = User.builder()
                .userId(2L)
                .role(EnumRole.Nurse)
                .build();

        // arrange (precondition)
        nurse1 = Nurse.builder()
                .licenceNo("A7B2C9")
                .expertise("Critical Care Nursing")
                .gender("Male")
                .user(user1)
                .build();

        // arrange (precondition)
        nurse2 = Nurse.builder()
                .licenceNo("X3Y8Z1")
                .expertise("Pediatric Nursing")
                .gender("Female")
                .build();

        nurseList.add(nurse1);
        nurseList.add(nurse2);
    }

    @Test
    @DisplayName("** JUnit test: save nurse from Nurse Ctrl. **")
    void saveNurse() throws Exception {
        // arrange - setup precondition
        String requestBody = objectMapper.writeValueAsString(nurse2);

        // act - action or behaviour to test
        ResultActions resultActions = mockMvc.perform(post(API_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        // assert - verify the output
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.licenceNo").value(nurse2.getLicenceNo()))
                .andExpect(jsonPath("$.expertise").value(nurse2.getExpertise()))
                .andExpect(jsonPath("$.gender").value(nurse2.getGender()))
                .andExpect(result -> assertNotNull(result.getResponse().getContentAsString()))
                .andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains(nurse2.getLicenceNo())));
    }

    @Test
    @DisplayName("** JUnit test: get all nurse from Nurse Ctrl. **")
    void allNurse() throws Exception {
        // arrange - setup precondition
        nurseRepository.saveAll(nurseList);

        // act - action or behaviour to test
        ResultActions resultActions = mockMvc.perform(get(API_ENDPOINT));

        // assert - verify the output (as expected)
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(nurseList.size())));
    }

    @Test
    @DisplayName("** JUnit test: get nurse by userId from Nurse Ctrl. **")
    void getNurseByUserId() throws Exception {
        // arrange - setup precondition
        nurseRepository.save(nurse1);
        // act -  action or behaviour to test
        ResultActions resultActions = mockMvc.perform(get(API_ENDPOINT.concat("/user/{userId}"), nurse1.getUser().getUserId()));
        // assert - verify the output
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.licenceNo").value(nurse1.getLicenceNo()))
                .andExpect(jsonPath("$.expertise").value(nurse1.getExpertise()))
                .andExpect(jsonPath("$.gender").value(nurse1.getGender()))
                .andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains(nurse1.getLicenceNo())));
    }
}