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
    void deberiaListarTodosLosProductos() {
        // Arrange
        Categoria categoria = new Categoria("Bebidas");
        Producto producto1 = new Producto("Coca Cola 500ml", "Bebida gaseosa", new BigDecimal("3.50"), 100, categoria);
        Producto producto2 = new Producto("Agua sin gas 600ml", "Agua embotellada", new BigDecimal("1.50"), 200, categoria);

        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto1, producto2));

        // Act
        List<Producto> resultado = productoService.listar();

        // Assert
        assertEquals(2, resultado.size());
    }
}