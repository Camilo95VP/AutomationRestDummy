package com.co.dummy.tasks;

import com.co.dummy.models.datatable.CreateEmployeeDataTable;
import com.co.dummy.models.json.request.generator.CreateEmployeeInfoBodyGenerator;
import com.co.dummy.models.json.request.model.EmployeeInfo;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.RestQueryFunction;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateEmployee implements Task {

    private CreateEmployeeDataTable data;

    @Managed
    private EnvironmentVariables environmentVariables;

    public CreateEmployee(CreateEmployeeDataTable data) {
        this.data = data;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        CreateEmployeeInfoBodyGenerator createEmployeeInfoBodyGenerator = new CreateEmployeeInfoBodyGenerator(this.data, actor);
        EmployeeInfo body = createEmployeeInfoBodyGenerator.generate();
        String endpoint = this.environmentVariables.optionalProperty("endpoints.dummy.create").orElse("/");

        RestQueryFunction restConfiguration = requestSpecification -> {
            requestSpecification.body(body);
            requestSpecification.relaxedHTTPSValidation();
            return requestSpecification;
        };

        actor.attemptsTo(
                Post.to(endpoint).with(restConfiguration)
        );
    }

    public static CreateEmployee createEmployee(CreateEmployeeDataTable data){
        return instrumented(CreateEmployee.class, data);
    }

    
}
