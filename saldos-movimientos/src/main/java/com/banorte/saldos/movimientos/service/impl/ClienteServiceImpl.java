package com.banorte.saldos.movimientos.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.banorte.saldos.movimientos.converter.ClienteConverter;
import com.banorte.saldos.movimientos.exception.ServiceException;
import com.banorte.saldos.movimientos.model.dto.ClienteDTO;
import com.banorte.saldos.movimientos.model.entity.Cliente;
import com.banorte.saldos.movimientos.repository.ClienteRepository;
import com.banorte.saldos.movimientos.service.IClienteService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteServiceImpl implements IClienteService {

	private final ClienteRepository clienteRepository;
	private final ClienteConverter clienteConverter;

	public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteConverter clienteConverter) {
		this.clienteRepository = clienteRepository;
		this.clienteConverter = clienteConverter;
	}

	@Override
	public List<ClienteDTO> findAll() throws ServiceException {
		log.info("Iniciando la consulta para obtener la lista de clientes");

		try {
			List<Cliente> clientes = clienteRepository.findAll();
			Optional<List<Cliente>> optionalClientes = Optional.ofNullable(clientes);

			if (optionalClientes.isPresent() && !optionalClientes.get().isEmpty()) {
				log.info("Se encontraron {} clientes", optionalClientes.get().size());
				return optionalClientes.get().stream().map(clienteConverter::convertEntityToDTO)
						.collect(Collectors.toList());
			} else {
				log.warn("No se encontraron clientes en la base de datos");
				return Collections.emptyList();
			}

		} catch (Exception e) {
			log.error("Error al obtener la lista de clientes: {}", e.getMessage(), e);
			throw new ServiceException("Error al obtener la lista de clientes", e);
		}
	}


	@Override
	public ClienteDTO findById(Long id) throws ServiceException {
		log.info("Iniciando la busqueda del cliente con id: {}", id);
		try {
			return clienteRepository.findById(id).map(clienteConverter::convertEntityToDTO).orElseThrow(() -> {
				log.warn("Cliente con id {} no encontrado", id);
				return new ServiceException("Cliente no encontrado con id: " + id);
			});
		} catch (Exception e) {
			log.error("Error al buscar el cliente con id {}: {}", id, e.getMessage(), e);
			throw new ServiceException("Error al buscar el cliente con id: " + id, e);
		}
	}


	@Override
	public ClienteDTO create(ClienteDTO clienteDTO) throws ServiceException {
		log.info("Creando nuevo cliente: {}", clienteDTO);
		try {
			Cliente cliente = clienteConverter.convertDTOToEntity(clienteDTO);
			Cliente clienteGuardado = clienteRepository.save(cliente);
			log.info("Cliente creado exitosamente con id: {}", clienteGuardado.getId());
			return clienteConverter.convertEntityToDTO(clienteGuardado);
		} catch (Exception e) {
			log.error("Error al crear el cliente: {}", e.getMessage(), e);
			throw new ServiceException("Error al crear el cliente", e);
		}
	}

	@Override
	public ClienteDTO update(Long id, ClienteDTO clienteDTO) throws ServiceException {
		log.info("Actualizando cliente con id: {}", id);
		try {
			Cliente clienteExistente = clienteRepository.findById(id).orElseThrow(() -> {
				log.warn("Cliente no encontrado con id: {}", id);
				return new ServiceException("Cliente no encontrado con id: " + id);
			});

			clienteExistente.setNombre(clienteDTO.nombre());
			clienteExistente.setApPaterno(clienteDTO.apPaterno());
			clienteExistente.setApMaterno(clienteDTO.apMaterno());
			clienteExistente.setRfc(clienteDTO.rfc());

			Cliente clienteActualizado = clienteRepository.save(clienteExistente);
			log.info("Cliente actualizado exitosamente con id: {}", id);
			return clienteConverter.convertEntityToDTO(clienteActualizado);
		} catch (Exception e) {
			log.error("Error al actualizar el cliente con id {}: {}", id, e.getMessage(), e);
			throw new ServiceException("Error al actualizar el cliente con id: " + id, e);
		}
	}

	
	@Override
	public void delete(Long id) throws ServiceException {
		log.info("Eliminando cliente con id: {}", id);
		try {
			if (!clienteRepository.existsById(id)) {
				log.warn("Cliente no encontrado con id: {}", id);
				throw new ServiceException("Cliente no encontrado con id: " + id);
			}
			clienteRepository.deleteById(id);
			log.info("Cliente eliminado exitosamente con id: {}", id);
		} catch (Exception e) {
			log.error("Error al eliminar el cliente con id {}: {}", id, e.getMessage(), e);
			throw new ServiceException("Error al eliminar el cliente con id: " + id, e);
		}
	}

}
