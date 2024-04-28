package com.ivoronline.springboot_database_simplejdbcinsert.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MyController {

  //PROPERTIES
  @Autowired private JdbcTemplate jdbcTemplate;

  //==========================================================
  // HELLO
  //==========================================================
  @ResponseBody
  @GetMapping("/hello")
  public int hello() {
    int deletedRecords = insertRecords("John", 10);
    return deletedRecords;
  }

  //==========================================================
  // INSERT RECORDS
  //==========================================================
  public int insertRecords(String name, Integer age) {

    //CONFIGURE INSERT STATEMENT
    SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
      .withTableName("PERSON")
      .usingGeneratedKeyColumns("ID");

    //SET PARAMETERS
    final Map<String, Object> parameters = new HashMap<>();
                              parameters.put("NAME", "Jill");
                              parameters.put("AGE" , 40    );

    //EXECUTE & RETURN ID
    Number id = insert.executeAndReturnKey(parameters);

    //RETURN ID
    return id.intValue();

  }

}
