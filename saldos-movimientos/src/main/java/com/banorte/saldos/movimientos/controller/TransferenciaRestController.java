package com.banorte.saldos.movimientos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banorte.saldos.movimientos.exception.ServiceException;
import com.banorte.saldos.movimientos.model.dto.BancoDTO;
import com.banorte.saldos.movimientos.model.dto.TransferenciaDTO;
import com.banorte.saldos.movimientos.service.IBancoService;
import com.banorte.saldos.movimientos.service.ITransferenciaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/transferencias")
@Tag(name = "Transferencias", description = "Operaciones relacionadas con transferencias")
public class TransferenciaRestController {

	private final IBancoService bancoService;
	private final ITransferenciaService transferenciaService;

	public TransferenciaRestController(IBancoService bancoService, ITransferenciaService transferenciaService) {
		this.bancoService = bancoService;
		this.transferenciaService = transferenciaService;
	}

	@Operation(summary = "Obtener lista de bancos")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista de bancos obtenida con exito"),
		@ApiResponse(responseCode = "204", description = "No se encontraron bancos"),
		@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@GetMapping("/bancos")
	public ResponseEntity<?> getListaBancos() {
		log.info("Inicio de petición GET para obtener lista de bancos");
		try {
			Optional<List<BancoDTO>> optionalBancos = Optional.ofNullable(bancoService.getLista());
			if (optionalBancos.isPresent() && !optionalBancos.get().isEmpty()) {
				log.info("Se encontraron {} bancos", optionalBancos.get().size());
				return ResponseEntity.ok(optionalBancos.get());
			} else {
				log.warn("No se encontraron bancos disponibles");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron bancos disponibles.");
			}
		} catch (ServiceException e) {
			log.error("Error al obtener la lista de bancos: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al obtener la lista de bancos. El servicio no está disponible en este momento.");
		}
	}

	@Operation(summary = "Obtener todas las transferencias")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista de transferencias obtenida con exito"),
		@ApiResponse(responseCode = "204", description = "No se encontraron transferencias"),
		@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@GetMapping
	public ResponseEntity<List<TransferenciaDTO>> getAllTransferencias() throws ServiceException {
		log.info("Obteniendo lista de transferencias");
		List<TransferenciaDTO> transferencias = transferenciaService.findAll();
		if (transferencias.isEmpty()) {
			log.warn("No se encontraron transferencias");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(transferencias);
		}
		return ResponseEntity.ok(transferencias);
	}

	@Operation(summary = "Obtener transferencia por ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Transferencia obtenida con éxito"),
		@ApiResponse(responseCode = "404", description = "Transferencia no encontrada"),
		@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@GetMapping("/{id}")
	public ResponseEntity<TransferenciaDTO> getTransferenciaById(@PathVariable Long id) throws ServiceException {
		log.info("Obteniendo transferencia con id: {}", id);
		try {
			TransferenciaDTO transferencia = transferenciaService.findById(id);
			return ResponseEntity.ok(transferencia);
		} catch (RuntimeException e) {
			log.error("Error al obtener transferencia: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@Operation(summary = "Crear una nueva transferencia")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Transferencia creada con exito"),
		@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@PostMapping
	public ResponseEntity<TransferenciaDTO> createTransferencia(@RequestBody TransferenciaDTO transferenciaDTO) throws ServiceException {
		log.info("Creando una nueva transferencia");
		TransferenciaDTO nuevaTransferencia = transferenciaService.create(transferenciaDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTransferencia);
	}

	@Operation(summary = "Eliminar una transferencia por ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Transferencia eliminada con exito"),
		@ApiResponse(responseCode = "404", description = "Transferencia no encontrada"),
		@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTransferencia(@PathVariable Long id) throws ServiceException {
		log.info("Eliminando transferencia con id: {}", id);
		transferenciaService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}