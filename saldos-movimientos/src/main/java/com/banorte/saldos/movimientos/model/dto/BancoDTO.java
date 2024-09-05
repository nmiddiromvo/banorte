package com.banorte.saldos.movimientos.model.dto;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record BancoDTO(
    Long id, 
    String nombre
) {

}
