package org.deneb.springbootreact.student;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Student {
  private Long id;
  private String name;
  private String email;
  private Gender gender;
}
