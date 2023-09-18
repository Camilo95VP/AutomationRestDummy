package com.co.dummy.models.json.request.generator;

import com.co.dummy.models.datatable.CreateEmployeeDataTable;
import com.co.dummy.models.json.request.model.EmployeeInfo;
import net.serenitybdd.screenplay.Actor;

public class CreateEmployeeInfoBodyGenerator {

    private final CreateEmployeeDataTable data;
    private final EmployeeInfo employeeInfo;
    private final Actor actor;


    public CreateEmployeeInfoBodyGenerator(CreateEmployeeDataTable data,Actor actor) {
        this.data = data;
        this.actor = actor;
        this.employeeInfo = EmployeeInfo.builder().build();
    }

    private void setName(){
        this.employeeInfo.setName(this.data.getName());
    }

    private void setSalary(){
        this.employeeInfo.setSalary(this.data.getSalary());
    }

    private void setAge(){
        this.employeeInfo.setAge(this.data.getAge());
    }

    public EmployeeInfo generate(){
        setName();
        setSalary();
        setAge();
        return this.employeeInfo;
    }
}
