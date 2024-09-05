package com.banorte.saldos.movimientos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banorte.saldos.movimientos.model.entity.Banco;

@Repository
public interface IBancoRepository extends JpaRepository<Banco, Long> {
	
}
