package com.banorte.saldos.movimientos.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banorte.saldos.movimientos.exception.ServiceException;
import com.banorte.saldos.movimientos.model.dto.MovimientosDTO;
import com.banorte.saldos.movimientos.service.IMovimientosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/movimientos")
@Tag(name = "Movimientos", description = "Operaciones relacionadas con movimientos")
public class MovimientosRestController {

	private final IMovimientosService movimientosService;

	public MovimientosRestController(IMovimientosService movimientosService) {
		this.movimientosService = movimientosService;
	}

	@Operation(summary = "Obtiene la lista de movimientos")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista de movimientos obtenida con exito"),
		@ApiResponse(responseCode = "204", description = "No se encontraron movimientos"),
		@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@GetMapping("/lista")
	public ResponseEntity<?> getListaBancos() {
		log.info("Inicio de peticion GET para obtener lista de movimientos");
		try {
			Optional<List<MovimientosDTO>> optionalMovimientos = Optional.ofNullable(movimientosService.getLista());
			if (optionalMovimientos.isPresent() && !optionalMovimientos.get().isEmpty()) {
				log.info("Se encontraron {} movimientos", optionalMovimientos.get().size());
				return ResponseEntity.ok(optionalMovimientos.get());
			} else {
				log.warn("No se encontraron movimientos disponibles");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron movimientos disponibles.");
			}
		} catch (ServiceException e) {
			log.error("Error al obtener la lista de bancos: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al obtener la lista de movimientos. El servicio no esta disponible en este momento.");
		}
	}
	
	@Operation(summary = "Obtener movimiento por ID")
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
	    log.info("Inicio de petición GET para obtener movimiento con id: {}", id);
	    
	    try {
	        Optional<MovimientosDTO> optionalMovimiento = movimientosService.findById(id);
	        if (optionalMovimiento.isPresent()) {
	            log.info("Movimiento encontrado con id: {}", id);
	            return ResponseEntity.ok(optionalMovimiento.get());
	        } else {
	            log.warn("Movimiento no encontrado con id: {}", id);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movimiento no encontrado con id: " + id);
	        }
	        
	    } catch (ServiceException e) {
	        log.error("Error al obtener movimiento con id {}: {}", id, e.getMessage(), e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener movimiento: " + e.getMessage());
	    }
	}


	@Operation(summary = "Obtener movimientos por concepto")
	@GetMapping("/concepto/{concepto}")
	public ResponseEntity<?> findByConcepto(@PathVariable String concepto) {
	    log.info("Inicio de petición GET para obtener movimientos con concepto: {}", concepto);
	    try {
	        List<MovimientosDTO> movimientos = movimientosService.findByConcepto(concepto);
	        return movimientos.isEmpty() ?
	                ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron movimientos con el concepto proporcionado") :
	                ResponseEntity.ok(movimientos);
	    } catch (ServiceException e) {
	        log.error("Error al obtener movimientos con concepto {}: {}", concepto, e.getMessage(), e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	    }
	}

	@Operation(summary = "Obtener movimientos por rango de fechas")
	@GetMapping("/fechas")
	public ResponseEntity<?> findByFechaBetween(
	        @RequestParam("fechaInicio") String fechaInicioStr,
	        @RequestParam("fechaFin") String fechaFinStr) {
	    
	    log.info("Inicio de petición GET para obtener movimientos entre las fechas {} y {}", fechaInicioStr, fechaFinStr);
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	    try {
	        LocalDateTime fechaInicio = LocalDate.parse(fechaInicioStr, formatter).atStartOfDay();
	        LocalDateTime fechaFin = LocalDate.parse(fechaFinStr, formatter).atTime(23, 59, 59);
	        
	        List<MovimientosDTO> movimientos = movimientosService.findByFechaBetween(fechaInicio, fechaFin);
	        
	        return movimientos.isEmpty() ?
	                ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron movimientos en el rango de fechas proporcionado") :
	                ResponseEntity.ok(movimientos);
	        
	    } catch (DateTimeParseException e) {
	        log.error("Error al convertir las fechas {} y {}: {}", fechaInicioStr, fechaFinStr, e.getMessage(), e);
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato de fecha inválido. Usa el formato dd-MM-yyyy.");
	    } catch (ServiceException e) {
	        log.error("Error al obtener movimientos entre las fechas {} y {}: {}", fechaInicioStr, fechaFinStr, e.getMessage(), e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	    }
	}
	
	
	@Operation(summary = "Obtener el saldo disponible")
	@GetMapping("/saldo")
	public ResponseEntity<?> getSaldoDisponible() {
	    log.info("Inicio de peticion GET para obtener el saldo disponible");
	    try {
	        BigDecimal saldoDisponible = movimientosService.getSaldoDisponible();
	        return ResponseEntity.ok(saldoDisponible);
	    } catch (ServiceException e) {
	        log.error("Error al obtener el saldo disponible: {}", e.getMessage(), e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el saldo disponible.");
	    }
	}
	
	@Operation(summary = "Eliminar movimiento por ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarPorId(@PathVariable Long id) {
	    log.info("Iniciando peticion DELETE para eliminar movimiento con id: {}", id);
	    
	    try {
	        movimientosService.eliminarPorId(id);
	        return ResponseEntity.ok("Movimiento eliminado con exito.");
	    } catch (ServiceException e) {
	        log.error("Error al eliminar el movimiento con id {}: {}", id, e.getMessage(), e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar movimiento: " + e.getMessage());
	    }
	}

	@Operation(summary = "Obtener movimientos por tipo")
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<?> findByTipo(@PathVariable String tipo) {
	    log.info("Inicio de petición GET para obtener movimientos con tipo: {}", tipo);
	    try {
	        List<MovimientosDTO> movimientos = movimientosService.findByTipo(tipo);
	        return movimientos.isEmpty() ?
	                ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron movimientos con el tipo proporcionado") :
	                ResponseEntity.ok(movimientos);
	    } catch (ServiceException e) {
	        log.error("Error al obtener movimientos con tipo {}: {}", tipo, e.getMessage(), e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	    }
	}

	
	@Operation(summary = "Actualizar favorito de un movimiento")
	@PatchMapping("/{id}/favorito")
	public ResponseEntity<?> actualizarFavorito(@PathVariable Long id, @RequestParam boolean favorito) {
	    log.info("Inicio de petición PATCH para actualizar el favorito del movimiento con id: {}", id);
	    try {
	        MovimientosDTO movimientoActualizado = movimientosService.actualizarFavorito(id, favorito);
	        return ResponseEntity.ok(movimientoActualizado);
	    } catch (ServiceException e) {
	        log.error("Error al actualizar favorito del movimiento con id {}: {}", id, e.getMessage(), e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	    }
	}


}