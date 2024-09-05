package com.banorte.saldos.movimientos.service;

import java.util.List;

import com.banorte.saldos.movimientos.exception.ServiceException;
import com.banorte.saldos.movimientos.model.dto.TransferenciaDTO;

public interface ITransferenciaService {

	List<TransferenciaDTO> findAll() throws ServiceException;

	TransferenciaDTO findById(Long id) throws ServiceException;

	TransferenciaDTO create(TransferenciaDTO transferenciaDTO) throws ServiceException;

	void delete(Long id) throws ServiceException;

}
