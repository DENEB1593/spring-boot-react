package org.deneb.springbootreact.student;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
@AllArgsConstructor
public class StudentController {

  private final StudentService studentService;

  @GetMapping
  public List<Student> findAll() {
    return studentService.findAll();
  }

  @PostMapping
  public void save(@RequestBody Student student) {
   studentService.save(student);
  }

}
