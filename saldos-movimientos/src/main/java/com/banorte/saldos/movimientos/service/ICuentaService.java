package com.banorte.saldos.movimientos.service;

import java.util.List;

import com.banorte.saldos.movimientos.exception.ServiceException;
import com.banorte.saldos.movimientos.model.dto.CuentaDTO;

public interface ICuentaService {

	List<CuentaDTO> findAll() throws ServiceException;

	CuentaDTO findById(Long id) throws ServiceException;

	CuentaDTO create(CuentaDTO cuentaDTO) throws ServiceException;

	CuentaDTO update(Long id, CuentaDTO cuentaDTO) throws ServiceException;

	void delete(Long id) throws ServiceException;

}
