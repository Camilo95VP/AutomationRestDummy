package com.co.dummy.stepdefinition;

import com.co.dummy.models.datatable.CreateEmployeeDataTable;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.util.EnvironmentVariables;

import java.util.Map;

import static com.co.dummy.tasks.CreateEmployee.createEmployee;
import static com.co.dummy.tasks.ValidateCreateEmployee.validateCreateEmployee;


public class CreateEmployeeStepDefinition {

    @Managed
    private Actor actor;

    @Managed
    private EnvironmentVariables environmentVariables;

    @DataTableType
    public CreateEmployeeDataTable createEmployeeDataTable(Map<String, String> table) {
        return new ObjectMapper().convertValue(table, CreateEmployeeDataTable.class);
    }

    @Before
    public void setTheStage() {
        String theRestApiBaseUrl = this.environmentVariables.optionalProperty("restapi.baseurl.dummyUrl")
                .orElse("http://");

        OnStage.setTheStage(new OnlineCast());
        actor = Actor.named("User").whoCan(CallAnApi.at(theRestApiBaseUrl));
    }
    @When("the user calls the microservice to register an employee")
    public void theUserCallsTheMicroserviceToRegisterAnEmployee(CreateEmployeeDataTable data) {
        actor.attemptsTo(
                createEmployee(data)
        );
    }

    @Then("the response data is validated")
    public void theResponseDataIsValidated(CreateEmployeeDataTable data) {
        actor.attemptsTo(
                validateCreateEmployee(data)
        );
    }
}
