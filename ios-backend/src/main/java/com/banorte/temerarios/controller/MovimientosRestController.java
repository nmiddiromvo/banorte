package com.banorte.temerarios.controller;

import com.banorte.temerarios.dto.MovimientoDTO;
import com.banorte.temerarios.entity.MovimientosEntity;
import com.banorte.temerarios.service.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/api/movimientos/")
public class MovimientosRestController {

    @Autowired
    MovimientosService movimientosService;

    @GetMapping("/list")
    public List<MovimientosEntity> obtenerMovimientos() {
        return movimientosService.obtenerTodosLosMovimientos();
    }

    @GetMapping("/tipo/{tipo}")
    public List<MovimientosEntity> filtrarMovimientosPorTipo(@PathVariable String tipo) {
        return movimientosService.filtrarPorTipo(tipo);
    }

    @PutMapping("/{id}/favorito")
    public MovimientosEntity marcarComoFavorito(@PathVariable int id) {
        return movimientosService.marcarComoFavorito(id);
    }

    @DeleteMapping("/{id}/delete")
    public void eliminarMovimiento(@PathVariable int id) {
        movimientosService.eliminaMovimiento(id);
    }

}
