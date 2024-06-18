package com.example.woorinature.controller;

import com.example.woorinature.model.Event;
import com.example.woorinature.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CalenderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventRepository eventRepository;

    @BeforeEach
    void setUp() {
        eventRepository.deleteAll();
    }

    @Test
    void 캘린더_조회() throws Exception {
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }


    @Test
    void 이벤트_추가() throws Exception {

        // given
        String expect = "이번주 일요일 가족 식사 모임";

        // then
        mockMvc.perform(post("/add-event")
                        .param("title", expect)
                        .param("date", "2023-06-23"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));

        Event event = eventRepository.findByDate(LocalDate.of(2023, 06, 23)).get(0);
        assertEquals(expect, event.getTitle());

    }

}