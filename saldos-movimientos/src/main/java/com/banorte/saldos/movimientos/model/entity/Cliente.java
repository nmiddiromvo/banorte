package com.banorte.saldos.movimientos.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;
	
	@Column(name = "ap_paterno", length = 30, nullable = false)
	private String apPaterno;
	
	@Column(name = "ap_materno", length = 30, nullable = false)
	private String apMaterno;
	
	@Column(name = "rfc", length = 13, nullable = false)
	private String rfc;

}
