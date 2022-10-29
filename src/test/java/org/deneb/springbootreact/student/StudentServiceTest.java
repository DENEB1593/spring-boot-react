package org.deneb.springbootreact.student;

import org.deneb.springbootreact.student.exception.BadRequestException;
import org.deneb.springbootreact.student.exception.StudentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;

import static java.util.Optional.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository studentRepository;
  @InjectMocks
  private StudentService studentService;


  @Test
  @DisplayName("학생_전체_조회")
  void findAll() {
    // when
    studentService.findAll();

    // then
    verify(studentRepository).findAll();
  }

  @Test
  @DisplayName("학생_추가")
  void save() {
    // given
    Student student = new Student(1L, "Kim", "Kim@mail.com", Gender.MALE);

    // when
    studentService.save(student);

    // then
    ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

    verify(studentRepository).save(studentArgumentCaptor.capture());

    Student capturedStudent = studentArgumentCaptor.getValue();

    assertThat(capturedStudent).isEqualTo(student);
  }

  @Test
  @DisplayName("학생_이메일_존재시")
  void ifEmailExistWhenSave() {
    // given
    Student student = new Student(1L, "Kim", "Kim@email.com", Gender.MALE);
    given(studentRepository.findByEmail(anyString())).willReturn(of(student));

    // then
    assertThatThrownBy(() -> studentService.save(student))
      .isInstanceOf(BadRequestException.class)
      .hasMessageContaining(student.getEmail() + " already exists");


    // save() 기능을 사용하지 않음을 명시
    // Moickto는 save 기능을 사용하지 않는 것을 검증
    verify(studentRepository, never()).save(any());
  }

  @Test
  @DisplayName("학생_삭제_학생_존재시")
  void deleteStudentWhenExist() {
    // given
    Student student = new Student(1L, "Kim", "Kim@mail.com", Gender.MALE);
    Long id = student.getId();

    given(studentRepository.findById(id)).willReturn(ofNullable(student));

    // when
    studentService.delete(id);

    // then
    verify(studentRepository).deleteById(id);
  }

  @Test
  @DisplayName("학생_삭제_학생_미존재시")
  void deleteStudentWhenNotExist() {
    // given
    Student student = new Student(1L, "Kim", "Kim@mail.com", Gender.MALE);
    Long id = student.getId();

    given(studentRepository.findById(id)).willReturn(empty());

    // when

    // then
    assertThatThrownBy(() -> studentService.delete(id))
      .isInstanceOf(StudentNotFoundException.class)
      .hasMessageContaining("Student not found with id : " + id);

    verify(studentRepository, never()).deleteById(id);
  }
}