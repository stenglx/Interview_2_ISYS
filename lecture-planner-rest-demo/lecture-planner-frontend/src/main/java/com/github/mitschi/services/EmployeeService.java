package com.github.mitschi.services;

import com.github.mitschi.models.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

    @Value("${endpoint.employee}")
    private String employeesEndpointUrl;

    WebClient webClient;

    public EmployeeService() {
        this.webClient = WebClient.builder().build();
    }

    public Flux<Employee> getEmployees(){
        Flux<Employee> response = webClient.get()
                .uri(employeesEndpointUrl)
                .retrieve()
                .bodyToFlux(Employee.class);

        return response;
    }

    public Mono<Employee> addEmployee(Employee e) {
        Mono<Employee> response = webClient.post()
                .uri(employeesEndpointUrl)
                .body(BodyInserters.fromValue(e))
                .retrieve()
                .bodyToMono(Employee.class);

        return response;
    }

    public String getEmployeesEndpointUrl() {
        return employeesEndpointUrl;
    }
}
