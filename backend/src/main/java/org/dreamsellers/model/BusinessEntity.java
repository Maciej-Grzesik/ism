package org.dreamsellers.model;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.*;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "business")
public class BusinessEntity {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "business_id")
  private long id;
  
  @Basic
  @Column(name = "business_name", nullable = false)
  private String name;

  @Basic
  @Column(name = "description", nullable = false)
  private String description;

  @OneToOne(mappedBy = "business")
  private AuthEntity auth;


  @Basic
  @Column(name = "address", nullable = false)
  private String address;

  @Basic
  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;
  
  @Basic
  @Column(name = "updated_at", nullable = false)
  private OffsetDateTime updatedAt;

  @OneToMany(mappedBy = "business")
  private List<ListingEntity> listings = new ArrayList<>();
}
