package com.sistemainventario.backend.service;


import java.util.Arrays;
import java.util.List;
import com.sistemainventario.backend.entity.Categoria;
import com.sistemainventario.backend.entity.Producto;
import com.sistemainventario.backend.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

            @Test
    void deberiaEliminarUnProducto() {
        // Arrange
        Categoria categoria = new Categoria("Bebidas");
        Producto producto = new Producto("Coca Cola 500ml", "Bebida gaseosa", new BigDecimal("3.50"), 100, categoria);
        producto.setId(1L);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        // Act
        productoService.eliminar(1L);

        // Assert
        verify(productoRepository).deleteById(1L);
    }
}