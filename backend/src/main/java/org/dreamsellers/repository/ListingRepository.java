package org.dreamsellers.repository;

import org.dreamsellers.model.ListingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListingRepository extends JpaRepository<ListingEntity, Long> {
    Optional<ListingEntity> getListingById(Long listingId);
}
