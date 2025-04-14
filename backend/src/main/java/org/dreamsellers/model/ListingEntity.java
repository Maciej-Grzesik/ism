package org.dreamsellers.model;


import java.time.LocalDate;
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
public class ListingEntity {
  
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

  @Basic
  @Column(name = "description")
  private String description;

  @Basic
  @Column(name = "category")
  private String category;

  @Basic
  @Column(name="location")
  private String location;

  @Basic
  @Column(name = "price")
  private float price;

  @ElementCollection
  @CollectionTable(name = "listing_availability", joinColumns = @JoinColumn(name = "listing_id"))
  @Column(name = "available_date")
  private List<LocalDate> availability = new ArrayList<>();

  @ElementCollection
  @CollectionTable(name = "images", joinColumns = @JoinColumn(name = "listing_id"))
  @Column(name = "image", nullable = false)
  private List<String> images = new ArrayList<>();

  @Basic
  @Column(name="rating")
  private Float rating;

  @Basic
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Basic
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;





} 
