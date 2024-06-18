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

    @GetMapping("/calendar")
    public String getCalender(Model model) {
        List<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return "calendar";
    }

    @PostMapping("/add-event")
    public String addEvent(@RequestParam String title, @RequestParam String date) {
        Event event = new Event();
        event.setTitle(title);
        event.setDate(LocalDate.parse(date));
        eventRepository.save(event);
        return "redirect:/calendar";
    }
}
