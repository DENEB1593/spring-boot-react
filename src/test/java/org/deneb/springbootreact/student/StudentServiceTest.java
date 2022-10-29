package org.deneb.springbootreact.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository studentRepository;
  private StudentService studentService;

  @BeforeEach
  void setUp() {
    studentService = new StudentService(studentRepository);
  }



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

    Student capturedStudent = studentArgumentCaptor.capture();

    assertThat(capturedStudent).isEqualTo(student);
  }

  @Test
  @Disabled
  void delete() {
  }
}