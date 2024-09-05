package com.banorte.saldos.movimientos.service;

import java.util.List;

import com.banorte.saldos.movimientos.exception.ServiceException;
import com.banorte.saldos.movimientos.model.dto.ClienteDTO;

public interface IClienteService {
	
    List<ClienteDTO> findAll() throws ServiceException;
    ClienteDTO findById(Long id) throws ServiceException;
    ClienteDTO create(ClienteDTO clienteDTO) throws ServiceException;
    ClienteDTO update(Long id, ClienteDTO clienteDTO) throws ServiceException;
    void delete(Long id) throws ServiceException;
}

