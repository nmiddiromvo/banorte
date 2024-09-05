package com.banorte.temerarios.service;

import com.banorte.temerarios.dto.MovimientoDTO;
import com.banorte.temerarios.entity.MovimientosEntity;
import com.banorte.temerarios.repository.MovimientosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientosService {

    @Autowired
    MovimientosRepository movimientosRepository;

    public List<MovimientosEntity> obtenerTodosLosMovimientos(){
        return movimientosRepository.findAll();
    }

    public List<MovimientosEntity> filtrarPorTipo(String tipo){
        return movimientosRepository.findByType(tipo);
    }

    public MovimientosEntity marcarComoFavorito(int id){
        Optional<MovimientosEntity> o = movimientosRepository.findById( (long) id );
        MovimientosEntity m = o.get();
        m.setFavorite(!m.isFavorite());
        movimientosRepository.save( m );
        return m;
    }

    public void eliminaMovimiento(int id){
        movimientosRepository.deleteById( (long)id );
    }
}
