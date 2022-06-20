package ru.springcourse.FirstRestApp.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class SensorDto {


    @NotEmpty(message = "Name should not be empty!")
    @Length(min =  3, max = 30, message = "Name should be between 3 and 30 characterrs")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
