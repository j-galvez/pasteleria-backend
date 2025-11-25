package com.example.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.model.Pedido;
import com.example.backend.repository.PedidoRepository;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    // CREATE
    public Pedido crearPedido(Pedido pedido) {
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
