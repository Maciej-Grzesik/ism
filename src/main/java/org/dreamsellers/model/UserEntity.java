package org.dreamsellers.model;


import jakarta.persistence.*;
import lombok.*;
import org.dreamsellers.types.Role;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Basic
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Basic
    @Column(name = "surname", nullable = false)
    private String surname;

    @OneToOne(mappedBy = "user")
    private AuthEntity auth;
}
