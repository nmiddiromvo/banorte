package com.banorte.saldos.movimientos.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransaccionBaseDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private BigDecimal monto;
	private LocalDateTime fecha;
	private String tipo;
	private String concepto;
}
