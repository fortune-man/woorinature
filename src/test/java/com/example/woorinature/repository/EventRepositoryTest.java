package com.example.woorinature.repository;

import com.example.woorinature.model.Event;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @DisplayName("이벤트_저장과_조회_테스트")
    @Test
    public void eventRepositoryTest() {

        // given
        String expect = "이번주 일요일 가족 식사 모임";
        Event event = new Event();
        event.setTitle("이번주 일요일 가족 식사 모임");

        // when
        event.setDate(LocalDate.of(2024, 06, 23));
        eventRepository.save(event);

        // then
        List<Event> events = eventRepository.findByDate(LocalDate.of(2024, 06, 23));
        assertFalse(events.isEmpty());
        assertEquals(expect, events.get(0).getTitle());
    }
}