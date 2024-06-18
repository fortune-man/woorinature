package com.example.woorinature.controller;

import com.example.woorinature.model.Event;
import com.example.woorinature.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CalenderController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/index")
    public String getCalender(Model model) {
        List<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return "index";
    }

    @PostMapping("/add-event")
    public String addEvent(@RequestParam String title, @RequestParam String date, Model model) {
        Event event = new Event();
        event.setTitle(title);
        event.setDate(LocalDate.parse(date));
        eventRepository.save(event);

        // 리스트에 이벤트 저장 -> 조회 -> 모델에 추가
        List<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        model.addAttribute("message", "휴일 등록 성공!");

        // 현재 페이지로 이동 -> 200 상태 코드 반환
        return "redirect:/index";
    }
}
