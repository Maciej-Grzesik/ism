package org.dreamsellers.repository;

import org.dreamsellers.model.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingsRepository extends JpaRepository<BookingEntity, Long> {
    List<BookingEntity> getAllByUserId(long userId);
}
