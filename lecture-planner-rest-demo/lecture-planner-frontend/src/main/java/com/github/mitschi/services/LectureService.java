package com.github.mitschi.services;

import com.github.mitschi.models.Employee;
import com.github.mitschi.models.Lecture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.concurrent.ForkJoinPool;

@Service
public class LectureService {
    @Value("${endpoint.lecture}")
    private String lectureEndpointUrl;

    WebClient webClient;

    public LectureService() {
        this.webClient = WebClient.builder().build();
    }

    public Flux<Lecture> getLectures(){
        Flux<Lecture> response = webClient.get()
                .uri(lectureEndpointUrl)
                .retrieve()
                .bodyToFlux(Lecture.class);

        return response;
    }

    public Mono<Lecture> addLecture(Lecture l) {
        Mono<Lecture> response = webClient.post()
                .uri(lectureEndpointUrl)
                .body(BodyInserters.fromValue(l))
                .retrieve()
                .bodyToMono(Lecture.class);

        return response;
    }

    public Mono<Void> deleteLecture(long pLecId){
        Mono<Void> response = webClient.delete() // send delete request to Lecture Controller
                .uri(lectureEndpointUrl + "/" + pLecId)
                .retrieve()//client shall retrieve response from server
                .bodyToMono(Void.class);

        return response;//.then();
    }

    public Mono<Void> deleteLecturesOfEmployee(long pEmpId) { // // send delete request to Lecture Controller
        Mono<Void> response = webClient.delete() // send delete request to Lecture Controller
                .uri(lectureEndpointUrl + "/emp/" + pEmpId)
                .retrieve()//client shall retrieve response from server
                .bodyToMono(Void.class);

        return response;//.then();
    }
    public String getLectureEndpointUrl() {
        return lectureEndpointUrl;
    }



}
