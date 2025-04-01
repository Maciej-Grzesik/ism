package org.dreamsellers.model;


import java.util.*;
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
  
}
