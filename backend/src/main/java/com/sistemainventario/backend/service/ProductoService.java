package com.sistemainventario.backend.service;

import com.sistemainventario.backend.entity.Producto;
import com.sistemainventario.backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistemainventario.backend.exception.ProductoNotFoundException;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    public Producto buscarPorId(Long id) {
    return productoRepository.findById(id)
            .orElseThrow(() -> new ProductoNotFoundException(id));
}

    public Producto actualizar(Long id, Producto datosActualizados) {
        Producto producto = buscarPorId(id);

        producto.setNombre(datosActualizados.getNombre());
        producto.setDescripcion(datosActualizados.getDescripcion());
        producto.setPrecio(datosActualizados.getPrecio());
        producto.setStock(datosActualizados.getStock());
        producto.setCategoria(datosActualizados.getCategoria());

        return productoRepository.save(producto);
    }

    public void eliminar(Long id) {
        buscarPorId(id);
        productoRepository.deleteById(id);
    }
}