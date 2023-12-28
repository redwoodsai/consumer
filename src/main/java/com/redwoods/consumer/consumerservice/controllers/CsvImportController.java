package com.redwoods.consumer.consumerservice.controllers;

import com.redwoods.consumer.consumerservice.service.impl.CsvImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/import")
public class CsvImportController {

    @Autowired
    private CsvImportService importService;

    @PostMapping("/csv")
    public String importCsv(@RequestParam("file") String filePath) {
        try {
            importService.importCsv(filePath);
            return "CSV import successful!";
        } catch (IOException e) {
            e.printStackTrace();
            return "CSV import failed!";
        }
    }
}
