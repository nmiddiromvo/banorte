package com.banorte.saldos.movimientos.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transferencia")
public class Transferencia extends TransaccionBase implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String tarjeta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BANCO_ID")
	private Banco banco;
	
	@Column(name = "beneficicario", length = 60, nullable = false)
	private String beneficicario;
	
	@Column(name = "alias", length = 60, nullable = true)
	private String alias;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUENTA_ID")
	private Cuenta cuentaOrigen;
	
	@Column(name = "comision", nullable = true)
	private BigDecimal comision;
	
	@Column(name = "iva", nullable = true)
	private BigDecimal iva;
	
	@Column(name = "num_referencia", length = 7, nullable = true)
	private Long numReferencia;
	
	@Column(name = "estatus", length = 14, nullable = false)
	private String estatus;
	
	@Column(name = "folio", length = 14, nullable = false)
	private String folio;
	
	@Column(name = "tipo_operacion", length = 14, nullable = false)
	private String tipoOperacion;
	
	@Column(name = "clave_rastreo", length = 50, nullable = false)
	private String claveRastreo;
	
}
