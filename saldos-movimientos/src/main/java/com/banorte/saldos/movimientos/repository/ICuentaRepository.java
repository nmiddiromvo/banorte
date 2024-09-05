package com.banorte.saldos.movimientos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banorte.saldos.movimientos.model.entity.Cuenta;

@Repository
public interface ICuentaRepository extends JpaRepository<Cuenta, Long> {
	
}
