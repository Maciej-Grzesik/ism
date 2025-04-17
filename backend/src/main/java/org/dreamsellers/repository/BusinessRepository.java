package org.dreamsellers.repository;

import org.dreamsellers.model.BusinessEntity;
import org.dreamsellers.model.ListingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessRepository extends JpaRepository<BusinessEntity, Long> {

}
