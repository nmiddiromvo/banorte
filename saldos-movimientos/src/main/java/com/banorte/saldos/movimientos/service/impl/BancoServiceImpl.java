package com.banorte.saldos.movimientos.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.banorte.saldos.movimientos.converter.BancoConverter;
import com.banorte.saldos.movimientos.exception.ServiceException;
import com.banorte.saldos.movimientos.model.dto.BancoDTO;
import com.banorte.saldos.movimientos.model.entity.Banco;
import com.banorte.saldos.movimientos.repository.IBancoRepository;
import com.banorte.saldos.movimientos.service.IBancoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BancoServiceImpl implements IBancoService {

    private final IBancoRepository bancoRepository;
    private final BancoConverter bancoConverter;

    public BancoServiceImpl(IBancoRepository bancoRepository, BancoConverter bancoConverter) {
        this.bancoRepository = bancoRepository;
        this.bancoConverter = bancoConverter;
    }

	@Override
	public List<BancoDTO> getLista() throws ServiceException {
		log.info("Iniciando la consulta para obtener la lista de bancos");
		try {
			List<Banco> bancos = bancoRepository.findAll();
			if (bancos.isEmpty()) {
				log.warn("No se encontraron bancos en la base de datos");
			} else {
				log.info("Se encontraron {} bancos", bancos.size());
			}
			return Optional.ofNullable(bancos).filter(list -> !list.isEmpty())
					.map(list -> list.stream().map(bancoConverter::convertEntityToDTO).collect(Collectors.toList()))
					.orElse(Collections.emptyList());
		} catch (Exception e) {
			log.error("Error al obtener la lista de bancos: {}", e.getMessage(), e);
			throw new ServiceException("Error al obtener la lista de bancos", e);
		}
	}

}

