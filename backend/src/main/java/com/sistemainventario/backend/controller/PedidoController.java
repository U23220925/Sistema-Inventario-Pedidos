package com.sistemainventario.backend.controller;

import com.sistemainventario.backend.dto.PedidoRequest;
import com.sistemainventario.backend.entity.Pedido;
import com.sistemainventario.backend.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> registrar(@Valid @RequestBody PedidoRequest request) {
        Pedido pedido = pedidoService.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> consultar() {
        return ResponseEntity.ok(pedidoService.consultar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.consultarPorId(id));
    }
}