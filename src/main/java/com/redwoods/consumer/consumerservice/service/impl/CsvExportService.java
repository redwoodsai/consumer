package com.redwoods.consumer.consumerservice.service.impl;

import com.redwoods.consumer.consumerservice.models.FormDesigner;
import com.redwoods.consumer.consumerservice.repos.FormDesignerRepository;
import com.redwoods.consumer.consumerservice.service.FormDesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.PrintWriter;
import java.util.List;

@Service
public class CsvExportService {

    @Autowired
    FormDesignService formDesignService;

    @Autowired
    private FormDesignerRepository formDesignerRepository;

    @Transactional(readOnly = true)
    public void exportDataToCsv(PrintWriter writer) {
        List<FormDesigner> response = formDesignService.getAllData(); // Fetch all entities

        // Write CSV header
        writer.write("FORM DESIGN ID,CREATED BY, CREATED ON ,IS DELETED, LAST UPDATED BY, LAST UPDATED ON, TITLE , " +
                " ID,CREATED BY, CREATED ON ,IS DELETED, LAST UPDATED BY, LAST UPDATED ON, FORM ID, META DATA, VERSION ,STATUS\n");

        // Write CSV content
        for (FormDesigner entity : response) {
            if(entity.getFormDesignerJson()==null ){
                writer.write(String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n", entity.getId(), entity.getCreated_by(), entity.getCreated_on(),entity.is_deleted(),
                        entity.getLast_updated_by(),entity.getLast_updated_on(),entity.getTitle(),
                        "", "","",
                        "","","",
                        "","","",""));

            } else {
                writer.write(String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n", entity.getId(), entity.getCreated_by(), entity.getCreated_on(),entity.is_deleted(),
                        entity.getLast_updated_by(),entity.getLast_updated_on(),entity.getTitle(),
                        entity.getFormDesignerJson().getId(), entity.getFormDesignerJson().getCreated_by(),entity.getFormDesignerJson().getCreated_on(),
                        entity.getFormDesignerJson().is_deleted(),entity.getFormDesignerJson().getLast_updated_by(),entity.getFormDesignerJson().getLast_updated_on(),
                        entity.getFormDesignerJson().getFormId(),entity.getFormDesignerJson().getMetaData(),entity.getFormDesignerJson().getVersion(), entity.getFormDesignerJson().getStatus() ));

            }

        }
    }
}
