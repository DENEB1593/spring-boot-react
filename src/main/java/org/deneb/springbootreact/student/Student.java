package org.deneb.springbootreact.student;

import lombok.*;

import javax.persistence.*;

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

  private String name;

  private String email;

  @Enumerated(EnumType.STRING)
  private Gender gender;
}
