package com.sistemainventario.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class PedidoRequest {

    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId;

    @NotEmpty(message = "El pedido debe tener al menos un detalle")
    private List<DetalleRequest> detalles;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<DetalleRequest> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleRequest> detalles) {
        this.detalles = detalles;
    }

    public static class DetalleRequest {

        @NotNull(message = "El producto es obligatorio")
        private Long productoId;

        @NotNull(message = "La cantidad es obligatoria")
        private Integer cantidad;

        public Long getProductoId() {
            return productoId;
        }

        public void setProductoId(Long productoId) {
            this.productoId = productoId;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }
    }
}