package com.redwoods.consumer.consumerservice.controllers;

import com.redwoods.consumer.consumerservice.service.impl.CsvExportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;

@RestController
@RequestMapping("/api/export")
public class ExportController {

    @Autowired
    private CsvExportService csvExportService;

    @GetMapping("/csv")
    public void exportToCsv(HttpServletResponse response) throws Exception {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=data.csv");

        PrintWriter writer = response.getWriter();
        csvExportService.exportDataToCsv(writer);
    }
}
