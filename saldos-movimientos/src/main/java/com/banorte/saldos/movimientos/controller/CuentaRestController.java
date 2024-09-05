package com.banorte.saldos.movimientos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banorte.saldos.movimientos.exception.ServiceException;
import com.banorte.saldos.movimientos.model.dto.CuentaDTO;
import com.banorte.saldos.movimientos.service.ICuentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/cuenta")
@Tag(name = "Cuentas", description = "Operaciones relacionadas con la gestión de cuentas")
public class CuentaRestController {

    private final ICuentaService cuentaService;

    public CuentaRestController(ICuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @Operation(summary = "Obtener todas las cuentas", description = "Devuelve una lista con todas las cuentas registradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de cuentas obtenida con éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CuentaDTO.class))),
        @ApiResponse(responseCode = "204", description = "No se encontraron cuentas"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<CuentaDTO>> getAllCuentas() throws ServiceException {
        log.info("Obteniendo lista de cuentas");
        List<CuentaDTO> cuentas = cuentaService.findAll();
        if (cuentas.isEmpty()) {
            log.warn("No se encontraron cuentas");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(cuentas);
        }
        return ResponseEntity.ok(cuentas);
    }

    @Operation(summary = "Obtener cuenta por ID", description = "Devuelve una cuenta específica según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuenta obtenida con éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CuentaDTO.class))),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> getCuentaById(@PathVariable Long id) throws ServiceException {
        log.info("Obteniendo cuenta con id: {}", id);
        try {
            CuentaDTO cuenta = cuentaService.findById(id);
            return ResponseEntity.ok(cuenta);
        } catch (RuntimeException e) {
            log.error("Error al obtener cuenta: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Crear una nueva cuenta", description = "Crea una nueva cuenta con la información proporcionada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cuenta creada con éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CuentaDTO.class))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<CuentaDTO> createCuenta(@RequestBody CuentaDTO cuentaDTO) throws ServiceException {
        log.info("Creando una nueva cuenta");
        CuentaDTO nuevaCuenta = cuentaService.create(cuentaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCuenta);
    }

    @Operation(summary = "Actualizar una cuenta", description = "Actualiza una cuenta existente por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuenta actualizada con éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CuentaDTO.class))),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CuentaDTO> updateCuenta(@PathVariable Long id, @RequestBody CuentaDTO cuentaDTO) throws ServiceException {
        log.info("Actualizando cuenta con id: {}", id);
        try {
            CuentaDTO cuentaActualizada = cuentaService.update(id, cuentaDTO);
            return ResponseEntity.ok(cuentaActualizada);
        } catch (RuntimeException e) {
            log.error("Error al actualizar cuenta: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    
    @Operation(summary = "Eliminar una cuenta", description = "Elimina una cuenta existente por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Cuenta eliminada con éxito"),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) throws ServiceException {
        log.info("Eliminando cuenta con id: {}", id);
        cuentaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
