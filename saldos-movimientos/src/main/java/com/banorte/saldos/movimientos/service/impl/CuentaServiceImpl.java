package com.banorte.saldos.movimientos.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.banorte.saldos.movimientos.converter.ClienteConverter;
import com.banorte.saldos.movimientos.converter.CuentaConverter;
import com.banorte.saldos.movimientos.exception.ServiceException;
import com.banorte.saldos.movimientos.model.dto.CuentaDTO;
import com.banorte.saldos.movimientos.model.entity.Cuenta;
import com.banorte.saldos.movimientos.repository.ICuentaRepository;
import com.banorte.saldos.movimientos.service.ICuentaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CuentaServiceImpl implements ICuentaService {

    private final ICuentaRepository cuentaRepository;
    private final CuentaConverter cuentaConverter;
    private final ClienteConverter clienteConverter;

    public CuentaServiceImpl(ICuentaRepository cuentaRepository, CuentaConverter cuentaConverter, ClienteConverter clienteConverter) {
        this.cuentaRepository = cuentaRepository;
        this.cuentaConverter = cuentaConverter;
        this.clienteConverter = clienteConverter;
    }

    @Override
    public List<CuentaDTO> findAll() throws ServiceException {
        log.info("Iniciando la consulta para obtener la lista de cuentas");

        try {
            List<Cuenta> cuentas = cuentaRepository.findAll();
            Optional<List<Cuenta>> optionalCuentas = Optional.ofNullable(cuentas);

            if (optionalCuentas.isPresent() && !optionalCuentas.get().isEmpty()) {
                log.info("Se encontraron {} cuentas", optionalCuentas.get().size());
                return optionalCuentas.get().stream()
                        .map(cuentaConverter::convertEntityToDTO)
                        .collect(Collectors.toList());
            } else {
                log.warn("No se encontraron cuentas en la base de datos");
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.error("Error al obtener la lista de cuentas: {}", e.getMessage(), e);
            throw new ServiceException("Error al obtener la lista de cuentas", e);
        }
    }


    @Override
    public CuentaDTO findById(Long id) throws ServiceException {
        log.info("Buscando cuenta con id: {}", id);

        try {
            return cuentaRepository.findById(id)
                    .map(cuentaConverter::convertEntityToDTO)
                    .orElseThrow(() -> {
                        log.warn("Cuenta no encontrada con id: {}", id);
                        return new ServiceException("Cuenta no encontrada con id: " + id);
                    });
        } catch (Exception e) {
            log.error("Error al buscar la cuenta con id {}: {}", id, e.getMessage(), e);
            throw new ServiceException("Error al buscar la cuenta con id: " + id, e);
        }
    }


    @Override
    public CuentaDTO create(CuentaDTO cuentaDTO) throws ServiceException {
        log.info("Creando nueva cuenta: {}", cuentaDTO);

        try {
            Cuenta cuenta = cuentaConverter.convertDTOToEntity(cuentaDTO);
            Cuenta cuentaGuardada = cuentaRepository.save(cuenta);
            log.info("Cuenta creada exitosamente con id: {}", cuentaGuardada.getId());
            return cuentaConverter.convertEntityToDTO(cuentaGuardada);
        } catch (Exception e) {
            log.error("Error al crear la cuenta: {}", e.getMessage(), e);
            throw new ServiceException("Error al crear la cuenta", e);
        }
    }


    @Override
    public CuentaDTO update(Long id, CuentaDTO cuentaDTO) throws ServiceException {
        log.info("Actualizando cuenta con id: {}", id);

        try {
            Cuenta cuentaExistente = cuentaRepository.findById(id)
                    .orElseThrow(() -> {
                        log.warn("Cuenta no encontrada con id: {}", id);
                        return new ServiceException("Cuenta no encontrada con id: " + id);
                    });

            cuentaExistente.setCuenta(cuentaDTO.cuenta());
            cuentaExistente.setTipo(cuentaDTO.tipo());
            cuentaExistente.setCliente(clienteConverter.convertDTOToEntity(cuentaDTO.cliente()));

            Cuenta cuentaActualizada = cuentaRepository.save(cuentaExistente);
            log.info("Cuenta actualizada exitosamente con id: {}", id);
            return cuentaConverter.convertEntityToDTO(cuentaActualizada);
        } catch (Exception e) {
            log.error("Error al actualizar la cuenta con id {}: {}", id, e.getMessage(), e);
            throw new ServiceException("Error al actualizar la cuenta con id: " + id, e);
        }
    }


    @Override
    public void delete(Long id) throws ServiceException {
        log.info("Eliminando cuenta con id: {}", id);
        try {
            if (!cuentaRepository.existsById(id)) {
                log.warn("Cuenta no encontrada con id: {}", id);
                throw new ServiceException("Cuenta no encontrada con id: " + id);
            }

            cuentaRepository.deleteById(id);
            log.info("Cuenta eliminada exitosamente con id: {}", id);
        } catch (Exception e) {
            log.error("Error al eliminar la cuenta con id {}: {}", id, e.getMessage(), e);
            throw new ServiceException("Error al eliminar la cuenta con id: " + id, e);
        }
    }

}
