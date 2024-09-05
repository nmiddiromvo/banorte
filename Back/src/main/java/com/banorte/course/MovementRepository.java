package com.banorte.course;

import com.banorte.course.ms.system.adapter.application.domain.model.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovementRepository extends JpaRepository<Movement, Long> {

    Optional<List<Movement>> findByMovementDate(LocalDateTime movementDate);
    Optional<List<Movement>> findByConcept(String concept);

}
