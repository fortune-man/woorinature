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
    public String addEvent(@RequestParam(value = "title") String title, @RequestParam("date") String date, Model model) {
        // 이벤트 객체 생성
        Event event = new Event();

        // 속성 설정
        event.setTitle(title);
        event.setDate(LocalDate.parse(date));

        // 이벤트 저장
        eventRepository.save(event);

        // 저장된 이벤트 목록을 모델에 추가
        List<Event> events = eventRepository.findAll();

        model.addAttribute("events", events);
        model.addAttribute("message", "휴일 등록 성공 ✅");

        // 리다이렉트하여 페이지 이동
        return "redirect:/index";
    }
}
