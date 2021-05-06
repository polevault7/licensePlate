package com.example.number.controller;



import com.example.number.model.PlateNumber;
import com.example.number.repository.PlateNumberRepository;
import com.example.number.service.PlateNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/numbers")
public class PlateNumberController {
    private final PlateNumberService service;
    private final PlateNumberRepository repository;


    @GetMapping
    public List<PlateNumber> getAll() {
        return repository.getAll();
    }

    @GetMapping("/isExist/{plate}")
    public Boolean isExists(@PathVariable String plate) {
        return repository.isExists(plate);
    }

    @GetMapping("/{id}")
    public PlateNumber getById(@PathVariable long id) {
        return repository.getById(id);
    }

    //
//
    @PostMapping
    public PlateNumber save(@RequestBody PlateNumber item) {
        return repository.save(item);
    }

    @GetMapping("/generateRandom/")
    public String saveAsRandom() {
        return service.getRandom().toString() + " 116 RUS";
    }

    @GetMapping("/generateNext/")
    public String saveAsNext() {
        return service.getNext().toString() + " 116 RUS";
    }

}

