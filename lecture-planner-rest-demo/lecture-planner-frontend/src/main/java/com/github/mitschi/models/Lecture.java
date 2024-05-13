package com.github.mitschi.models;

import lombok.Data;
import lombok.Generated;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Lecture {
    @Generated
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String number;

    @NonNull
    private Long lecturerId;

    public Lecture(){
    }

//    public void updateFromDto(Lecture other) {
//        this.setName(other.getName());
//        this.setNumber(other.getNumber());
//        this.setLecturerId(other.getLecturerId());
//    }
}
