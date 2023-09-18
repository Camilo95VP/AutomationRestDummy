package com.co.dummy.tasks;

import com.co.dummy.models.datatable.CreateEmployeeDataTable;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;

public class ValidateCreateEmployee implements Task {

    private final CreateEmployeeDataTable data;

    public ValidateCreateEmployee(CreateEmployeeDataTable data) {
        this.data = data;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.should(
                seeThatResponse(String.format("The code response is %s", data.getCodeResponse()),
                        response -> response
                                .statusCode(Integer.parseInt(data.getCodeResponse()))
                ));

        if (data.getCodeResponse().equals("200")) {
            actor.should(
                    seeThatResponse("The json response body has the correct format",
                            response -> response
                                    .body(matchesJsonSchemaInClasspath("schemas/create_employee_json_response_hp.json"))
                    ),
                    seeThatResponse("The json response body has the correct information",
                            response -> response
                                    .body("status", equalTo(data.getStatus()))
                                    .body("message", equalTo(data.getMessage()))

                    )
            );
        }
      }

        public static ValidateCreateEmployee validateCreateEmployee(CreateEmployeeDataTable data) {
            return instrumented(ValidateCreateEmployee.class, data);
        }
}

