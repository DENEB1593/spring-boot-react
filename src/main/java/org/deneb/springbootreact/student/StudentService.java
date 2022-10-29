package org.deneb.springbootreact.student;

import lombok.AllArgsConstructor;
import org.deneb.springbootreact.student.exception.BadRequestException;
import org.deneb.springbootreact.student.exception.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    boolean isExist = studentRepository.findById(studentId).isPresent();
    if (!isExist) {
      throw new StudentNotFoundException(
        String.format("Student not found with id : %s", studentId));
    }

    studentRepository.deleteById(studentId);
  }

}
