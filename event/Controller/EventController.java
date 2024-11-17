package com.example.event.Controller;

import com.example.event.ApiResponse.ApiResponse;
import com.example.event.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/event")
public class EventController {

    ArrayList<Event> events= new ArrayList<>();
@GetMapping("/get")
    public ArrayList<Event> getEvents() {
        return events;
    }

@PostMapping("/add")
    public ApiResponse addEvent(@RequestBody Event event) {
    events.add(event);
    return new ApiResponse("Successfully added event");
    }


    @PutMapping("/update/{id}")
    public ApiResponse updateEvent(@PathVariable int id ,@RequestBody Event event) {
    for (Event e : events) {
        if (e.getId() == id) {
            e.setDescription(event.getDescription());
            e.setCapacity(event.getCapacity());
            e.setEndDate(event.getEndDate());
            e.setStartDate(event.getStartDate());
        }
    }return  new ApiResponse("Successfully updated event");
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteEvent(@PathVariable int id) {
        events.removeIf(event -> event.getId() == id);
        return  new ApiResponse("Successfully deleted event");
    }
    @PutMapping("/changeCapacity/{id}")
    public ApiResponse changeCapacity(@PathVariable int id ,@RequestBody int capacity) {
    for (Event e : events) {
        if (e.getId() == id) {
            e.setCapacity(capacity);
        }
    }return  new ApiResponse("Successfully changed event capacity");
    }



    @GetMapping("/eventBy-id/{id}")
    public Event getEventById(@PathVariable int id) {
    for (Event event : events) {
        if (event.getId() == id) {
            return event;
        }
    }return  null;
    }


}
