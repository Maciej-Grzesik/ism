package org.dreamsellers.model;


import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;
import org.model.BookingResponseDto;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "booking")
public class BookingEntity {
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
  @Column(name = "status")
  private BookingResponseDto.StatusEnum status;

  @Basic
  @Column(name = "price")
  private double totalPrice;

  @Enumerated(EnumType.STRING)
  @Column(name = "payment_status")
  private BookingResponseDto.PaymentStatusEnum paymentStatus;

  @Basic
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Basic
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}
