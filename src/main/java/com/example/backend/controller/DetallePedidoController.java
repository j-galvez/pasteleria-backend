package com.example.backend.controller;

import com.example.backend.model.DetallePedido;
import com.example.backend.service.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-pedido")
@CrossOrigin(origins = "http://localhost:3000")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    // POST - Crear un nuevo detalle de pedido
    @PostMapping
    public ResponseEntity<DetallePedido> crearDetallePedido(@RequestBody DetallePedido detallePedido) {
        DetallePedido nuevoDetalle = detallePedidoService.crearDetallePedido(detallePedido);
        return new ResponseEntity<>(nuevoDetalle, HttpStatus.CREATED);
    }

    // GET - Obtener todos los detalles de pedido
    @GetMapping
    public ResponseEntity<List<DetallePedido>> obtenerTodosLosDetalles() {
        List<DetallePedido> detalles = detallePedidoService.obtenerTodosLosDetalles();
        return new ResponseEntity<>(detalles, HttpStatus.OK);
    }

    // GET - Obtener un detalle de pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> obtenerDetallePorId(@PathVariable Long id) {
        return detallePedidoService.obtenerDetallePorId(id)
                .map(detalle -> new ResponseEntity<>(detalle, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // PUT - Actualizar un detalle de pedido
    @PutMapping("/{id}")
    public ResponseEntity<DetallePedido> actualizarDetallePedido(@PathVariable Long id, @RequestBody DetallePedido detalleActualizado) {
        try {
            DetallePedido detalle = detallePedidoService.actualizarDetallePedido(id, detalleActualizado);
            return new ResponseEntity<>(detalle, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - Eliminar un detalle de pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetallePedido(@PathVariable Long id) {
        try {
            detallePedidoService.eliminarDetallePedido(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}