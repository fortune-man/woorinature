package com.example.woorinature.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @DisplayName("이벤트_생성_테스트")
    @Test
    void generateEventTest() {
        // given
        Event event = new Event();
        String expect = "이번주 일요일 가족 식사 모임";

        // when
        event.setTitle("이번주 일요일 가족 식사 모임");
        event.setDate(LocalDate.of(2024, 06, 23));

        // then
        assertNotNull(event);
        assertEquals(expect, event.getTitle());
        assertEquals(LocalDate.of(2024, 06, 23), event.getDate());
    }
}