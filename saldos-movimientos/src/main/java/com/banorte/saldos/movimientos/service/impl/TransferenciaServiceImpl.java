package com.banorte.saldos.movimientos.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.banorte.saldos.movimientos.converter.MovimientosConverter;
import com.banorte.saldos.movimientos.converter.TransferenciaConverter;
import com.banorte.saldos.movimientos.exception.ServiceException;
import com.banorte.saldos.movimientos.model.dto.MovimientosDTO;
import com.banorte.saldos.movimientos.model.dto.TransferenciaDTO;
import com.banorte.saldos.movimientos.model.entity.Movimientos;
import com.banorte.saldos.movimientos.model.entity.Transferencia;
import com.banorte.saldos.movimientos.repository.IMovimientosRepository;
import com.banorte.saldos.movimientos.repository.ITransferenciaRepository;
import com.banorte.saldos.movimientos.service.ITransferenciaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransferenciaServiceImpl implements ITransferenciaService {

    private final ITransferenciaRepository transferenciaRepository;
    private final IMovimientosRepository movimientosRepository;
    private final TransferenciaConverter transferenciaConverter;
    private final MovimientosConverter movimientosConverter;

    public TransferenciaServiceImpl(ITransferenciaRepository transferenciaRepository, IMovimientosRepository movimientosRepository,
                                    TransferenciaConverter transferenciaConverter, MovimientosConverter movimientosConverter) {
        this.transferenciaRepository = transferenciaRepository;
        this.movimientosRepository = movimientosRepository;
        this.transferenciaConverter = transferenciaConverter;
        this.movimientosConverter = movimientosConverter;
    }

    @Override
    public List<TransferenciaDTO> findAll() throws ServiceException {
        log.info("Iniciando la consulta para obtener la lista de transferencias");
        try {
            List<Transferencia> transferencias = transferenciaRepository.findAll();
            Optional<List<Transferencia>> optionalTransferencias = Optional.ofNullable(transferencias);

            if (optionalTransferencias.isPresent() && !optionalTransferencias.get().isEmpty()) {
                log.info("Se encontraron {} transferencias", optionalTransferencias.get().size());
                return optionalTransferencias.get().stream()
                        .map(transferenciaConverter::convertEntityToDTO)
                        .collect(Collectors.toList());
            } else {
                log.warn("No se encontraron transferencias en la base de datos");
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.error("Error al obtener la lista de transferencias: {}", e.getMessage(), e);
            throw new ServiceException("Error al obtener la lista de transferencias", e);
        }
    }


    @Override
    public TransferenciaDTO findById(Long id) throws ServiceException {
        log.info("Buscando transferencia con id: {}", id);
        try {
            return transferenciaRepository.findById(id)
                    .map(transferenciaConverter::convertEntityToDTO)
                    .orElseThrow(() -> {
                        log.warn("Transferencia no encontrada con id: {}", id);
                        return new ServiceException("Transferencia no encontrada con id: " + id);
                    });
        } catch (Exception e) {
            log.error("Error al buscar la transferencia con id {}: {}", id, e.getMessage(), e);
            throw new ServiceException("Error al buscar la transferencia con id: " + id, e);
        }
    }

    @Override
    public TransferenciaDTO create(TransferenciaDTO transferenciaDTO) throws ServiceException {
        log.info("Creando nueva transferencia: {}", transferenciaDTO);
        try {
            Transferencia transferencia = transferenciaConverter.convertDTOToEntity(transferenciaDTO);
            Transferencia transferenciaGuardada = transferenciaRepository.save(transferencia);
            log.info("Transferencia creada exitosamente con id: {}", transferenciaGuardada.getId());

            MovimientosDTO movimientosDTO = new MovimientosDTO();
            movimientosDTO.setMonto(transferenciaDTO.getMonto());
            movimientosDTO.setFecha(transferenciaDTO.getFecha());
            movimientosDTO.setTipo(transferenciaDTO.getTipo().toUpperCase());
            movimientosDTO.setConcepto(transferenciaDTO.getConcepto());
            movimientosDTO.setFavorito(false);

            Movimientos movimiento = movimientosConverter.convertDTOToEntity(movimientosDTO);
            movimientosRepository.save(movimiento);
            log.info("Movimiento registrado exitosamente para la transferencia con id: {}", transferenciaGuardada.getId());

            return transferenciaConverter.convertEntityToDTO(transferenciaGuardada);
        } catch (Exception e) {
            log.error("Error al crear la transferencia: {}", e.getMessage(), e);
            throw new ServiceException("Error al crear la transferencia", e);
        }
    }


	@Override
	public void delete(Long id) throws ServiceException {
		log.info("Eliminando transferencia con id: {}", id);
		try {
			if (!transferenciaRepository.existsById(id)) {
				log.warn("Transferencia no encontrada con id: {}", id);
				throw new ServiceException("Transferencia no encontrada con id: " + id);
			}
			transferenciaRepository.deleteById(id);
			log.info("Transferencia eliminada exitosamente con id: {}", id);
		} catch (Exception e) {
			log.error("Error al eliminar la transferencia con id {}: {}", id, e.getMessage(), e);
			throw new ServiceException("Error al eliminar la transferencia con id: " + id, e);
		}
	}

}

