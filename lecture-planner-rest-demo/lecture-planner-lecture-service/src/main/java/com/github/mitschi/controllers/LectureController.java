package com.github.mitschi.controllers;

import com.github.mitschi.models.Lecture;
import com.github.mitschi.models.validation.LectureValidator;
import com.github.mitschi.repositories.LectureRepository;
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

@RestController()
@RequiredArgsConstructor
@RequestMapping("/lectures") // uri start + annotation of methods
@Slf4j
@Tag(name = "Lecture Controller", description = "Rest Controller class for handling lecture related backend operations")
public class LectureController {
    private final LectureRepository lectureDao;
    private final LectureValidator validator;

    @GetMapping()
    @Operation(summary = "Returns a list of all lectures")
    public List<Lecture> listLectures() {
        return lectureDao.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns a lecture via its ID")
    public ResponseEntity<Lecture> getLectureById(@PathVariable("id") Long id) {
        try {
            Lecture lecture = lectureDao.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Lecture not found for id: " + id));
            return ResponseEntity.ok(lecture);
        } catch (ResourceNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lecture Not Found", exc);
        }
    }

    @PostMapping()
    @Operation(summary = "Saves a new lecture to the database")
    public ResponseEntity<Lecture> createLecture(@RequestBody Lecture lecture) {
        if (!validator.isLectureValid(lecture))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lecture values are not valid");

        lectureDao.save(lecture);
        return new ResponseEntity<>(lecture, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates the values of a lecture")
    public ResponseEntity<Lecture> updateLecture(@PathVariable("id") Long id,
                                                 @RequestBody Lecture lectureDto) {
        if (!validator.isLectureValid(lectureDto))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lecture values are not valid");

        try {
            Lecture origLecture = lectureDao.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Lecture not found for id: " + id));

            origLecture.updateFromDto(lectureDto);
            Lecture updatedLecture = lectureDao.save(origLecture);
            return ResponseEntity.ok(updatedLecture);
        } catch (ResourceNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lecture Not Found", exc);
        }
    }

    @DeleteMapping("/{id}") // add path variable here = extracted from uri
    @Operation(summary = "Deletes a lecture from the database")
    // ? -> depending on case we return different types
    // can also use void as spring is capable of handling
    public ResponseEntity<?> deleteLecture(@PathVariable Long id) {
        try { // from update
            Lecture lecture_to_delete = lectureDao.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Lecture not found for id: " + id));
                    // @ResponseStatus(code = HttpStatus.NOT_FOUND) -> returns 404

            lectureDao.delete(lecture_to_delete);
            return ResponseEntity.noContent().build(); // = set status code 204
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lecture Not Found", e); // 404 - Not found
        }
    }

    @DeleteMapping("/emp/{empId}")
    @Operation(summary = "Deletes all lectures of certain employee from the database")
    public ResponseEntity<?> deleteLecturesOfEmployee(@PathVariable("empId") Long empId) {
        List<Lecture> lectures = lectureDao.findAll();

        for (Lecture lecture : lectures){
            if (lecture.getLecturerId().equals(empId)){
                lectureDao.delete(lecture);
            }
        }

        return ResponseEntity.noContent().build(); // build = finalize response creation
    }

}
