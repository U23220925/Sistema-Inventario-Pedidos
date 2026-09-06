package com.sistemainventario.backend.service;

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
    void deberiaCrearUnProductoCorrectamente() {
        // Arrange (preparar los datos de prueba)
        Categoria categoria = new Categoria("Bebidas");
        Producto producto = new Producto("Coca Cola 500ml", "Bebida gaseosa", new BigDecimal("3.50"), 100, categoria);

        when(productoRepository.save(producto)).thenReturn(producto);

        // Act (ejecutar la acción que estamos probando)
        Producto resultado = productoService.crear(producto);

        // Assert (verificar que el resultado es el esperado)
        assertNotNull(resultado);
        assertEquals("Coca Cola 500ml", resultado.getNombre());
        assertEquals(new BigDecimal("3.50"), resultado.getPrecio());
    }
}