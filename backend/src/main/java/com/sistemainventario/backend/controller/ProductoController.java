package com.sistemainventario.backend.controller;

import com.sistemainventario.backend.dto.ProductoRequest;
import com.sistemainventario.backend.dto.ProductoResponse;
import com.sistemainventario.backend.entity.Categoria;
import com.sistemainventario.backend.entity.Producto;
import com.sistemainventario.backend.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody ProductoRequest request) {
        Producto producto = convertirAEntidad(request);
        Producto guardado = productoService.crear(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirAResponse(guardado));
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponse>> listar() {
        List<ProductoResponse> productos = productoService.listar().stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> buscarPorId(@PathVariable Long id) {
        Producto producto = productoService.buscarPorId(id);
        return ResponseEntity.ok(convertirAResponse(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequest request) {
        Producto datosActualizados = convertirAEntidad(request);
        Producto actualizado = productoService.actualizar(id, datosActualizados);
        return ResponseEntity.ok(convertirAResponse(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // --- Métodos auxiliares de conversión ---

    private Producto convertirAEntidad(ProductoRequest request) {
        Categoria categoria = new Categoria();
        categoria.setId(request.getCategoriaId());

        return new Producto(
                request.getNombre(),
                request.getDescripcion(),
                request.getPrecio(),
                request.getStock(),
                categoria
        );
    }

    private ProductoResponse convertirAResponse(Producto producto) {
        String nombreCategoria = producto.getCategoria() != null ? producto.getCategoria().getNombre() : null;
        return new ProductoResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                nombreCategoria
        );
    }
}