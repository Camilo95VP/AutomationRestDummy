package com.co.dummy.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/create_employee.feature",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"com.co.dummy.stepdefinition"},
        tags = "@EN001-HP",
        plugin = {"pretty"}
)

public class CreateEmployeeRunner {
}
