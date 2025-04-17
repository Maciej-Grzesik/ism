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
    @Column(name = "first_name", nullable = false, unique = true)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToOne(mappedBy = "user")
    private AuthEntity auth;

    @OneToMany(mappedBy = "user")
    private List<BookingEntity> bookings = new ArrayList<>();

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "user")
    private List<ReviewEntity> reviews = new ArrayList<>();
}
