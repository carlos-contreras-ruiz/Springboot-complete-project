package com.cc.liquibase.stepsCucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "com.cc.liquibase.stepsCucumber")
public class CucumberConfig {
}
