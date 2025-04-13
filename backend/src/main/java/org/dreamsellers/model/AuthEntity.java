package org.dreamsellers.model;


import jakarta.persistence.*;
import lombok.*;
import org.model.RegisterDto;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "auth")
public class AuthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    private long id;

    @Basic
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Basic
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RegisterDto.RoleEnum role;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;
}
