package org.dreamsellers.model;


import java.time.LocalDateTime;

import org.dreamsellers.types.PaymentStatus;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "booking")
class BookingEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "booking_id")
  private long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity user;

  @OneToOne
  @JoinColumn(name = "listing_id", referencedColumnName = "listing_id")
  private ListingEntity listing;

  @Basic
  @Column(name = "date")
  private LocalDateTime date;
 
  @Enumerated(EnumType.STRING)
  @Column(name = "payment_status")
  private PaymentStatus paymentStatus;
  }
