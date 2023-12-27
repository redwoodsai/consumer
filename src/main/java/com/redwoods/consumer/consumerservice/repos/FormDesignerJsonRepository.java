package com.redwoods.consumer.consumerservice.repos;

import com.redwoods.consumer.consumerservice.models.FormDesignerJson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FormDesignerJsonRepository extends JpaRepository<FormDesignerJson, Long> {

    @Query("From FormDesignerJson f where f.formId = :sss")
    FormDesignerJson getData(@Param("sss") Long formId);


    @Query(value = "select * from   liquibasedb.Form_Designer_JSON fdj where fdj.id = :anyId", nativeQuery = true)
    public Optional<FormDesignerJson> getdataById(@Param("anyId")Long id);
//    @Query(value = "SELECT fdj FROM liquibasedb.Form_Designer_JSON fdj\n" +
//            "WHERE fdj.id = :id\n" +
//            "ORDER BY fdj.version DESC",nativeQuery = true)
    @Query(value = "SELECT fdj.version \n" +
            "FROM liquibasedb.form_designer_json AS fdj\n" +
            "LEFT JOIN liquibasedb.form_designer AS fd ON fdj.id = fd.id\n" +
            "WHERE fdj.id = :id\n" +
            "ORDER BY fdj.version DESC;",nativeQuery = true)
    FormDesignerJson findFirstByFormDesignerOrderByVersionDesc(@Param("id") Long id);

    @Query(value = "select * from liquibasedb.form_designer_json as fdj  where fdj.formId = :formId And fdj.status = 'active'  ",nativeQuery = true)
    Optional<FormDesignerJson> findByFormId(@Param("formId") Long formId);
}
