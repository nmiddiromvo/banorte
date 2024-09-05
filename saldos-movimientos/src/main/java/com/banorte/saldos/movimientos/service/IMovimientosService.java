package com.banorte.saldos.movimientos.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banorte.saldos.movimientos.exception.ServiceException;
import com.banorte.saldos.movimientos.model.dto.MovimientosDTO;

@Service
public interface IMovimientosService {
	
	List<MovimientosDTO> getLista() throws ServiceException;

	Optional<MovimientosDTO> findById(Long id) throws ServiceException;
	
	List<MovimientosDTO> findByConcepto(String concepto) throws ServiceException;
	
	List<MovimientosDTO> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws ServiceException;

	BigDecimal getSaldoDisponible() throws ServiceException;
	
	 void eliminarPorId(Long id) throws ServiceException;

	List<MovimientosDTO> findByTipo(String tipo) throws ServiceException;

	MovimientosDTO actualizarFavorito(Long id, boolean favorito) throws ServiceException;
}
