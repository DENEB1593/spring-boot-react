package org.deneb.springbootreact.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.deneb.springbootreact.student.Gender;
import org.deneb.springbootreact.student.Student;
import org.deneb.springbootreact.student.StudentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application-it.properties"
)
@AutoConfigureMockMvc
public class StudentIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private StudentRepository studentRepository;

  private final Faker faker = new Faker();

  @Test
  @DisplayName("학생_추가")
  void canSaveNewStudent() throws Exception {
    // given
    Student student = new Student(
      1L,
      faker.name().firstName().trim(),
      faker.name().firstName().trim() + "@mail.com",
      Gender.MALE);

    // when
    ResultActions resultActions = mockMvc.perform(
      post("/api/v1/students")
        .contentType(APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(student))
    );
    // then
    resultActions.andExpect(status().isOk());
    List<Student> students = studentRepository.findAll();
    assertThat(students)
      .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
      .contains(student);

  }


  @Test
  @DisplayName("학생_삭제")
  void canDeleteStudent() throws Exception {
    // given
    String name = String.format(
      "%s %s",
      faker.name().firstName(),
      faker.name().lastName()
    );

    String email = String.format("%s@amigoscode.edu",
      StringUtils.trimAllWhitespace(name.trim().toLowerCase()));

    Student student = new Student(
      1L,
      name,
      email,
      Gender.FEMALE
    );

    mockMvc.perform(post("/api/v1/students")
        .contentType(APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(student)))
      .andExpect(status().isOk());

    MvcResult getStudentsResult = mockMvc.perform(get("/api/v1/students")
        .contentType(APPLICATION_JSON))
      .andExpect(status().isOk())
      .andReturn();

    String contentAsString = getStudentsResult
      .getResponse()
      .getContentAsString();

    List<Student> students = objectMapper.readValue(
      contentAsString,
      new TypeReference<>() { }
    );

    long id = students.stream()
      .filter(s -> s.getEmail().equals(student.getEmail()))
      .map(Student::getId)
      .findFirst()
      .orElseThrow(() ->
        new IllegalStateException(
          "student with email: " + email + " not found"));

    // when
    ResultActions resultActions = mockMvc
      .perform(delete("/api/v1/students/" + id));

    // then
    resultActions.andExpect(status().isOk());
    boolean exists = studentRepository.existsById(id);
    assertThat(exists).isFalse();
  }
}
