package org.dreamsellers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
  private long id;
  private String name;
  private String surname;
  private String phone;
  private String email;
}
