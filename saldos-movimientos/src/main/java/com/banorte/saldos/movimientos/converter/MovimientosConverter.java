package com.banorte.saldos.movimientos.converter;

import org.springframework.stereotype.Component;

import com.banorte.saldos.movimientos.model.dto.MovimientosDTO;
import com.banorte.saldos.movimientos.model.entity.Movimientos;

@Component
public class MovimientosConverter {

    public MovimientosDTO convertEntityToDTO(Movimientos entity) {
        MovimientosDTO dto = new MovimientosDTO();
        dto.setId(entity.getId());
        dto.setMonto(entity.getMonto());
        dto.setFecha(entity.getFecha());
        dto.setTipo(entity.getTipo());
        dto.setFavorito(entity.isFavorito());
        dto.setConcepto(entity.getConcepto());
        return dto;
    }

    public Movimientos convertDTOToEntity(MovimientosDTO dto) {
        Movimientos entity = new Movimientos();
        entity.setId(dto.getId());
        entity.setMonto(dto.getMonto());
        entity.setFecha(dto.getFecha());
        entity.setTipo(dto.getTipo());
        entity.setFavorito(dto.isFavorito());
        entity.setConcepto(dto.getConcepto());
        return entity;
    }
}




