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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false) // addFilter = false to disable security filter for unit test.
class NurseControllerTest2 {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private NurseRepository nurseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String API_ENDPOINT = "/api/nurse";

    private Nurse nurse1;

    @BeforeEach
    void setUp() {
        // delete all records before running the tests
        nurseRepository.deleteAll();

        // arrange (precondition)
        User user1 = User.builder()
                .userId(3L)
                .build();

        // arrange (precondition)
        nurse1 = Nurse.builder()
                .licenceNo("A7B2C9")
                .expertise("Critical Care Nursing")
                .gender("Male")
                .user(user1)
                .build();
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
//                .andExpect(jsonPath("$.NurseId").value(nurse1.getNurseId()))
                .andExpect(jsonPath("$.licenceNo").value(nurse1.getLicenceNo()))
                .andExpect(jsonPath("$.expertise").value(nurse1.getExpertise()))
                .andExpect(jsonPath("$.gender").value(nurse1.getGender()))
                .andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains(nurse1.getLicenceNo())));
    }
}