package com.banorte.saldos.movimientos.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimientosDTO extends TransaccionBaseDTO {
	
	private static final long serialVersionUID = 1L;
	private Long id;
    private boolean favorito;

}

