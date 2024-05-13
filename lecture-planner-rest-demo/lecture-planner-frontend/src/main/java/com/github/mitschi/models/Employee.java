package com.github.mitschi.models;

import lombok.Data;
import lombok.Generated;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class Employee {
    @Generated
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private int employeeNumber;

    public Employee(){
    }
//
//    public void updateFromDto(Employee other) {
//        this.setName(other.getName());
//        this.setEmployeeNumber(other.getEmployeeNumber());
//    }
}
