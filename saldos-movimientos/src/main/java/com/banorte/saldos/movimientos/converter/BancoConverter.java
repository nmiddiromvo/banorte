package com.banorte.saldos.movimientos.converter;

import org.springframework.stereotype.Component;

import com.banorte.saldos.movimientos.model.dto.BancoDTO;
import com.banorte.saldos.movimientos.model.entity.Banco;

@Component
public class BancoConverter {

    public BancoDTO convertEntityToDTO(Banco entity) {
        return new BancoDTO(
                entity.getId(),
                entity.getNombre()
        );
    }

    public Banco convertDTOToEntity(BancoDTO dto) {
        return new Banco(
                dto.id(),
                dto.nombre()
        );
    }
}

