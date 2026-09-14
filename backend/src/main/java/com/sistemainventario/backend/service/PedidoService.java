package com.sistemainventario.backend.service;

import com.sistemainventario.backend.dto.PedidoRequest;
import com.sistemainventario.backend.entity.Cliente;
import com.sistemainventario.backend.entity.DetallePedido;
import com.sistemainventario.backend.entity.Pedido;
import com.sistemainventario.backend.entity.Producto;
import com.sistemainventario.backend.repository.ClienteRepository;
import com.sistemainventario.backend.repository.PedidoRepository;
import com.sistemainventario.backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public Pedido registrar(PedidoRequest request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + request.getClienteId()));

        Pedido pedido = new Pedido(cliente);

        for (PedidoRequest.DetalleRequest detalleSolicitado : request.getDetalles()) {
            Producto producto = productoRepository.findById(detalleSolicitado.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + detalleSolicitado.getProductoId()));

            DetallePedido detalle = new DetallePedido(pedido, producto, detalleSolicitado.getCantidad());
            pedido.getDetalles().add(detalle);
        }

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> consultar() {
        return pedidoRepository.findAll();
    }

    public Pedido consultarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
    }
}