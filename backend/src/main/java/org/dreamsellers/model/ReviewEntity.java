package org.dreamsellers.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "review")
public class ReviewEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "review_id")
  private long id;

  @Basic
  @Column(name = "rating")
  private double rating;

  @Basic
  @Column(name = "message")
  private String message;

  @ManyToOne
  @JoinColumn(name = "listing_id", nullable = false)
  private ListingEntity listing;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity user;
}

