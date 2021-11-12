package com.cc.liquibase.stepsCucumber;

import com.cc.liquibase.LiquibaseApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = LiquibaseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = LiquibaseApplication.class, loader = SpringBootContextLoader.class)
public class CucumberBase {
}
