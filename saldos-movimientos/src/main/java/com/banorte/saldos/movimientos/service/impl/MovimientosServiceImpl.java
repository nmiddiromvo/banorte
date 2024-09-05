package com.banorte.saldos.movimientos.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.banorte.saldos.movimientos.converter.MovimientosConverter;
import com.banorte.saldos.movimientos.exception.ServiceException;
import com.banorte.saldos.movimientos.model.dto.MovimientosDTO;
import com.banorte.saldos.movimientos.model.entity.Movimientos;
import com.banorte.saldos.movimientos.repository.IMovimientosRepository;
import com.banorte.saldos.movimientos.service.IMovimientosService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovimientosServiceImpl implements IMovimientosService {

    private final IMovimientosRepository movimientosRepository;
    private final MovimientosConverter movimientosConverter;

    public MovimientosServiceImpl(IMovimientosRepository movimientosRepository, MovimientosConverter movimientosConverter) {
        this.movimientosRepository = movimientosRepository;
        this.movimientosConverter = movimientosConverter;
    }

	@Override
	public List<MovimientosDTO> getLista() throws ServiceException {
		log.info("Iniciando la consulta para obtener la lista de movimientos");
		try {
			List<Movimientos> movimientos = movimientosRepository.findAll();
			if (movimientos.isEmpty()) {
				log.warn("No se encontraron movimientos en la base de datos");
			} else {
				log.info("Se encontraron {} movimientos", movimientos.size());
			}
			return Optional.ofNullable(movimientos).filter(list -> !list.isEmpty())
					.map(list -> list.stream().map(movimientosConverter::convertEntityToDTO).collect(Collectors.toList()))
					.orElse(Collections.emptyList());
		} catch (Exception e) {
			log.error("Error al obtener la lista de movimientos: {}", e.getMessage(), e);
			throw new ServiceException("Error al obtener la lista de movimientos", e);
		}
	}
	
	@Override
	public Optional<MovimientosDTO> findById(Long id) throws ServiceException {
	    log.info("Buscando movimiento con id: {}", id);
	    try {
	        return movimientosRepository.findById(id)
	                .map(movimientosConverter::convertEntityToDTO);
	    } catch (Exception e) {
	        log.error("Error al buscar el movimiento con id {}: {}", id, e.getMessage(), e);
	        throw new ServiceException("Error al buscar el movimiento con id: " + id, e);
	    }
	}
	
	
	@Override
	public List<MovimientosDTO> findByConcepto(String concepto) throws ServiceException {
	    log.info("Buscando movimientos con el concepto: {}", concepto);
	    try {
	        List<Movimientos> movimientos = movimientosRepository.findByConceptoContainingIgnoreCase(concepto);
	        if (movimientos.isEmpty()) {
	            log.warn("No se encontraron movimientos con el concepto: {}", concepto);
	            return Collections.emptyList();
	        }
	        log.info("Se encontraron {} movimientos con el concepto: {}", movimientos.size(), concepto);
	        return movimientos.stream()
	                .map(movimientosConverter::convertEntityToDTO)
	                .collect(Collectors.toList());
	    } catch (Exception e) {
	        log.error("Error al buscar movimientos con el concepto {}: {}", concepto, e.getMessage(), e);
	        throw new ServiceException("Error al buscar movimientos con el concepto: " + concepto, e);
	    }
	}

	@Override
	public List<MovimientosDTO> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws ServiceException {
	    log.info("Buscando movimientos entre las fechas {} y {}", fechaInicio, fechaFin);
	    try {
	        List<Movimientos> movimientos = movimientosRepository.findByFechaBetween(fechaInicio, fechaFin);
	        if (movimientos.isEmpty()) {
	            log.warn("No se encontraron movimientos entre las fechas {} y {}", fechaInicio, fechaFin);
	            return Collections.emptyList();
	        }
	        log.info("Se encontraron {} movimientos entre las fechas {} y {}", movimientos.size(), fechaInicio, fechaFin);
	        return movimientos.stream()
	                .map(movimientosConverter::convertEntityToDTO)
	                .collect(Collectors.toList());
	    } catch (Exception e) {
	        log.error("Error al buscar movimientos entre las fechas {} y {}: {}", fechaInicio, fechaFin, e.getMessage(), e);
	        throw new ServiceException("Error al buscar movimientos entre las fechas: " + fechaInicio + " y " + fechaFin, e);
	    }
	}

	
	@Override
	public BigDecimal getSaldoDisponible() throws ServiceException {
	    log.info("Calculando el saldo disponible a partir de los movimientos");

	    try {
	        List<Movimientos> movimientos = movimientosRepository.findAll();

	        BigDecimal saldoDisponible = movimientos.stream()
	                .map(movimiento -> {
	                    if ("ENTRADA".equalsIgnoreCase(movimiento.getTipo())) {
	                        return movimiento.getMonto();
	                    } else if ("SALIDA".equalsIgnoreCase(movimiento.getTipo())) {
	                        return movimiento.getMonto().negate();
	                    } else {
	                        return BigDecimal.ZERO;
	                    }
	                })
	                .reduce(BigDecimal.ZERO, BigDecimal::add);

	        log.info("Saldo disponible calculado: {}", saldoDisponible);
	        return saldoDisponible;
	    } catch (Exception e) {
	        log.error("Error al calcular el saldo disponible: {}", e.getMessage(), e);
	        throw new ServiceException("Error al calcular el saldo disponible", e);
	    }
	}

	@Override
	public void eliminarPorId(Long id) throws ServiceException {
	    log.info("Iniciando la eliminacion del movimiento con id: {}", id);
	    try {
	        if (movimientosRepository.existsById(id)) {
	            movimientosRepository.deleteById(id);
	            log.info("Movimiento con id {} eliminado exitosamente", id);
	        } else {
	            log.warn("Movimiento con id {} no encontrado", id);
	            throw new ServiceException("Movimiento no encontrado con el id: " + id);
	        }
	    } catch (Exception e) {
	        log.error("Error al eliminar el movimiento con id {}: {}", id, e.getMessage(), e);
	        throw new ServiceException("Error al eliminar el movimiento con id: " + id, e);
	    }
	}

	@Override
	public List<MovimientosDTO> findByTipo(String tipo) throws ServiceException {
	    log.info("Buscando movimientos con tipo: {}", tipo);
	    try {
	        List<Movimientos> movimientos = movimientosRepository.findByTipo(tipo);
	        if (movimientos.isEmpty()) {
	            log.warn("No se encontraron movimientos con el tipo: {}", tipo);
	            return Collections.emptyList();
	        }
	        log.info("Se encontraron {} movimientos con el tipo: {}", movimientos.size(), tipo);
	        return movimientos.stream()
	                .map(movimientosConverter::convertEntityToDTO)
	                .collect(Collectors.toList());
	    } catch (Exception e) {
	        log.error("Error al buscar movimientos con el tipo {}: {}", tipo, e.getMessage(), e);
	        throw new ServiceException("Error al buscar movimientos con el tipo: " + tipo, e);
	    }
	}
	
	@Override
	public MovimientosDTO actualizarFavorito(Long id, boolean favorito) throws ServiceException {
	    log.info("Actualizando favorito del movimiento con id: {}", id);
	    try {
	        Optional<Movimientos> optionalMovimiento = movimientosRepository.findById(id);
	        if (optionalMovimiento.isPresent()) {
	            Movimientos movimiento = optionalMovimiento.get();
	            movimiento.setFavorito(favorito);
	            movimientosRepository.save(movimiento);
	            log.info("Movimiento con id {} actualizado. Favorito: {}", id, favorito);
	            return movimientosConverter.convertEntityToDTO(movimiento);
	        } else {
	            log.warn("Movimiento con id {} no encontrado", id);
	            throw new ServiceException("Movimiento no encontrado con id: " + id);
	        }
	    } catch (Exception e) {
	        log.error("Error al actualizar el favorito del movimiento con id {}: {}", id, e.getMessage(), e);
	        throw new ServiceException("Error al actualizar el favorito del movimiento con id: " + id, e);
	    }
	}



}

