package org.dreamsellers.model;


import java.util.*;
import jakarta.persistence.*;
import lombok.*;

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

    @OneToMany(mappedBy = "user")
    private List<BookingEntity> bookings = new ArrayList<>();
}
