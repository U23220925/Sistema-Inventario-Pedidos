package com.sistemainventario.backend.controller;

import tools.jackson.databind.json.JsonMapper;
import com.sistemainventario.backend.dto.ProductoRequest;
import com.sistemainventario.backend.entity.Categoria;
import com.sistemainventario.backend.entity.Producto;
import com.sistemainventario.backend.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(ProductoController.class)
class ProductoControllerTest {
    // ... el resto del código queda exactamente igual

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService productoService;

    @Autowired
    private JsonMapper objectMapper;

    @Test
    void deberiaCrearProductoYRetornar201() throws Exception {
        // Arrange
        Categoria categoria = new Categoria("Bebidas");
        categoria.setId(1L);

        Producto productoGuardado = new Producto("Coca Cola 500ml", "Bebida gaseosa", new BigDecimal("3.50"), 100, categoria);
        productoGuardado.setId(1L);

        ProductoRequest request = new ProductoRequest();
        request.setNombre("Coca Cola 500ml");
        request.setDescripcion("Bebida gaseosa");
        request.setPrecio(new BigDecimal("3.50"));
        request.setStock(100);
        request.setCategoriaId(1L);

        when(productoService.crear(org.mockito.ArgumentMatchers.any(Producto.class))).thenReturn(productoGuardado);

        // Act & Assert
        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Coca Cola 500ml"))
                .andExpect(jsonPath("$.precio").value(3.50));
    }

    @Test
    void deberiaRetornar400SiNombreEstaVacio() throws Exception {
        // Arrange
        ProductoRequest request = new ProductoRequest();
        request.setNombre(""); // inválido
        request.setPrecio(new BigDecimal("3.50"));
        request.setStock(100);
        request.setCategoriaId(1L);

        // Act & Assert
        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deberiaListarProductosYRetornar200() throws Exception {
        // Arrange
        when(productoService.listar()).thenReturn(java.util.Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk());
    }
}