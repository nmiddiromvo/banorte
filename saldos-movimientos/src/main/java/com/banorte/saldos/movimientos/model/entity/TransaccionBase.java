package com.banorte.saldos.movimientos.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class TransaccionBase{
	
	@Column(name = "monto", nullable = false)
    private BigDecimal monto;
    
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha; 
    
    @Column(name = "tipo", length = 10, nullable = true)
    private String tipo;
    
	@Column(name = "concepto", length = 60, nullable = true)
	private String concepto;
}
