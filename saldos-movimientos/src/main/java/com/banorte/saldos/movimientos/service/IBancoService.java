package com.banorte.saldos.movimientos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banorte.saldos.movimientos.exception.ServiceException;
import com.banorte.saldos.movimientos.model.dto.BancoDTO;

@Service
public interface IBancoService {
	
	List<BancoDTO> getLista() throws ServiceException;

}
