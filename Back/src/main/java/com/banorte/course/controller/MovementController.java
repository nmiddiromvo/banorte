package com.banorte.course.controller;

import com.banorte.course.MovementRepository;
import com.banorte.course.exceptions.MovementNotFoundException;
import com.banorte.course.ms.system.adapter.application.domain.model.entity.Movement;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@RestController
public class MovementController {

    private final MovementRepository movementRepository;

    public MovementController(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    @GetMapping("/{filter}")
    public Optional<List<Movement>> getMovement(@PathVariable String filter){
        if (isDate(filter)) {
            return movementRepository.findByMovementDate(LocalDateTime.parse(filter));
        }
        else {
            return movementRepository.findByConcept(filter);
        }
    }

    public static boolean isDate(String date){
        try{
            LocalDateTime.parse(date);
            return true;
        }
        catch (DateTimeParseException e) {
            return false;
        }
    }


    @GetMapping("/movements")
    List<Movement> all() {
        return movementRepository.findAll();
    }

    @PostMapping("/movements")
    Movement newEmployee(@RequestBody Movement newMovement) {
        return movementRepository.save(newMovement);
    }

    @GetMapping("/movements/{id}")
    Movement one(@PathVariable Long id) {

        return movementRepository.findById(id)
                .orElseThrow(() -> new MovementNotFoundException(id));
    }

    @PutMapping("/movements/{id}")
    Movement replaceMovement(@RequestBody Movement newMovement, @PathVariable Long id) {

        return movementRepository.findById(id)
                .map(movement -> {
                    movement.setMovementDate(newMovement.getMovementDate());
                    movement.setType(newMovement.getType());
                    movement.setAmount(newMovement.getAmount());
                    movement.setAccountId(newMovement.getAccountId());
                    return movementRepository.save(movement);
                })
                .orElseGet(() -> {
                    return movementRepository.save(newMovement);
                });
    }


}
