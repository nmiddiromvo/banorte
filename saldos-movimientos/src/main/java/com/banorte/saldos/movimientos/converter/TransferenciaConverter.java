package com.banorte.saldos.movimientos.converter;

import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.banorte.saldos.movimientos.model.dto.TransferenciaDTO;
import com.banorte.saldos.movimientos.model.entity.Transferencia;

@Component
public class TransferenciaConverter {

    private final BancoConverter bancoConverter;
    private final CuentaConverter cuentaConverter;

    public TransferenciaConverter(BancoConverter bancoConverter, CuentaConverter cuentaConverter) {
        this.bancoConverter = bancoConverter;
        this.cuentaConverter = cuentaConverter;
    }

    public TransferenciaDTO convertEntityToDTO(Transferencia entity) {
        TransferenciaDTO dto = new TransferenciaDTO();
        dto.setId(entity.getId());
        dto.setTarjeta(entity.getTarjeta());
        dto.setBanco(bancoConverter.convertEntityToDTO(entity.getBanco()));
        dto.setBeneficicario(entity.getBeneficicario());
        dto.setAlias(entity.getAlias());
        dto.setCuentaOrigen(cuentaConverter.convertEntityToDTO(entity.getCuentaOrigen()));
        dto.setMonto(entity.getMonto());
        dto.setConcepto(entity.getConcepto());
        dto.setComision(entity.getComision());
        dto.setIva(entity.getIva());
        dto.setNumReferencia(entity.getNumReferencia());
        dto.setEstatus(entity.getEstatus());
        dto.setFolio(entity.getFolio());
        dto.setTipoOperacion(entity.getTipoOperacion());
        dto.setClaveRastreo(entity.getClaveRastreo());
        dto.setFecha(entity.getFecha());
        dto.setTipo(entity.getTipo());
        return dto;
    }

    public Transferencia convertDTOToEntity(TransferenciaDTO dto) {
    	String folio = generateFolio();
    	String claveRastreo = generarClaveRastreo();
        Transferencia entity = new Transferencia();
        entity.setId(dto.getId());
        entity.setTarjeta(dto.getTarjeta());
        entity.setBanco(bancoConverter.convertDTOToEntity(dto.getBanco()));
        entity.setBeneficicario(dto.getBeneficicario());
        entity.setAlias(dto.getAlias());
        entity.setCuentaOrigen(cuentaConverter.convertDTOToEntity(dto.getCuentaOrigen()));
        entity.setMonto(dto.getMonto());
        entity.setConcepto(dto.getConcepto());
        entity.setComision(dto.getComision());
        entity.setIva(dto.getIva());
        entity.setNumReferencia(dto.getNumReferencia());
        entity.setEstatus(dto.getEstatus());
        entity.setFolio(folio);
        entity.setTipoOperacion(dto.getTipoOperacion());
        entity.setClaveRastreo(claveRastreo);
        entity.setFecha(dto.getFecha());
        entity.setTipo(dto.getTipo());
        return entity;
    }
    
    private String generateFolio() {
        return String.valueOf(100000000 + new Random().nextInt(900000000));
    }
    
	private String generarClaveRastreo() {
		String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		return random.ints(8, 0, caracteres.length())
				.mapToObj(index -> String.valueOf(caracteres.charAt(index)))
				.collect(Collectors.joining());
	}

}




