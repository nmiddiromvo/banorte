package com.banorte.saldos.movimientos.model.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaDTO extends TransaccionBaseDTO {

	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private Long id;
	private String tarjeta;
	private BancoDTO banco;
	private String beneficicario;
	private String Alias;
	private CuentaDTO cuentaOrigen;
	private BigDecimal comision;
	private BigDecimal iva;
	private Long numReferencia;
	private String estatus;
	@JsonIgnore
	private String folio;
	private String tipoOperacion;
	@JsonIgnore
	private String claveRastreo;
}
