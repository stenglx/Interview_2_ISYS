package com.github.mitschi.controllers;

import com.github.mitschi.models.Employee;
import com.github.mitschi.models.validation.EmployeeValidator;
import com.github.mitschi.repositories.EmployeeRepository;
import com.github.mitschi.util.IllegalResourceException;
import com.github.mitschi.util.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
@Slf4j
@Tag(name = "Employee Controller", description = "Rest Controller class for handling employee related backend operations")
public class EmployeeController {
    private final EmployeeRepository repository;
    private final EmployeeValidator validator;

    @GetMapping()
    @Operation(summary = "Returns a list of all employees")
    public List<Employee> listEmployees() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns an employee via its ID")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        try {
            Employee employee = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found for id: " + id));
            return ResponseEntity.ok(employee);
        } catch (ResourceNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found", exc);
        }
    }

    @PostMapping()
    @Operation(summary = "Adds a new employee to the database")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        if (!validator.isEmployeeValid(employee))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee values are not valid");

        repository.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates the values of an employee")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id,
                                                   @RequestBody Employee employee) {
        if (!validator.isEmployeeValid(employee))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee values are not valid");

        try {
            Employee origEmployee = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found for id: " + id));

            origEmployee.updateFromDto(employee);
            Employee updatedEmployee = repository.save(origEmployee);
            return ResponseEntity.ok(updatedEmployee);
        } catch (ResourceNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found", exc);
        }
    }
}
