package com.banorte.saldos.movimientos.model.dto;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record ClienteDTO(
    Long id, 
    String nombre,
    String apPaterno,
    String apMaterno,
    String rfc
) {

}
