package com.example.backend.service;

import com.example.backend.model.DetallePedido;
import com.example.backend.model.Producto;
import com.example.backend.repository.DetallePedidoRepository;
import com.example.backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private ProductoRepository productoRepository; // Necesario para obtener el precio del producto

    // CREATE
    public DetallePedido crearDetallePedido(DetallePedido detallePedido) {
        // Lógica para calcular el subtotal antes de guardar
        Producto producto = productoRepository.findById(detallePedido.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        detallePedido.setSubtotal(detallePedido.getCantidad() * producto.getPrecio());
        return detallePedidoRepository.save(detallePedido);
    }

    // READ
    public List<DetallePedido> obtenerTodosLosDetalles() {
        return detallePedidoRepository.findAll();
    }

    public Optional<DetallePedido> obtenerDetallePorId(Long id) {
        return detallePedidoRepository.findById(id);
    }

    // UPDATE
    public DetallePedido actualizarDetallePedido(Long id, DetallePedido detalleActualizado) {
        return detallePedidoRepository.findById(id).map(detalle -> {
            Producto producto = productoRepository.findById(detalleActualizado.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            detalle.setPedido(detalleActualizado.getPedido());
            detalle.setProducto(detalleActualizado.getProducto());
            detalle.setCantidad(detalleActualizado.getCantidad());
            detalle.setSubtotal(detalleActualizado.getCantidad() * producto.getPrecio()); // Recalcular subtotal
            return detallePedidoRepository.save(detalle);
        }).orElseThrow(() -> new RuntimeException("Detalle de pedido no encontrado con ID: " + id));
    }

    // DELETE
    public void eliminarDetallePedido(Long id) {
        if (!detallePedidoRepository.existsById(id)) {
            throw new RuntimeException("Detalle de pedido no encontrado con ID: " + id);
        }
        detallePedidoRepository.deleteById(id);
    }
}