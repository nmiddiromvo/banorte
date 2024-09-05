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
import com.banorte.saldos.movimientos.model.dto.ClienteDTO;
import com.banorte.saldos.movimientos.service.IClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/cliente")
@Tag(name = "Clientes", description = "Operaciones relacionadas con la gestión de clientes")
public class ClienteRestController {

    private final IClienteService clienteService;

    public ClienteRestController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Obtener todos los clientes", description = "Devuelve una lista con todos los clientes registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida con éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))),
        @ApiResponse(responseCode = "204", description = "No se encontraron clientes"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() throws ServiceException {
        log.info("Obteniendo lista de clientes");
        List<ClienteDTO> clientes = clienteService.findAll();
        if (clientes.isEmpty()) {
            log.warn("No se encontraron clientes");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(clientes);
        }
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Obtener cliente por ID", description = "Devuelve un cliente específico según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente obtenido con éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) throws ServiceException {
        log.info("Obteniendo cliente con id: {}", id);
        try {
            ClienteDTO cliente = clienteService.findById(id);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            log.error("Error al obtener cliente: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Crear un nuevo cliente", description = "Crea un nuevo cliente con la información proporcionada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente creado con éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) throws ServiceException {
        log.info("Creando un nuevo cliente");
        ClienteDTO nuevoCliente = clienteService.create(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    @Operation(summary = "Actualizar un cliente", description = "Actualiza un cliente existente por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente actualizado con éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) throws ServiceException {
        log.info("Actualizando cliente con id: {}", id);
        try {
            ClienteDTO clienteActualizado = clienteService.update(id, clienteDTO);
            return ResponseEntity.ok(clienteActualizado);
        } catch (RuntimeException e) {
            log.error("Error al actualizar cliente: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente existente por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Cliente eliminado con éxito"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) throws ServiceException {
        log.info("Eliminando cliente con id: {}", id);
        clienteService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}