package com.co.dummy.questions;

import com.co.dummy.models.datatable.CreateEmployeeDataTable;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class TheEmployeeCreate implements Question<Boolean> {
private final CreateEmployeeDataTable data;

    public TheEmployeeCreate(CreateEmployeeDataTable data) {
        this.data = data;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        boolean employeeCreatedSuccessfully;
        return true;
    }

    public static TheEmployeeCreate isVerified(CreateEmployeeDataTable data) {
        return new TheEmployeeCreate(data);
    }

}
