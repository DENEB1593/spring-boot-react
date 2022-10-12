package org.deneb.springbootreact.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

  @GetMapping
  public List<Student> findAll() {
    return List.of(
            new Student(1L, "john", "john@naver.com", Gender.MALE),
            new Student(2L, "park", "park@daum.net", Gender.FEMALE),
            new Student(3L, "kim", "kim@nate.com", Gender.MALE)
    );
  }

}
