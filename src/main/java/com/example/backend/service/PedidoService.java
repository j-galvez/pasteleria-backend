package com.example.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.model.Pedido;
import com.example.backend.model.DetallePedido;
import com.example.backend.model.Producto;
import com.example.backend.model.Usuario;
import com.example.backend.repository.PedidoRepository;
import com.example.backend.repository.ProductoRepository;
import com.example.backend.repository.UsuarioRepository;
import java.time.LocalDateTime;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ProductoRepository productoRepository; // Necesario para obtener precios

    // CREATE
    @Transactional
    public Pedido crearPedido(Pedido pedido) {
        // 1. VALIDAR Y ASIGNAR USUARIO
        String rutUsuario = pedido.getUsuario().getRun();
        Usuario usuarioDB = usuarioRepository.findById(rutUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con RUT: " + rutUsuario));
        pedido.setUsuario(usuarioDB);

        Double totalPedido = 0.0;
        
        // 2. PROCESAR DETALLES, VALIDAR PRODUCTOS Y CALCULAR TOTALES
        if (pedido.getDetalles() == null || pedido.getDetalles().isEmpty()) {
            throw new RuntimeException("El pedido debe contener al menos un producto.");
        }

        for (DetallePedido detalle : pedido.getDetalles()) {
            
            // a. Validar Producto y obtener precio
            Long idProducto = detalle.getProducto().getId(); // Asumo que Producto tiene getId()
            Producto productoDB = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + idProducto));
            
            Double precioUnitario = productoDB.getPrecio(); // Asumo que Producto tiene getPrecio()

            // b. Cálculo
            Double subtotal = precioUnitario * detalle.getCantidad();
            
            // c. Asignación de datos transaccionales
            detalle.setProducto(productoDB); // Usar la instancia gestionada por JPA
            detalle.setSubtotal(subtotal);
            detalle.setPedido(pedido); // Establece la relación bidireccional (¡CRUCIAL!)
            
            totalPedido += subtotal;
        }

        // 3. ASIGNAR TOTAL FINAL Y GUARDAR
        pedido.setTotal(totalPedido);
        pedido.setFechaPedido(LocalDateTime.now()); // Asegura que la fecha sea la del servidor
        
        // Guardar el Pedido (los DetallePedido se guardan en cascada)
        return pedidoRepository.save(pedido);
    }

    // READ - Obtener pedido por ID
    public Optional<Pedido> obtenerPedidoPorId(Long idPedido) {
        return pedidoRepository.findById(idPedido);
    }

    // UPDATE
    public Pedido actualizarPedido(Long idPedido, Pedido pedidoActualizado) {
        return pedidoRepository.findById(idPedido).map(pedido -> {
            pedido.setUsuario(pedidoActualizado.getUsuario());
            pedido.setDetalles(pedidoActualizado.getDetalles());
            pedido.setFechaPedido(pedidoActualizado.getFechaPedido());
            pedido.setTotal(pedidoActualizado.getTotal());
            return pedidoRepository.save(pedido);
        }).orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + idPedido));
    }

    // DELETE
    public void eliminarPedido(Long idPedido) {
        if (pedidoRepository.existsById(idPedido)) {
            pedidoRepository.deleteById(idPedido);
        } else {
            throw new RuntimeException("Pedido no encontrado con ID: " + idPedido);
        }
    }
}
