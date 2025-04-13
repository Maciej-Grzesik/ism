package org.dreamsellers.model;


import java.time.LocalDateTime;
import java.util.*;
import java.util.Locale.Category;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "listing")
class ListingEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "listing_id")
  private long id;

  @ManyToOne
  @JoinColumn(name = "business_id", nullable = false)
  private BusinessEntity business;

  @OneToMany(mappedBy = "listing")
  private List<ReviewEntity> reviews = new ArrayList<>();
  
  @Basic
  @Column(name = "title")
  private String title;

  @Enumerated(EnumType.STRING)
  @Column(name = "category")
  private Category category;

  @Basic
  @Column(name = " price")
  private Double price;

  @Basic
  @Column(name = " availability", nullable = false)
  private List<LocalDateTime> availability = new ArrayList<>();

  @Basic
  @Column(name = "image", nullable = false)
  private String image;

  @Basic
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Basic
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;


} 
