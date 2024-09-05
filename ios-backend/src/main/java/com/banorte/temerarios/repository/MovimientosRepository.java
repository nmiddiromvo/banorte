package com.banorte.temerarios.repository;

import com.banorte.temerarios.entity.MovimientosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientosRepository extends JpaRepository<MovimientosEntity, Long> {

    public List<MovimientosEntity> findByType(String type);
}
