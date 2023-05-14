package com.example.NumberRangeSummarizer.controller;

import com.example.NumberRangeSummarizer.service.NumberRangeSummarizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/numbers")
public class SummarizeController {
    private NumberRangeSummarizer summarizer;

    @Autowired
    public SummarizeController(NumberRangeSummarizer summarizer) {
        this.summarizer = summarizer;
    }

    @PostMapping("/summarize")
    public ResponseEntity<String> summarizeNumbers(@RequestBody String input) {
        try {
            String result = summarizer.summarizeCollection(summarizer.collect(input));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
}
