package com.redwoods.consumer.consumerservice.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.redwoods.consumer.consumerservice.models.FormDesigner;
import com.redwoods.consumer.consumerservice.models.FormDesignerJson;
import com.redwoods.consumer.consumerservice.repos.FormDesignerJsonRepository;
import com.redwoods.consumer.consumerservice.repos.FormDesignerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;

@Service
public class CsvImportService {

    @Autowired
    private FormDesignerRepository formDesignerRepository;

    @Autowired
    private FormDesignerJsonRepository formDesignerJsonRepository;

    @Transactional
    public void importCsv(String filePath) throws IOException {
        FormDesignerJson formDesignerJson = new FormDesignerJson();
        FormDesigner entity = new FormDesigner();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {

                entity.setId(Long.valueOf(line[0]));
                entity.setCreated_by(line[1]);
                entity.setCreated_on(Long.valueOf(line[2]));
                entity.set_deleted(Boolean.parseBoolean(line[3]));
                entity.setLast_updated_by(line[4]);
                entity.setLast_updated_on(Long.valueOf(line[5]));
                entity.setTitle(line[6]);
                formDesignerJson.setId(Long.valueOf(line[7]));
                formDesignerJson.setCreated_by(line[8]);
                formDesignerJson.setCreated_on(Long.valueOf(line[9]));
                formDesignerJson.set_deleted(Boolean.parseBoolean(line[10]));
                formDesignerJson.setLast_updated_by(line[11]);
                formDesignerJson.setLast_updated_on(Long.valueOf(line[12]));
                formDesignerJson.setFormId(line[13]);
                formDesignerJson.setMetaData(line[14]);
                formDesignerJson.setVersion(Integer.valueOf(line[15]));
                formDesignerJson.setStatus(line[16]);

                // Set other fields as needed
               /* formDesignerRepository.save(entity);
                formDesignerJsonRepository.save(formDesignerJson);*/
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
