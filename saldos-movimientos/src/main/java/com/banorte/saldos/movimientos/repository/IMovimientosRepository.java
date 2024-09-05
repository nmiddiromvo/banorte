package com.banorte.saldos.movimientos.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banorte.saldos.movimientos.model.entity.Movimientos;

@Repository
public interface IMovimientosRepository extends JpaRepository<Movimientos, Long> {

	Optional<Movimientos> findById(Long id);

	List<Movimientos> findByConceptoContainingIgnoreCase(String concepto);

	List<Movimientos> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
	List<Movimientos> findByTipo(String tipo);

}