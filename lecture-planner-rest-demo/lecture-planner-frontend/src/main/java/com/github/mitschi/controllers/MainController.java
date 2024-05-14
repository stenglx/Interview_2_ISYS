package com.github.mitschi.controllers;

import com.github.mitschi.models.Employee;
import com.github.mitschi.models.Lecture;
import com.github.mitschi.services.EmployeeService;
import com.github.mitschi.services.LectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final EmployeeService employeeService;
    private final LectureService lectureService;

    @GetMapping(value="/")
//    @GetMapping(value="/", produces = MediaType.TEXT_HTML_VALUE)
//    @ResponseBody
    public String main(Model model){
//        System.out.println("Using employee endpoint " + employeeService.getEmployeesEndpointUrl());
//        System.out.println("Using lecture endpoint " + lectureService.getLectureEndpointUrl());

        Employee[] employees = employeeService.getEmployees()
                .collectList().block().toArray(new Employee[]{});
        Lecture[] lectures = lectureService.getLectures()
                .collectList().block().toArray(new Lecture[]{});

        model.addAttribute("employees", employees);
        model.addAttribute("lectures", lectures);

        return "index";
    }

    @GetMapping("/addEmployee")
    public RedirectView addEmployee(@RequestParam("pEmpName") String name,
                                    @RequestParam("pEmpNum") int num,
                                    Model model){
        Employee e = new Employee(name, num);
        employeeService.addEmployee(e).block();

        // redirect back to home
        return new RedirectView("/");
    }

    @GetMapping("/addLecture")
    public RedirectView addLecture(@RequestParam("pLecName") String name,
                                    @RequestParam("pLecNum") String num,
                                    @RequestParam("pLecEmpId") long empId,
                                    Model model){
        Lecture l = new Lecture(name, num, empId);
        lectureService.addLecture(l).block();

        // redirect back to home
        return new RedirectView("/");
    }

    @GetMapping("/deleteLecture") // map HTTP Get Request to method delete Lecture
    public RedirectView deleteLecture(@RequestParam("pLecId") long pLecId, Model model)
    {
        lectureService.deleteLecture(pLecId).block(); // block current thread to wait for result

        return new RedirectView("/");
    }

    @GetMapping("/deleteEmployeeAndLectures")
    public RedirectView deleteEmployeeAndLectures(@RequestParam("pEmpId") long pEmpId, Model model)
    {
        // -> error happens during deletion -> block will throw error
        //
        try {
            // make backend do work
            lectureService.deleteLecturesOfEmployee(pEmpId).block();
            // if success
            employeeService.deleteEmployee(pEmpId).block();
            return new RedirectView("/");
        }
        catch (Exception e){
            return new RedirectView("/error"); // maybe add other view with error message
        }
    }

}