package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend.model.Pedido;
import com.example.backend.service.PedidoService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:3000")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // POST - Crear nuevo pedido
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoService.crearPedido(pedido);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }

    // GET - Obtener pedido por ID
    @GetMapping("/{idPedido}")
    public ResponseEntity<Pedido> obtenerPedidoPorId(@PathVariable Long idPedido) {
        Optional<Pedido> pedido = pedidoService.obtenerPedidoPorId(idPedido);
        return pedido.map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // PUT - Actualizar pedido
    @PutMapping("/{idPedido}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable Long idPedido, @RequestBody Pedido pedidoActualizado) {
        try {
            Pedido pedido = pedidoService.actualizarPedido(idPedido, pedidoActualizado);
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - Eliminar pedido
    @DeleteMapping("/{idPedido}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long idPedido) {
        try {
            pedidoService.eliminarPedido(idPedido);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
