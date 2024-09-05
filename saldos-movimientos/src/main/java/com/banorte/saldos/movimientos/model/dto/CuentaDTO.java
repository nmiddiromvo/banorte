package com.banorte.saldos.movimientos.model.dto;

public record CuentaDTO(
	Long id,
	Long cuenta,
	String tipo,
	ClienteDTO cliente
	) {

}
