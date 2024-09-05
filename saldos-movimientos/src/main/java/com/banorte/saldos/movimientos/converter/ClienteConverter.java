package com.banorte.saldos.movimientos.converter;

import org.springframework.stereotype.Component;

import com.banorte.saldos.movimientos.model.dto.ClienteDTO;
import com.banorte.saldos.movimientos.model.entity.Cliente;

@Component
public class ClienteConverter {

    public ClienteDTO convertEntityToDTO(Cliente entity) {
        return new ClienteDTO(
                entity.getId(),
                entity.getNombre(),
                entity.getApPaterno(),
                entity.getApMaterno(),
                entity.getRfc()
        );
    }

    public Cliente convertDTOToEntity(ClienteDTO dto) {
        return new Cliente(
                dto.id(),
                dto.nombre(),
                dto.apPaterno(),
                dto.apMaterno(),
                dto.rfc()
        );
    }
}

