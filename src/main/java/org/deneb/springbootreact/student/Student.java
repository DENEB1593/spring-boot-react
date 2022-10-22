package org.deneb.springbootreact.student;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table
public class Student {

  @Id
  @SequenceGenerator(
    name = "student_sequence",
    sequenceName = "student_sequence",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator ="student_sequence"
  )
  private Long id;

  @NotBlank(message = "name is not valid")
  @Column(nullable = false)
  private String name;

  @Email(message = "email is not valid")
  @Column(nullable = false, unique = true)
  private String email;

  @NotNull(message = "gender is not valid")
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Gender gender;
}
