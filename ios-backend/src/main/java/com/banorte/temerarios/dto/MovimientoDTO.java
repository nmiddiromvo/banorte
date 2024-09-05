package com.banorte.temerarios.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class MovimientoDTO {

    private Long id;
    private BigDecimal amount;
    private Date date;
    private String type; //Entrada/Salida
    private boolean favorite;

    private String description;
    private String category;//comida //

    //private Date updated_at;
    private String user_id;

    private String desde;
    private String no_cuenta;
    private String banco_destino;
    private Date created_at = new Date();
    private String concepto;
    private BigDecimal comision;
    private BigDecimal iva;
    private String folio;
    private String referencia;
    private String clave_rastreo;
}
