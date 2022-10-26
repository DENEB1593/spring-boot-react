package org.deneb.springbootreact.student;

import lombok.AllArgsConstructor;
import org.deneb.springbootreact.student.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;

  public List<Student> findAll() {
    return studentRepository.findAll();
  }

  public void save(Student student) {
    studentRepository.findByEmail(student.getEmail())
        .ifPresent(s -> {
          throw new BadRequestException(
            String.format("%s already exists", s.getEmail()) );
        });
    studentRepository.save(student);
  }

  public void delete(Long studentId) {
    studentRepository.deleteById(studentId);
  }

}
