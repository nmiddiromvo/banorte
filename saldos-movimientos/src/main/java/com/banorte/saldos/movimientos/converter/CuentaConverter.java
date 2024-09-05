package com.banorte.saldos.movimientos.converter;

import org.springframework.stereotype.Component;

import com.banorte.saldos.movimientos.model.dto.CuentaDTO;
import com.banorte.saldos.movimientos.model.entity.Cuenta;

@Component
public class CuentaConverter {
	
    private final ClienteConverter clienteConverter;
    
    public CuentaConverter(ClienteConverter clienteConverter) {
        this.clienteConverter = clienteConverter;
    }

    public CuentaDTO convertEntityToDTO(Cuenta entity) {
        return new CuentaDTO(
                entity.getId(),
                entity.getCuenta(),
                entity.getTipo(),
                clienteConverter.convertEntityToDTO(entity.getCliente())
        );
    }

    public Cuenta convertDTOToEntity(CuentaDTO dto) {
        return new Cuenta(
                dto.id(),
                dto.cuenta(),
                dto.tipo(),
                clienteConverter.convertDTOToEntity(dto.cliente())
        );
    }
}


