package org.deneb.springbootreact.student;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository underTest;


  @Test
  void itShouldCheckWhenStudentEmailExists() {
    // given
    String email = "jamila@gmail.com";
    Student student = new Student(
      1L,
      "Jamila",
      email,
      Gender.FEMALE
    );
    underTest.save(student);

    // when
    Student result = underTest.findByEmail(email).get();

    // then
    assertThat(result).isNotNull();
  }

}